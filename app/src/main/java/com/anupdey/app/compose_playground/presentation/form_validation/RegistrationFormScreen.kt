package com.anupdey.app.compose_playground.presentation.form_validation

import android.text.Layout
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collect

@Destination
@Composable
fun RegistrationFormScreen(
    navigator: DestinationsNavigator,
    viewModel: RegistrationViewModel = hiltViewModel()
) {

    val state = viewModel.state
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        viewModel.validationEvent.collect { event ->
            when (event) {
                ValidationEvent.Success -> {
                    Toast.makeText(context, "Registration successful", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    val focusRequester = remember {
        FocusRequester()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp), verticalArrangement = Arrangement.Center
    ) {

        //Email
        TextField(
            value = state.email, onValueChange = {
                viewModel.onEvent(RegistrationFormEvent.EmailChanged(it))
            },
            isError = state.emailError != null,
            modifier = Modifier.fillMaxWidth().focusRequester(focusRequester),
            placeholder = {
                Text(text = "Email")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            maxLines = 1,
            singleLine = true,
        )
        if (state.emailError != null) {
            Text(
                text = state.emailError,
                color = MaterialTheme.colors.error,
                modifier = Modifier.align(Alignment.End).padding(top = 2.dp),
                fontSize = 12.sp,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        //Password
        TextField(
            value = state.password, onValueChange = {
                viewModel.onEvent(RegistrationFormEvent.PasswordChanged(it))
            },
            isError = state.emailError != null,
            modifier = Modifier.fillMaxWidth().focusRequester(focusRequester),
            placeholder = {
                Text(text = "Password")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            visualTransformation = PasswordVisualTransformation(),
            maxLines = 1,
            singleLine = true,
        )
        if (state.passwordError != null) {
            Text(
                text = state.passwordError,
                color = MaterialTheme.colors.error,
                modifier = Modifier.align(Alignment.End).padding(top = 2.dp),
                fontSize = 12.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        //Repeat Password
        TextField(
            value = state.repeatedPassword, onValueChange = {
                viewModel.onEvent(RegistrationFormEvent.RepeatedPasswordChanged(it))
            },
            isError = state.repeatedPasswordError != null,
            modifier = Modifier.fillMaxWidth().focusRequester(focusRequester),
            placeholder = {
                Text(text = "Repeat password")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            visualTransformation = PasswordVisualTransformation(),
            maxLines = 1,
            singleLine = true
        )
        if (state.passwordError != null) {
            Text(
                text = state.passwordError,
                color = MaterialTheme.colors.error,
                modifier = Modifier.align(Alignment.End).padding(top = 2.dp),
                fontSize = 12.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        //Checkbox
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = state.acceptedTerms, onCheckedChange = {
                viewModel.onEvent(RegistrationFormEvent.AcceptTerms(it))
            })
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Accept teams")
        }
        if (state.termsError != null) {
            Text(
                text = state.termsError,
                color = MaterialTheme.colors.error,
                modifier = Modifier.align(Alignment.Start),
                fontSize = 12.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        //Submit
        Button(
            onClick = {
                viewModel.onEvent(RegistrationFormEvent.Submit)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Submit")
        }
    }
}