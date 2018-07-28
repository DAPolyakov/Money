package yandexschool.dmpolyakov.money.models

import yandexschool.dmpolyakov.money.Currency
import java.math.BigDecimal
import java.text.DecimalFormat


data class Account(
        val id: String,
        val title: String,
        val amount: BigDecimal,
        val currency: Currency
) {

    val balance
        get() = DecimalFormat("0.00").format(amount) + " ${currency.sign}"

}