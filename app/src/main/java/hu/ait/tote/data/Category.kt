package hu.ait.tote.data

import androidx.annotation.DrawableRes
import hu.ait.tote.R

enum class Category(@DrawableRes val iconRes: Int) {
    FOOD(R.drawable.ic_category_food),
    ELECTRONICS(R.drawable.ic_category_electronics),
    BOOKS(R.drawable.ic_category_books),
    HOME(R.drawable.ic_category_home),
    HEALTH(R.drawable.ic_category_health)
}