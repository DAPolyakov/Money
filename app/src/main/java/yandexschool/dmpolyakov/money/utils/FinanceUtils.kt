package yandexschool.dmpolyakov.money.utils

import yandexschool.dmpolyakov.money.Currency
import yandexschool.dmpolyakov.money.DOLLAR_TO_RUBBLE
import yandexschool.dmpolyakov.money.models.FinanceOperation
import java.math.BigDecimal


fun sumFinanceOperations(operations: List<FinanceOperation>, resultCurrency: Currency = Currency.Rubble): BigDecimal {
    var sumInRubbles = BigDecimal(0)

    for (operation in operations) {
        sumInRubbles += operation.getDifferenceInRubbles()
    }

    return when (resultCurrency) {
        Currency.Dollar -> sumInRubbles.toDollars(Currency.Rubble)
        Currency.Rubble -> sumInRubbles.toRubbles(Currency.Rubble)
    }
}

fun BigDecimal.toRubbles(sourceCurrency: Currency): BigDecimal {
    return when (sourceCurrency) {
        Currency.Rubble -> this
        Currency.Dollar -> this.multiply(DOLLAR_TO_RUBBLE.toBigDecimal())
    }
}

fun BigDecimal.toDollars(sourceCurrency: Currency): BigDecimal {
    return when (sourceCurrency) {
        Currency.Rubble -> this.divide(DOLLAR_TO_RUBBLE.toBigDecimal(), 2, BigDecimal.ROUND_HALF_UP)
        Currency.Dollar -> this
    }
}

fun Int.toDollars(sourceCurrency: Currency) = this.toBigDecimal().toDollars(sourceCurrency)
fun Int.toRubbles(sourceCurrency: Currency) = this.toBigDecimal().toRubbles(sourceCurrency)