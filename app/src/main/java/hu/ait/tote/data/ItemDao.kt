package hu.ait.tote.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface ItemDao {


    @Query("SELECT * FROM items")
    fun getAllItems(): Flow<List<Item>>

    @Query("SELECT COUNT(*) FROM items")
    fun getNumberOfItems(): Flow<Int>

    @Query("SELECT * FROM items WHERE ID = :id")
    fun getItem(id: Int): Flow<Item>


    @Insert
    suspend fun addItem(todoItem: Item)

    @Update
    suspend fun updateItem(todoItem: Item)

    @Delete
    suspend fun deleteItem(todoItem: Item)

    @Query("DELETE FROM items WHERE category = :category")
    suspend fun deleteByCategory(category: Category)

    @Query("DELETE FROM items")
    suspend fun deleteAllItems()

}