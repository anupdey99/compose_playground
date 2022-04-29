package com.anupdey.app.compose_playground.domain.use_case

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
