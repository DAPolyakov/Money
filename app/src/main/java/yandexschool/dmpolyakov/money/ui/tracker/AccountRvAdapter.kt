package yandexschool.dmpolyakov.money.ui.tracker

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import yandexschool.dmpolyakov.money.R
import yandexschool.dmpolyakov.money.models.Account
import yandexschool.dmpolyakov.money.utils.getCompatColor


class AccountRvAdapter : RecyclerView.Adapter<AccountRvAdapter.ViewHolder>() {

    private var data: List<Account> = emptyList()

    fun swapData(newData: List<Account>) {
        data = newData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_account, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.fill(data[position])
    }

    override fun getItemCount() = data.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val title: TextView = view.findViewById(R.id.title)
        val balance: TextView = view.findViewById(R.id.balance)

        fun fill(item: Account) {
            title.text = item.title
            balance.text = item.balance

            balance.setTextColor(view.context.getCompatColor(
                    when {
                        item.amount > 0.toBigDecimal() -> R.color.saturated_green
                        item.amount < 0.toBigDecimal() -> R.color.dark_scarlet
                        else -> R.color.silver_82
                    }
            ))

        }

    }
}