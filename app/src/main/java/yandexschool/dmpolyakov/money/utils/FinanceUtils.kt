package yandexschool.dmpolyakov.money.utils

import yandexschool.dmpolyakov.money.Currency
import yandexschool.dmpolyakov.money.DOLLAR_TO_RUBBLE
import yandexschool.dmpolyakov.money.models.FinanceOperation


fun sumFinanceOperations(operations: List<FinanceOperation>, resultCurrency: Currency = Currency.Rubble): Double {
    var sumInRubbles = 0.0

    for (operation in operations) {
        sumInRubbles += operation.getDifferenceInRubbles()
    }

    return when (resultCurrency) {
        Currency.Dollar -> sumInRubbles.toDollars(Currency.Rubble)
        Currency.Rubble -> sumInRubbles.toRubbles(Currency.Rubble)
    }
}

fun Double.toRubbles(sourceCurrency: Currency): Double {
    return when (sourceCurrency) {
        Currency.Rubble -> this
        Currency.Dollar -> this * DOLLAR_TO_RUBBLE
    }
}

fun Double.toDollars(sourceCurrency: Currency): Double {
    return when (sourceCurrency) {
        Currency.Rubble -> this / DOLLAR_TO_RUBBLE
        Currency.Dollar -> this
    }
}

fun Int.toDollars(sourceCurrency: Currency): Double = this.toDouble().toDollars(sourceCurrency)
fun Int.toRubbles(sourceCurrency: Currency): Double = this.toDouble().toRubbles(sourceCurrency)