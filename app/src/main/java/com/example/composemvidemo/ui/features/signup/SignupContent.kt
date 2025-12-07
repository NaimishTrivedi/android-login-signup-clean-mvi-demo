package com.example.composemvidemo.ui.features.signup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun SignupContent(
    state: SignupUiState = SignupUiState(),
    onNameChanged: (String) -> Unit = {},
    onEmailChanged: (String) -> Unit = {},
    onMobileChanged: (String) -> Unit = {},
    onPasswordChanged: (String) -> Unit = {},
    onCPasswordChanged: (String) -> Unit = {},
    onSignupClick: () -> Unit = {},
    onLoginClick: () -> Unit = {},
) {
    val focusManager = LocalFocusManager.current
    Box(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize()
    )
    {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.Center),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Text("Hey! Welcome...")
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = state.name,
                    isError = state.errorName,
                    onValueChange = onNameChanged,
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                if (state.errorName) {
                    Text(
                        "Required Name!",
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = state.email,
                    isError = state.errorEmail,
                    onValueChange = onEmailChanged,
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )
                if (state.errorEmail) {
                    Text(
                        "Invalid email",
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = state.mobile,
                    isError = state.errorMobile,
                    onValueChange = onMobileChanged,
                    label = { Text("Mobile") },
                    modifier = Modifier.fillMaxWidth()
                )
                if (state.errorMobile) {
                    Text(
                        "Invalid Mobile No.",
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = state.password,
                    isError = state.errorPassword,
                    visualTransformation = PasswordVisualTransformation(),
                    onValueChange = onPasswordChanged,
                    label = { Text("Password") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth()
                )
                if (state.errorPassword) {
                    Text(
                        "Password must be min 4 and max 8 characters",
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = state.cpassword,
                    isError = state.errorCpassword,
                    visualTransformation = PasswordVisualTransformation(),
                    onValueChange = onCPasswordChanged,
                    label = { Text("Confirm Password") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth()
                )
                if (state.errorCpassword) {
                    Text(
                        "Password and confirm password not match",
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = {
                    focusManager.clearFocus()
                    onSignupClick()
                }, modifier = Modifier.fillMaxWidth()) {
                    Text(if (state.isLoading) "Sign in..." else "Register")
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Already have an account?",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 12.sp,
                    modifier = Modifier.clickable { onLoginClick() }
                )
            }
        }
    }
}