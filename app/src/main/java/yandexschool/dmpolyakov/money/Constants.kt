package yandexschool.dmpolyakov.money

import java.math.BigDecimal


val BALANCE_IN_RUBBLES = BigDecimal(15000)
val DOLLAR_TO_RUBBLE = BigDecimal(63.47)

enum class OperationType { Income, Paid }

enum class Currency(val sign: String) {
    Dollar("$"),
    Rubble("\u20BD")
}