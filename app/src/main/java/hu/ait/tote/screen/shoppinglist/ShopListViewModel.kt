package hu.ait.tote.screen.shoppinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.ait.tote.data.Category
import hu.ait.tote.data.Item
import hu.ait.tote.data.ItemDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ShopListViewModel @Inject constructor(
    private val itemDao: ItemDao
): ViewModel()  {

    val items: Flow<List<Item>> = itemDao.getAllItems()

    fun saveItem(item: Item){
        viewModelScope.launch {
            itemDao.addItem(item)
        }
    }

    fun updateItem(item: Item) {
        viewModelScope.launch {
            itemDao.updateItem(item)
        }
    }


    fun deleteItem(item: Item) {
        viewModelScope.launch {
            itemDao.deleteItem(item)
        }
    }

    fun deleteByCategory(category: Category) {
        viewModelScope.launch {
            itemDao.deleteByCategory(category)
        }
    }

    fun deleteAllItems() {
        viewModelScope.launch {
            itemDao.deleteAllItems()
        }
    }
}