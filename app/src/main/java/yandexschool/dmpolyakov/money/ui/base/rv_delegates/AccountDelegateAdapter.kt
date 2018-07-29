package yandexschool.dmpolyakov.money.ui.base.rv_delegates

import com.example.delegateadapter.delegate.KDelegateAdapter
import kotlinx.android.synthetic.main.item_account.*
import yandexschool.dmpolyakov.money.R
import yandexschool.dmpolyakov.money.models.Account
import yandexschool.dmpolyakov.money.utils.getCompatColor

class AccountDelegateAdapter() : KDelegateAdapter<Account>() {

    override fun getLayoutId() = R.layout.item_account

    override fun isForViewType(items: MutableList<*>, position: Int) = items[position] is Account

    override fun onBind(item: Account, viewHolder: KViewHolder) = with(viewHolder) {
        title.text = item.title
        balance.text = item.balance

        balance.setTextColor(viewHolder.itemView.context.getCompatColor(
                when {
                    item.amount > 0.toBigDecimal() -> R.color.saturated_green
                    item.amount < 0.toBigDecimal() -> R.color.dark_scarlet
                    else -> R.color.silver_82
                }
        ))

    }

}