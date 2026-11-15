package hu.ait.tote.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import hu.ait.tote.data.Category
import hu.ait.tote.data.Item
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import hu.ait.tote.R
import hu.ait.tote.data.Currency
import hu.ait.tote.components.toPriceString
import hu.ait.tote.components.toCentsOrNull
import kotlin.math.max


@Composable
fun ItemDialog(
    item: Item?,
    onDismiss: () -> Unit,
    onSave: (Item) -> Unit
) {

    var category by rememberSaveable {
        mutableStateOf(item?.category ?: Category.FOOD)
    }

    var name by rememberSaveable {
        mutableStateOf(item?.name ?: "")
    }

    var description by rememberSaveable {
        mutableStateOf(item?.description ?: "")
    }

    var price by rememberSaveable {
        mutableStateOf(item?.price?.toBigDecimal()
            ?.movePointLeft(2)?.toPlainString() ?: "")
    }

    var quantity by rememberSaveable {
        mutableIntStateOf(item?.quantity ?: 1)
    }

    val nameError = name.isBlank()
    val priceError = price.toCentsOrNull() == null
    val priceErrorMessage = priceErrorMessage(price)

    var showErrors by rememberSaveable {mutableStateOf(false)}

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            if (item == null) Text(stringResource(R.string.new_item))
            else Text(stringResource(R.string.edit_item))
                },
        text = {

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {


                OutlinedTextField(
                    label = {Text("Name")},
                    value = name,
                    maxLines = 1,
                    isError= nameError && showErrors,
                    supportingText = {if (showErrors && nameError) { Text("name is required") } },
                    onValueChange = { name = it}
                )

                OutlinedTextField(
                    label = {Text("Description")},
                    value = description,
                    maxLines = 1,
                    onValueChange = {description = it}
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        value = price,
                        label = {Text("price")},
                        onValueChange = {price = it},
                        isError = priceError && showErrors,
                        supportingText = if(showErrors && priceErrorMessage != null) {{ Text(priceErrorMessage)}} else null,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
                    )

                    Stepper(
                        quantity = quantity,
                    ) { quantity = max(1, it) }
                }

                CategoryDropdown(
                    selected = category,
                    onSelectionChanged = { category = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },


        confirmButton  = {
            Button(
                onClick = {
                    showErrors = nameError || priceError
                    if(nameError || priceError) return@Button

                    val cents = price.toCentsOrNull() ?: return@Button

                    val newItem = Item(
                        id = item?.id ?: 0,
                        name = name.trim(),
                        category = category,
                        description = description,
                        price = cents,
                        currency = item?.currency ?: Currency.USD,
                        quantity = quantity,
                        status = false
                    )

                    onSave(newItem)
                }
            ) {
                if (item == null) Text(stringResource(R.string.save))
                else Text(stringResource(R.string.update))
            } },
        dismissButton = {
            Button(
                onClick = {onDismiss()}
            ) {
                Text(stringResource(R.string.cancel))
            }
        }
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDropdown(
    selected: Category,
    onSelectionChanged: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = selected.name.lowercase().replaceFirstChar { it.uppercase() },
            onValueChange = {},
            readOnly = true,
            label = { Text("Category") },
            leadingIcon = {
                Image(
                    painterResource(selected.iconRes),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            Category.entries.forEach { category ->
                DropdownMenuItem(
                    text = { Text(category.name.lowercase().replaceFirstChar { it.uppercase() }) },
                    leadingIcon = {
                        Image(
                            painterResource(category.iconRes),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    onClick = {
                        onSelectionChanged(category)
                        expanded = false
                    }
                )
            }
        }
    }
}