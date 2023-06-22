package com.homecalapp.homework

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Assignment(
    @SerializedName("assignmentName") val assignmentName: String,
    @SerializedName("className") val className: String,
    @SerializedName("professor") val professor: String,
    @SerializedName("dueDate") val dueDate: Date
)
