package yandexschool.dmpolyakov.money.models

import android.os.Parcelable
import com.example.delegateadapter.delegate.diff.IComparableItem
import kotlinx.android.parcel.Parcelize
import yandexschool.dmpolyakov.money.Currency
import java.math.BigDecimal
import java.text.DecimalFormat

@Parcelize
data class Account(
        val title: String,
        val amount: BigDecimal,
        val currency: Currency,
        val operations: List<FinanceOperation> = emptyList(),
        val id: String = ""
) : Parcelable, IComparableItem {

    val balance
        get() = DecimalFormat("0.00").format(amount) + " ${currency.sign}"

    override fun id() = id
    override fun content() = "$title $amount $currency"
}