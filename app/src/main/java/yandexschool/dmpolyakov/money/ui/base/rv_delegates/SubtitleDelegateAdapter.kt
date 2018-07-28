package yandexschool.dmpolyakov.money.ui.base.rv_delegates

import android.widget.TextView
import com.example.delegateadapter.delegate.KDelegateAdapter
import yandexschool.dmpolyakov.money.R
import yandexschool.dmpolyakov.money.ui.base.rv_delegates.view_models.SubtitleViewModel

class SubtitleDelegateAdapter() : KDelegateAdapter<SubtitleViewModel>() {

    override fun getLayoutId() = R.layout.item_subtitle

    override fun isForViewType(items: MutableList<*>, position: Int): Boolean {
        return items[position] is SubtitleViewModel
    }

    override fun onBind(item: SubtitleViewModel, viewHolder: KViewHolder) {
        val text = viewHolder.itemView.findViewById<TextView>(R.id.text)
        text.text = item.text
    }

}