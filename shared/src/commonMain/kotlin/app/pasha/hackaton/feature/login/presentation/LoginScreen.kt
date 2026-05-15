package app.pasha.hackaton.feature.login.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.pasha.hackaton.core.navigation.Screen
import app.pasha.hackaton.ui.kit.Typography


class LoginScreen(private val viewModel: LoginViewModel) : Screen {

    @Composable
    override fun Content() {
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Box(Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(24.dp))
                    .padding(48.dp),
            ) {
                Text("Weclome", style = Typography.h0)
                Spacer(Modifier.size(12.dp))
                Text("Login to your account", style = Typography.l1)
                Spacer(Modifier.size(48.dp))


                TextField(
                    value = username,
                    onValueChange = { username = it },
                    placeholder = {
                        Text("Username")
                    },
                )

                TextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = {
                        Text("Password")
                    },
                )

                Spacer(Modifier.size(24.dp))

                Button(
                    onClick = {
                    },
                ) {
                    Text("Login")
                }
            }
        }
    }
}
