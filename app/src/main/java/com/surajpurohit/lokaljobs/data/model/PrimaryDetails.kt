package com.surajpurohit.lokaljobs.data.model

import com.google.gson.annotations.SerializedName

data class PrimaryDetails(
    @SerializedName("Place")
    val place: String,
    @SerializedName("Salary")
    val salary: String,
    @SerializedName("Job_Type")
    val jobType: String,
    @SerializedName("Experience")
    val experience: String,
    @SerializedName("Fees_Charged")
    val feesCharged: String,
    @SerializedName("Qualification")
    val qualification: String
)