package com.anupdey.app.compose_playground.domain.use_case

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ValidateTerms @Inject constructor() {

    fun execute(acceptedTerms: Boolean): ValidationResult {
        if(!acceptedTerms) {
            return ValidationResult(
                successful = false,
                errorMessage = "Please accept the terms"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}