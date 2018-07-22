package yandexschool.dmpolyakov.money


const val BALANCE_IN_RUBBLES = 15000.0
const val DOLLAR_TO_RUBBLE = 63.47

enum class OperationType { Income, Paid }

enum class Currency(val sign: String) {
    Dollar("$"),
    Rubble("\u20BD")
}