package com.aetherion.finalproject.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.aetherion.finalproject.R

data class InventoryProduct(
    val name: String,
    val units: Int,
    val expirationRange: String,
    val imageRes: Int,
    val price: String = "$0.00",
    val instructions: String = "No hay instrucciones disponibles.",
    val warnings: String = "No hay advertencias disponibles.",
    val imageUri: String? = null
)

val inventoryItems = mutableStateListOf(
    InventoryProduct(
        "Paracetamol 500mg", 100, "26/01/2026 - 10/02/2027", R.drawable.paracetamol,
        price = "$45.00",
        instructions = "Tomar una tableta cada 8 horas.",
        warnings = "No exceder la dosis recomendada."
    ),
    InventoryProduct(
        "Clorfenamina Compuesta 10t", 50, "27/01/2026 - 11/02/2027", R.drawable.clorferamina,
        price = "$35.00",
        instructions = "Tomar una tableta antes de dormir.",
        warnings = "Puede causar somnolencia."
    ),
    InventoryProduct(
        "Ketorolaco 10 mg", 10, "15/01/2027 - 25/06/2028", R.drawable.ketorolaco,
        price = "$72.50",
        instructions = "Dosis: La que el médico señale. Consérvese la caja bien cerrada. Instrucciones de uso: Se recomienda tomar Ketorolaco antes de los alimentos para que se absorban más rápidamente.",
        warnings = "Su venta requiere receta médica. No se use en el embarazo, ni en la lactancia. No se deje al alcance ni a la vista de los niños. Reporte sospechas de reacción adversa al correo: farmacovigilancia@cofepris.gob.mx"
    ),
    InventoryProduct(
        "Alprazolam 0.25mg", 75, "15/01/2027 - 25/06/2028", R.drawable.armstrong_neupax_duo_comp_025_50mg_c30,
        price = "$120.00",
        instructions = "Dosis según prescripción médica.",
        warnings = "Producto de control médico."
    ),
    InventoryProduct(
        "Ácido acetilsalicílico 300mg", 150, "15/01/2027 - 25/06/2028", R.drawable.acidoacitilsalicitico,
        price = "$25.00",
        instructions = "Disolver en agua.",
        warnings = "No usar en caso de gastritis."
    ),
    InventoryProduct(
        "Benzonatato 100mg", 160, "15/01/2027 - 25/06/2028", R.drawable.benzonatato,
        price = "$55.00",
        instructions = "Tomar una perla cada 8 horas.",
        warnings = "No masticar la perla."
    ),
    InventoryProduct(
        "Metamizol Sodico 1g", 150, "15/01/2027 - 25/06/2028", R.drawable.metamizolsodico,
        price = "$40.00",
        instructions = "Tomar en caso de fiebre alta.",
        warnings = "No usar por periodos prolongados."
    ),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryScreen(
    modifier: Modifier = Modifier, 
    onBack: () -> Unit = {},
    onProductClick: (InventoryProduct) -> Unit = {},
    onAddClick: () -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Inventario", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            Button(
                onClick = onAddClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB03060)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .height(50.dp)
                    .fillMaxWidth(0.7f)
            ) {
                Text("Añadir Medicamento", color = Color.White, fontWeight = FontWeight.Bold)
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.White),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            items(inventoryItems) { product ->
                InventoryItemRow(product, onClick = { onProductClick(product) })
                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), color = Color.LightGray.copy(alpha = 0.3f))
            }
        }
    }
}

@Composable
fun InventoryItemRow(product: InventoryProduct, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = product.name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.DarkGray
            )
            Text(
                text = "${product.units} unidades",
                fontSize = 12.sp,
                color = Color.Gray
            )
            Text(
                text = "F/C: ${product.expirationRange}",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
        
        Spacer(modifier = Modifier.width(8.dp))

        // Image Handling (URI or Resource)
        if (product.imageUri != null) {
            AsyncImage(
                model = product.imageUri,
                contentDescription = product.name,
                modifier = Modifier
                    .size(70.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        } else {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.name,
                modifier = Modifier
                    .size(70.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InventoryScreenPreview() {
    InventoryScreen()
}
