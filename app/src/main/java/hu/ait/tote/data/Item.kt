package hu.ait.tote.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "category") val category: Category,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "price") val price: Long,
    @ColumnInfo(name = "currency") val currency: Currency = Currency.USD,
    @ColumnInfo(name = "quantity") val quantity: Int,
    @ColumnInfo(name = "status") val status: Boolean
) {
    init {
        require(price > 0) {
            "Amount must be greater than zero"
        }

        require(quantity > 0) {
            "quantity must be greater than zero"
        }

    }
}