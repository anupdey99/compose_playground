package com.anupdey.app.compose_playground.domain.use_case

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ValidateRepeatedPassword @Inject constructor() {

    fun execute(password: String, repeatedPassword: String): ValidationResult {
        if(password != repeatedPassword) {
            return ValidationResult(
                successful = false,
                errorMessage = "The passwords don't match"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}