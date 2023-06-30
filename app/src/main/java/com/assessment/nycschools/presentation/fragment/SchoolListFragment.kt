package com.assessment.nycschools.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.assessment.nycschools.R
import com.assessment.nycschools.data.model.School
import com.assessment.nycschools.databinding.FragmentSchoolListBinding
import com.assessment.nycschools.presentation.adapter.SchoolListAdapter
import com.assessment.nycschools.presentation.viewmodel.SchoolListViewModel
import com.assessment.nycschools.utils.ResponseHandler
import com.assessment.nycschools.utils.gone
import com.assessment.nycschools.utils.showDialog
import com.assessment.nycschools.utils.showSnackBar
import com.assessment.nycschools.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SchoolListFragment : Fragment() {

    private var _binding: FragmentSchoolListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var countryListAdapter: SchoolListAdapter

    /*Get the viewModel instance*/
    private val viewModel by lazy {
        ViewModelProvider(this)[SchoolListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSchoolListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserver()

        val callback = object : OnBackPressedCallback(
            true
        ) {
            override fun handleOnBackPressed() {
                showDialog()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner, callback
        )
    }

    /*Configure the UI elements*/
    private fun initView() {
        binding.rvCountries.apply {
            setHasFixedSize(true)
            setItemViewCacheSize(25)
            adapter = countryListAdapter
        }
    }

    /*Observe the flow elements*/
    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect {
                handleUiState(it)
            }
        }
    }

    /*Update the UI state based on response*/
    private fun handleUiState(uiState: ResponseHandler<List<School>>) {
        when (uiState) {
            is ResponseHandler.Loading -> {
                binding.progressbar.visible()
                binding.rvCountries.gone()
                binding.textViewFailed.gone()
            }

            is ResponseHandler.Success -> {
                binding.progressbar.gone()
                binding.rvCountries.visible()
                binding.textViewFailed.gone()
                uiState.data?.let { countryListAdapter.setData(it) }
            }

            is ResponseHandler.Error -> {
                binding.progressbar.gone()
                binding.rvCountries.gone()
                binding.textViewFailed.visible()
                binding.textViewFailed.text = uiState.message
                binding.layoutParent.showSnackBar(getString(R.string.message_general_error))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}