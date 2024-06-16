package com.assessment.nycschools.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/*Model class with schoolName, numOfSatTestTakers, satCriticalReadingAvgScore, satMathAvgScore, satWritingAvgScore */
@Parcelize
data class SchoolDetail(
    @SerializedName("num_of_sat_test_takers") val numOfSatTestTakers: String = "Unknown",
    @SerializedName("sat_critical_reading_avg_score") val satCriticalReadingAvgScore: String = "Unknown",
    @SerializedName("sat_math_avg_score") val satMathAvgScore: String = "Unknown",
    @SerializedName("sat_writing_avg_score") val satWritingAvgScore: String = "Unknown"
) : Parcelable
