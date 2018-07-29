package yandexschool.dmpolyakov.money.ui.base.rv_delegates

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.example.delegateadapter.delegate.KDelegateAdapter
import kotlinx.android.synthetic.main.item_empty_state.*
import yandexschool.dmpolyakov.money.R
import yandexschool.dmpolyakov.money.ui.base.rv_delegates.view_models.EmptyStateViewModel


class EmptyStateDelegateAdapter() : KDelegateAdapter<EmptyStateViewModel>() {

    private var isVisible = false

    override fun getLayoutId() = R.layout.item_empty_state

    override fun isForViewType(items: MutableList<*>, position: Int): Boolean {
        isVisible = items.size == 1
        return items[position] is EmptyStateViewModel
    }

    override fun onBind(item: EmptyStateViewModel, viewHolder: KViewHolder) = with(viewHolder) {
        description.text = item.description

        if (isVisible) {
            description.visibility = View.VISIBLE
            viewHolder.containerView.layoutParams.height = LinearLayout.LayoutParams.MATCH_PARENT
        } else {
            description.visibility = View.GONE
            viewHolder.containerView.layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
        }
    }

}