package com.assessment.nycschools.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.assessment.nycschools.R
import com.assessment.nycschools.data.model.School
import com.assessment.nycschools.data.model.SchoolDetail
import com.assessment.nycschools.databinding.FragmentSchoolDetailBinding
import com.assessment.nycschools.presentation.viewmodel.SchoolDetailViewModel
import com.assessment.nycschools.utils.Constants
import com.assessment.nycschools.utils.ResponseHandler
import com.assessment.nycschools.utils.gone
import com.assessment.nycschools.utils.showSnackBar
import com.assessment.nycschools.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SchoolDetailFragment : Fragment() {

    private var _binding: FragmentSchoolDetailBinding? = null
    private val binding get() = _binding!!

    private val args: SchoolDetailFragmentArgs by navArgs()

    /*Get the viewModel instance*/
    private val viewModel by lazy {
        ViewModelProvider(this)[SchoolDetailViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSchoolDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()

        val school: School = args.schoolArg
        Constants.SCHOOL_DBN = school.schoolDbn
        binding.itemSchool = school
        viewModel.fetchSchool()

        val callback = object : OnBackPressedCallback(
            true
        ) {
            override fun handleOnBackPressed() {
                Navigation.findNavController(view)
                    .navigate(R.id.action_SecondFragment_to_FirstFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner, callback
        )
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
    private fun handleUiState(uiState: ResponseHandler<List<SchoolDetail>>) {
        when (uiState) {
            is ResponseHandler.Loading -> {
                binding.progressbar.visible()
            }

            is ResponseHandler.Success -> {
                binding.progressbar.gone()
                uiState.data?.let {
                    if (it.isNotEmpty()) {
                        binding.itemSAT = it[0]
                    } else {
                        binding.itemSAT = SchoolDetail()
                        binding.progressbar.gone()
                        binding.layoutParent.showSnackBar(getString(R.string.message_general_api_error))
                    }
                }
            }

            is ResponseHandler.Error -> {
                binding.progressbar.gone()
                binding.layoutParent.showSnackBar(getString(R.string.message_general_api_error))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}