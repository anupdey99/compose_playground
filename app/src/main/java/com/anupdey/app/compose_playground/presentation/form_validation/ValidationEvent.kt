package com.anupdey.app.compose_playground.presentation.form_validation

sealed class ValidationEvent {
    object Success: ValidationEvent()
}
