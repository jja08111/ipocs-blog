package com.foundy.domain.model

data class Notice(
    val title: String,
    val content: String,
    val createdAt: String,
    val user: User
)
