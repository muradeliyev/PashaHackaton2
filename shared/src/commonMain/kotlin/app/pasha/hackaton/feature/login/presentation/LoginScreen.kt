package app.pasha.hackaton.feature.login.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.pasha.hackaton.core.navigation.Screen
import app.pasha.hackaton.ui.kit.Typography
import app.pasha.hackaton.ui.kit.component.InputField
import app.pasha.hackaton.ui.kit.component.MainButton
import app.pasha.hackaton.ui.kit.icon.IcFlag


class LoginScreen(private val viewModel: LoginViewModel) : Screen {

    @Composable
    override fun Content() {
        val state by viewModel.state.collectAsStateWithLifecycle()
        var isPasswordVisible by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(48.dp)
                    .background(Color(0xffFFFFFF), shape = RoundedCornerShape(28.dp))
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    Text("AZ", style = Typography.l1)
                    Spacer(Modifier.size(12.dp))
                    Icon(
                        imageVector = IcFlag,
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
            )

            Column(
                modifier = Modifier
                    .width(600.dp)
                    .background(Color.White, RoundedCornerShape(24.dp))
                    .padding(48.dp),
            ) {
                Text("Welcome", style = Typography.h0)
                Spacer(Modifier.size(12.dp))
                Text("Login to your account", style = Typography.l1, color = Color(0xff9496AC))
                Spacer(Modifier.size(48.dp))

                InputField(
                    value = state.username,
                    onValueChange = { viewModel.onIntent(LoginIntent.UpdateUsername(it)) },
                    placeHolderText = "Username"
                )

                Spacer(Modifier.size(12.dp))

                InputField(
                    value = state.password,
                    onValueChange = { viewModel.onIntent(LoginIntent.UpdatePassword(it)) },
                    placeHolderText = "Password",
                    trailingIcon = {
                        IconButton(
                            onClick = { isPasswordVisible = !isPasswordVisible }
                        ) {
                            Icon(
                                imageVector = if (isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                contentDescription = null
                            )
                        }
                    },
                    visualTransformation = if(isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                Spacer(Modifier.size(24.dp))

                MainButton(
                    onClick = { viewModel.onIntent(LoginIntent.Login) },
                    content = {
                        Text("Login")
                    }
                )
            }
        }
    }
}
