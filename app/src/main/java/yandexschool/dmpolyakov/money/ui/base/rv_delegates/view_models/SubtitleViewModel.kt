package yandexschool.dmpolyakov.money.ui.base.rv_delegates.view_models

import com.example.delegateadapter.delegate.diff.IComparableItem


data class SubtitleViewModel(
        val text: String
) : IComparableItem {

    override fun id() = text
    override fun content() = text

}
