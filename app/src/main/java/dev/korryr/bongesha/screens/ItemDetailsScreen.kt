package dev.korryr.bongesha.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dev.korryr.bongesha.viewmodels.CartItemViewModel

@Composable
fun ItemDetailsScreen(
    itemId: String,
    navController: NavController,
    cartItemViewModel: CartItemViewModel = viewModel()
) {
    val selectedItem by cartItemViewModel.selectedItem.collectAsState()
    var quantity by remember { mutableStateOf(1) }
    var isFavorite by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(itemId) {
        cartItemViewModel.selectItem(itemId)
    }

    selectedItem?.let { item ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .clickable {
                            // Handle image click if needed
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = item.image),
                        contentDescription = item.name,
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = item.name,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Ksh ${item.price}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Green
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        if (quantity > 1) quantity--
                    }) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = "Remove")
                    }
                    Text(text = "$quantity")
                    IconButton(onClick = { quantity++ }) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                    }
                }

                Button(onClick = {
                    cartItemViewModel.addToCart(item, quantity)
                    // Show a toast or snackbar
                }) {
                    Text("Add to Cart")
                }
            }

            IconButton(
                onClick = {
                    isFavorite = !isFavorite
                    // Add to favorite list in your ViewModel
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite"
                )
            }
        }
    } /*?: run {
        // Show a loading or error message if selectedItem is null
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Loading item details...", style = MaterialTheme.typography.bodyLarge)
        }
    }
}
}*/
}
