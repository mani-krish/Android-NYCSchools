package com.assessment.nycschools.presentation.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.assessment.nycschools.R
import com.assessment.nycschools.data.model.School
import com.assessment.nycschools.databinding.ItemSchoolBinding
import javax.inject.Inject

class SchoolListAdapter @Inject constructor() :
    RecyclerView.Adapter<SchoolListAdapter.CountryViewHolder>() {

    private var dataSet: ArrayList<School> = ArrayList()

    inner class CountryViewHolder(private val binding: ItemSchoolBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(countryItem: School) {
            binding.item = countryItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder =
        CountryViewHolder(
            ItemSchoolBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun getItemCount(): Int = dataSet.count()

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(dataSet[position])
        val args = Bundle()
        args.putParcelable("schoolArg", dataSet[position])

        holder.itemView.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_FirstFragment_to_SecondFragment,
                args
            )
        )
    }

    /*Set the dataset to adapter list instance*/
    fun setData(list: List<School>) {
        this.dataSet = list as ArrayList<School>
        notifyDataSetChanged()
    }

}