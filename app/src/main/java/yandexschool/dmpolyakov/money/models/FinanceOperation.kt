package yandexschool.dmpolyakov.money.models

import android.os.Parcelable
import com.example.delegateadapter.delegate.diff.IComparableItem
import kotlinx.android.parcel.Parcelize
import yandexschool.dmpolyakov.money.Currency
import yandexschool.dmpolyakov.money.DOLLAR_TO_RUBBLE
import yandexschool.dmpolyakov.money.OperationType
import java.math.BigDecimal

@Parcelize
data class FinanceOperation(
        var title: String,
        val amount: BigDecimal,
        val type: OperationType,
        var currency: Currency,
        var date: String,
        var id: String = ""

) : Parcelable, IComparableItem {

    fun getDifferenceInRubbles(): BigDecimal {
        val rubbles = when (currency) {
            Currency.Dollar -> amount * DOLLAR_TO_RUBBLE
            Currency.Rubble -> amount
        }

        return when (type) {
            OperationType.Income -> rubbles
            OperationType.Paid -> rubbles * BigDecimal(-1)
        }
    }

    override fun id() = id
    override fun content() = "$title$amount$type$currency$date"
}