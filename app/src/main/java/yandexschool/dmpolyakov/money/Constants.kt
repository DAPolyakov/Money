package yandexschool.dmpolyakov.money

import java.math.BigDecimal


val BALANCE_IN_RUBBLES = BigDecimal(15000)
val DOLLAR_TO_RUBBLE = BigDecimal(63.47)

val ACCOUNT_AMOUNT_MAX_LENGTH = 15

enum class OperationType { Income, Paid }

enum class Currency(val sign: String, val shortTitle: String) {
    Rubble("\u20BD", "RUB"),
    Dollar("$", "USD")
}