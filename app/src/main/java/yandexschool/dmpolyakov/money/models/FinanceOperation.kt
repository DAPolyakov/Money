package yandexschool.dmpolyakov.money.models

import yandexschool.dmpolyakov.money.Currency
import yandexschool.dmpolyakov.money.DOLLAR_TO_RUBBLE
import yandexschool.dmpolyakov.money.OperationType
import java.math.BigDecimal

data class FinanceOperation(
        val type: OperationType,
        val count: BigDecimal,
        var currency: Currency

) {

    fun getDifferenceInRubbles(): BigDecimal {
        val rubbles = when (currency) {
            Currency.Dollar -> count * DOLLAR_TO_RUBBLE
            Currency.Rubble -> count
        }

        return when (type) {
            OperationType.Income -> rubbles
            OperationType.Paid -> rubbles * BigDecimal(-1)
        }
    }

}