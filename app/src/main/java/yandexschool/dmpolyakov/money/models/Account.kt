package yandexschool.dmpolyakov.money.models

import com.example.delegateadapter.delegate.diff.IComparableItem
import yandexschool.dmpolyakov.money.Currency
import java.math.BigDecimal
import java.text.DecimalFormat


data class Account(
        val title: String,
        val amount: BigDecimal,
        val currency: Currency,
        val id: String = ""
) : IComparableItem {

    val balance
        get() = DecimalFormat("0.00").format(amount) + " ${currency.sign}"

    override fun id() = id
    override fun content() = "$title $amount $currency"
}