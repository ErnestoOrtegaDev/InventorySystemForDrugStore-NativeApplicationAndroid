package com.aetherion.finalproject.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aetherion.finalproject.R

private data class NotificationItem(
    val title: String,
    val description: String,
    val date: String,
    val icon: ImageVector,
    val iconColor: Color = Color(0xFFB03060)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(modifier: Modifier = Modifier, onBack: () -> Unit = {}) {
    val notifications = listOf(
        NotificationItem(
            title = "Estado del medicamento",
            description = "💊 Paracetamol 500 mg disponible en sucursal.\n📦 Amoxicilina 500 mg ya está en stock.\n❌ El medicamento Metformina 850 mg no está disponible por el momento.",
            date = "26/01/26",
            icon = Icons.Default.Person
        ),
        NotificationItem(
            title = "Pedido del medicamento",
            description = "🧾 Pedido del medicamento confirmado.\n🚚 Envío del medicamento en camino.\n📍 Medicamento listo para recogerse.",
            date = "26/01/26",
            icon = Icons.Default.Share
        ),
        NotificationItem(
            title = "Tu medicamento está por agotarse ⚠️ Compra con anticipación.",
            description = "Atención: has omitido una dosis hoy.",
            date = "26/01/26",
            icon = Icons.Default.Link
        ),
        NotificationItem(
            title = "Alertas del medicamento",
            description = "⚠️ Medicamento próximo a caducar.\n❗ Cambio en la disponibilidad del medicamento.\n🚫 Medicamento temporalmente suspendido.",
            date = "27/01/26",
            icon = Icons.Default.ExitToApp
        )
    )

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(
                            "Buzón de Avisos",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.DarkGray
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    Box(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFB03060)),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.farmalogo),
                            contentDescription = "Logo",
                            modifier = Modifier.size(20.dp),
                            contentScale = ContentScale.Fit
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.White)
        ) {
            // Filter Tabs
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterTab("Nuevos(3)", isSelected = true, modifier = Modifier.weight(1f))
                FilterTab("Personales", isSelected = false, modifier = Modifier.weight(1f))
                FilterTab("Favoritos", isSelected = false, modifier = Modifier.weight(1f))
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                items(notifications) { notification ->
                    NotificationRow(notification)
                }
            }
        }
    }
}

@Composable
private fun FilterTab(text: String, isSelected: Boolean, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        color = if (isSelected) Color(0xFFD97D7D) else Color(0xFFE59A9A).copy(alpha = 0.6f)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(vertical = 8.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = if (isSelected) Color.White else Color.Black,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }
}

@Composable
private fun NotificationRow(notification: NotificationItem) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = notification.date,
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.End)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = notification.icon,
                contentDescription = null,
                tint = notification.iconColor,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .size(32.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = notification.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = notification.description,
                    fontSize = 13.sp,
                    color = Color.Gray,
                    lineHeight = 18.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationScreenPreview() {
    NotificationScreen()
}
