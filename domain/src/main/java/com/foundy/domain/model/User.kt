package com.foundy.domain.model

import com.google.gson.annotations.SerializedName

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val studentId: String,
    val type: String,
    val company: String?,
    val generation: String
)
