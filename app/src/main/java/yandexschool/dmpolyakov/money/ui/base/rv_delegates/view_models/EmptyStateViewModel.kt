package yandexschool.dmpolyakov.money.ui.base.rv_delegates.view_models

import com.example.delegateadapter.delegate.diff.IComparableItem


data class EmptyStateViewModel(
        val description: String
) : IComparableItem {

    private val id = System.currentTimeMillis()

    override fun id() = id
    override fun content() = id

}
