package yandexschool.dmpolyakov.money

import yandexschool.dmpolyakov.money.OperationCategory.*

var DOLLAR_TO_RUBBLE: Double = 63.47

enum class Currency(val sign: String, val shortTitle: String) {
    Rubble("\u20BD", "RUB"),
    Dollar("$", "USD")
}

enum class OperationCategory(val icon: Int, val title: Int) {
    Salary(R.drawable.ic_salary, R.string.category_salary),
    Gift(R.drawable.ic_gift, R.string.category_gift),
    Other(R.drawable.ic_category_other, R.string.category_other),

    Transport(R.drawable.ic_transport, R.string.category_transport),
    Products(R.drawable.ic_products, R.string.category_products),
    Health(R.drawable.ic_health, R.string.category_health),
    Education(R.drawable.ic_education, R.string.category_education),
}

enum class OperationType { Income, Expense;

    fun getCategories(): List<OperationCategory> {
        return when (this) {
            OperationType.Income -> arrayListOf(Salary, Gift, Other)
            OperationType.Expense -> arrayListOf(Transport, Products, Health, Education)
        }
    }
}
