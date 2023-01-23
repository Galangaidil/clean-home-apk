package com.kodegakure.sistempemesanancleaningservice.model

data class Register(
    val name: String,
    val email: String,
    val password: String,
    val password_confirmation: String
)
