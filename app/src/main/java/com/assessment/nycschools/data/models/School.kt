package com.assessment.nycschools.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/*Model class with schoolName, schoolLocation, schoolPhoneNumber, schoolEmail, schoolWebsite */

@Parcelize
data class School(
    @SerializedName("school_name") val schoolName: String,
    @SerializedName("location") val schoolLocation: String,
    @SerializedName("primary_address_line_1") val schoolAddress: String,
    @SerializedName("city") val schoolCity: String,
    @SerializedName("zip") val schoolZip: String,
    @SerializedName("state_code") val schoolState: String,
    @SerializedName("phone_number") val schoolPhoneNumber: String,
    @SerializedName("school_email") val schoolEmail: String,
    @SerializedName("website") val schoolWebsite: String,
    @SerializedName("dbn") val schoolDbn: String,
    @SerializedName("overview_paragraph") val schoolOverview: String,
    @SerializedName("total_students") val totalStudents: String
) : Parcelable