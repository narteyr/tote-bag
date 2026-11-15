package hu.ait.tote.screen.shoppinglist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import hu.ait.tote.components.ItemCard
import hu.ait.tote.components.ItemDialog
import hu.ait.tote.components.NewItemFab
import hu.ait.tote.components.ToteTopAppBar
import hu.ait.tote.data.Category

import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hu.ait.tote.R
import hu.ait.tote.components.DeleteIconButton
import hu.ait.tote.components.toPriceString
import hu.ait.tote.data.Currency
import hu.ait.tote.ui.theme.ToteTheme
import kotlin.math.max

@Composable
fun ShoppingListScreen( toteViewModel: ShopListViewModel = hiltViewModel()) {

    var showDialog by rememberSaveable {mutableStateOf(false)}

    val items by toteViewModel.items.collectAsState(initial = emptyList())
    val countsByCategory: Map<Category, Int> = items.groupingBy {it.category}.eachCount()
    val totalPriceByCategory: Map<Category, Long> =
        items.groupingBy { it.category }
            .fold(0L) {total , item -> total + item.price * item.quantity}

    var itemToEditId by rememberSaveable { mutableIntStateOf(0) }
    val itemToEdit = items.find {it.id == itemToEditId}

    Scaffold(
        topBar = {
                 Column() {
                     ToteTopAppBar{toteViewModel.deleteAllItems()}
                 } },
        floatingActionButton = { NewItemFab { itemToEditId = 0; showDialog = true } }

    )
    { innerPadding ->

            LazyColumn(
                modifier = Modifier.padding(innerPadding)
                    .padding(top = 8.dp),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 88.dp
                )
            ) {
                items(Category.entries) {
                    category ->


                    if (items.any{ item -> item.category == category}) {
                        CategoryHeadlineCard(
                            category = category,
                            currency = Currency.USD,
                            totalPrice = totalPriceByCategory.getValue(category),
                            quantityLeft = countsByCategory.getValue(category),
                            onDeleteAllItemsByCategory = {toteViewModel.deleteByCategory(category)}
                        )
                    }


                    Column(
                        modifier = Modifier.padding(bottom = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items.filter({item -> item.category == category}).forEach { item ->
                            ItemCard(
                                item,
                                onEditItem = {
                                        clicked -> itemToEditId = clicked.id
                                    showDialog = true },
                                onCheckItem = {
                                    toteViewModel.updateItem(item.copy(status = it))

                                },
                                onDeleteItem = {toteViewModel.deleteItem(it)},
                                onQuantityChange = {
                                    toteViewModel.updateItem(item.copy(quantity = max(1, it)))
                                }
                            )
                        }
                    }

                }
            }

            //who dialog
            if (showDialog) {
                ItemDialog(
                    itemToEdit,
                    onDismiss = { showDialog = false},
                ) {
                    if (itemToEdit == null) toteViewModel.saveItem(it)
                    else toteViewModel.updateItem(it)

                    showDialog = false
                }
            }


            if (items.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painterResource(R.drawable.img_empty_tote),
                            contentDescription = "empty tote bag"
                        )
                        Text("Tote is Empty", style = MaterialTheme.typography.titleMedium)
                    }
                }
            }
        }

}



@Composable
fun CategoryHeadlineCard(
    category: Category,
    quantityLeft: Int,
    currency: Currency,
    totalPrice: Long,
    onDeleteAllItemsByCategory: (Category) -> Unit
){

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                 painterResource(category.iconRes),
                contentDescription = category.name,
                modifier = Modifier.size(36.dp)
            )

            Text(category.name.lowercase().replaceFirstChar { it.uppercase() }, style = MaterialTheme.typography.headlineSmall)
            Text(stringResource(R.string.left, quantityLeft))
        }
        Spacer(
            modifier = Modifier
                .weight(1f)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(totalPrice.toPriceString(currency), style = MaterialTheme.typography.labelSmall)
            DeleteIconButton { onDeleteAllItemsByCategory(category) }
        }
    }
}

