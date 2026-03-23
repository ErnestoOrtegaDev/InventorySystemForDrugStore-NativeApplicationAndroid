package com.aetherion.finalproject.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aetherion.finalproject.R
import com.aetherion.finalproject.ui.theme.FinalProjectTheme

@Composable
fun AuthScreen(onLoginSuccess: () -> Unit, initialIsLogin: Boolean = true) {
    var isLogin by remember { mutableStateOf(initialIsLogin) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
    ) {
        // Header with Real Background and Logo
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            // Background Image from drawable
            // Note: If R.drawable.fondo_login causes a Render Problem, 
            // ensure the project is built and the resource exists in res/drawable.
            Image(
                painter = painterResource(id = R.drawable.fondo_login),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            
            // Dark overlay for readability
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.2f))
            )

            // Logo Overlay using farmalogo.png
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(140.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.farmalogo),
                    contentDescription = "FarmaVida Logo",
                    modifier = Modifier.size(180.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Toggle Switcher (Login / Register)
        Row(
            modifier = Modifier
                .padding(horizontal = 40.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(50.dp))
                .background(Color(0xFFF2F2F2)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(50.dp))
                    .background(if (isLogin) Color(0xFFB03060) else Color.Transparent)
                    .clickable { isLogin = true }
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Iniciar sesión",
                    color = if (isLogin) Color.White else Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(50.dp))
                    .background(if (!isLogin) Color(0xFFB03060) else Color.Transparent)
                    .clickable { isLogin = false }
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Registrarse",
                    color = if (!isLogin) Color.White else Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Form Fields
        Column(
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .fillMaxWidth()
        ) {
            if (isLogin) {
                LoginForm(onLoginSuccess)
            } else {
                RegisterForm(onLoginSuccess)
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun LoginForm(onLoginSuccess: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        AuthField(label = "Email", icon = Icons.Default.Email)
        Spacer(modifier = Modifier.height(16.dp))
        AuthField(label = "Contraseña", icon = Icons.Default.Lock, isPassword = true)
        
        Text(
            text = "¿Olvidaste tu contraseña?",
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 8.dp)
                .clickable { /* TODO */ },
            color = Color(0xFF00BCD4),
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(32.dp))
        
        Button(
            onClick = onLoginSuccess,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB03060)),
            shape = RoundedCornerShape(50.dp)
        ) {
            Text("Iniciar sesión", modifier = Modifier.padding(vertical = 8.dp), fontSize = 16.sp)
        }
    }
}

@Composable
private fun RegisterForm(onLoginSuccess: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        AuthField(label = "Nombre", icon = Icons.Default.Person)
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f)) {
                AuthField(label = "Apellido Paterno")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box(modifier = Modifier.weight(1f)) {
                AuthField(label = "Apellido Materno")
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        AuthField(label = "Correo electrónico", icon = Icons.Default.Email)
        Spacer(modifier = Modifier.height(16.dp))
        AuthField(label = "Número de teléfono", icon = Icons.Default.Phone)
        Spacer(modifier = Modifier.height(16.dp))
        AuthField(label = "Contraseña", icon = Icons.Default.Lock, isPassword = true)

        Spacer(modifier = Modifier.height(32.dp))
        
        Button(
            onClick = onLoginSuccess,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB03060)),
            shape = RoundedCornerShape(50.dp)
        ) {
            Text("Registrarse", modifier = Modifier.padding(vertical = 8.dp), fontSize = 16.sp)
        }
    }
}

@Composable
private fun AuthField(label: String, icon: ImageVector? = null, isPassword: Boolean = false) {
    Column {
        Text(
            text = label, 
            fontWeight = FontWeight.SemiBold, 
            modifier = Modifier.padding(bottom = 4.dp),
            fontSize = 14.sp
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            leadingIcon = icon?.let { { Icon(imageVector = it, contentDescription = null, tint = Color.Gray) } },
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF8E8E8).copy(alpha = 0.5f),
                unfocusedContainerColor = Color(0xFFF8E8E8).copy(alpha = 0.5f),
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
            )
        )
    }
}

@Preview(showBackground = true, name = "Login View")
@Composable
fun AuthScreenLoginPreview() {
    FinalProjectTheme {
        AuthScreen(onLoginSuccess = {}, initialIsLogin = true)
    }
}

@Preview(showBackground = true, name = "Register View")
@Composable
fun AuthScreenRegisterPreview() {
    FinalProjectTheme {
        AuthScreen(onLoginSuccess = {}, initialIsLogin = false)
    }
}
