package yandexschool.dmpolyakov.money.models

import yandexschool.dmpolyakov.money.Currency
import yandexschool.dmpolyakov.money.DOLLAR_TO_RUBBLE
import yandexschool.dmpolyakov.money.OperationType

data class FinanceOperation(
        val type: OperationType,
        val count: Double,
        var currency: Currency

) {

    fun getDifferenceInRubbles(): Double {
        val rubbles = when (currency) {
            Currency.Dollar -> count * DOLLAR_TO_RUBBLE
            Currency.Rubble -> count
        }

        return when (type) {
            OperationType.Income -> rubbles
            OperationType.Paid -> rubbles * -1
        }
    }
}