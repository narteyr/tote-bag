package hu.ait.tote.components

import hu.ait.tote.data.Currency
import java.math.BigDecimal


fun String.toCentsOrNull(): Long? {
    val amount = trim().replace(',', '.').toBigDecimalOrNull() ?: return null
    if(amount <= BigDecimal.ZERO) return null
    if (amount.stripTrailingZeros().scale() > 2) return null
    return amount.movePointRight(2).toLong()
}


fun Long.toPriceString(currency: Currency): String {
    return when(currency) {
        Currency.USD -> "${currency.symbol} ${this.toBigDecimal()
            .movePointLeft(2).toPlainString()}"

        Currency.FT -> "${currency.symbol} ${this.toBigDecimal()
            .movePointLeft(2).toPlainString()
            .replace(".", ",")}"

        Currency.GHS -> "${currency.symbol} ${this.toBigDecimal()
            .movePointLeft(2).toPlainString()}"
    }
}


fun priceErrorMessage(input: String): String? {
    val text = input.trim().replace(',', '.')
    if (text.isEmpty()) return "Price is required"
    val amount = text.toBigDecimalOrNull() ?: return "Enter a valid number, e.g. 12.50"
    if (amount <= BigDecimal.ZERO) return "Price must be greater than 0"
    if (amount.stripTrailingZeros().scale() > 2) return "Use at most 2 decimal places"
    return null
}