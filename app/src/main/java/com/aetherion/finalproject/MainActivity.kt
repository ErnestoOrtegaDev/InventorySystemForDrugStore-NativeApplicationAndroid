package com.aetherion.finalproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.aetherion.finalproject.ui.screens.*
import com.aetherion.finalproject.ui.theme.FinalProjectTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinalProjectTheme {
                var isLoggedIn by rememberSaveable { mutableStateOf(false) }
                var isLoading by remember { mutableStateOf(true) }

                // Loading simulation that triggers whenever isLoading is true
                LaunchedEffect(isLoading) {
                    if (isLoading) {
                        delay(2000)
                        isLoading = false
                    }
                }

                if (isLoading) {
                    LoadingScreen()
                } else {
                    if (!isLoggedIn) {
                        AuthScreen(onLoginSuccess = {
                            isLoggedIn = true
                            isLoading = true // Trigger loading transition to home
                        })
                    } else {
                        MainScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.DASHBOARD) }
    var selectedProduct by remember { mutableStateOf<InventoryProduct?>(null) }
    var editingProduct by remember { mutableStateOf<InventoryProduct?>(null) }
    var isSearching by remember { mutableStateOf(false) }
    var isAddingProduct by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (selectedProduct == null && !isSearching && !isAddingProduct && editingProduct == null) {
                NavigationBar {
                    AppDestinations.entries.forEach { destination ->
                        NavigationBarItem(
                            selected = currentDestination == destination,
                            onClick = { currentDestination = destination },
                            icon = {
                                Icon(
                                    imageVector = destination.icon,
                                    contentDescription = destination.label
                                )
                            },
                            label = { Text(destination.label) }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        if (editingProduct != null) {
            EditProductScreen(
                product = editingProduct!!,
                onBack = { editingProduct = null },
                onProductUpdated = { updated ->
                    val index = inventoryItems.indexOfFirst { it.name == editingProduct?.name }
                    if (index != -1) {
                        inventoryItems[index] = updated
                    }
                    selectedProduct = updated
                    editingProduct = null
                }
            )
        } else if (selectedProduct != null) {
            ProductDetailScreen(
                product = selectedProduct!!,
                onBack = { selectedProduct = null },
                onDelete = { 
                    inventoryItems.remove(selectedProduct)
                    selectedProduct = null 
                },
                onModify = { 
                    editingProduct = selectedProduct
                }
            )
        } else if (isSearching) {
            SearchScreen(
                onBack = { isSearching = false },
                onProductClick = { product -> 
                    selectedProduct = product
                    isSearching = false
                }
            )
        } else if (isAddingProduct) {
            AddProductScreen(
                onBack = { isAddingProduct = false },
                onProductAdded = { product ->
                    inventoryItems.add(product)
                    isAddingProduct = false
                }
            )
        } else {
            when (currentDestination) {
                AppDestinations.DASHBOARD -> HomeScreen(
                    modifier = Modifier.padding(innerPadding),
                    onSearchClick = { isSearching = true },
                    onProductClick = { product -> selectedProduct = product }
                )
                AppDestinations.INVENTARIO -> InventoryScreen(
                    modifier = Modifier.padding(innerPadding),
                    onBack = { currentDestination = AppDestinations.DASHBOARD },
                    onProductClick = { product -> selectedProduct = product },
                    onAddClick = { isAddingProduct = true }
                )
                AppDestinations.NOTIFICACIONES -> NotificationScreen(
                    modifier = Modifier.padding(innerPadding),
                    onBack = { currentDestination = AppDestinations.DASHBOARD }
                )
                AppDestinations.PERFIL -> ProfileScreen(
                    onBack = { currentDestination = AppDestinations.DASHBOARD }
                )
            }
        }
    }
}

@Composable
fun PlaceholderScreen(name: String, modifier: Modifier = Modifier) {
    Surface(modifier = modifier.fillMaxSize()) {
        Text(text = "Pantalla de $name")
    }
}

enum class AppDestinations(
    val label: String,
    val icon: ImageVector,
) {
    DASHBOARD("Home", Icons.Default.Dashboard),
    INVENTARIO("Inventario", Icons.Default.Inventory),
    NOTIFICACIONES("Notificaciones", Icons.Default.Notifications),
    PERFIL("Perfil", Icons.Default.Person),
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FinalProjectTheme {
        MainScreen()
    }
}
