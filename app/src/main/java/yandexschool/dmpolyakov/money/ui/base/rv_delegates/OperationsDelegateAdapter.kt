package yandexschool.dmpolyakov.money.ui.base.rv_delegates

import com.example.delegateadapter.delegate.KDelegateAdapter
import kotlinx.android.synthetic.main.item_operation.*
import yandexschool.dmpolyakov.money.OperationType
import yandexschool.dmpolyakov.money.R
import yandexschool.dmpolyakov.money.models.FinanceOperation
import yandexschool.dmpolyakov.money.utils.getCompatColor


class OperationsDelegateAdapter : KDelegateAdapter<FinanceOperation>() {

    override fun getLayoutId() = R.layout.item_operation

    override fun isForViewType(items: MutableList<*>, position: Int) = items[position] is FinanceOperation

    override fun onBind(item: FinanceOperation, viewHolder: KViewHolder) = with(viewHolder) {

        date.text = item.date
        title.text = item.title

        val s = "${item.amount} ${item.currency.sign}"
        amount.text = s

        amount.setTextColor(viewHolder.itemView.context.getCompatColor(
                when (item.type) {
                    OperationType.Income -> R.color.saturated_green
                    OperationType.Paid -> R.color.dark_scarlet
                }
        ))

    }

}