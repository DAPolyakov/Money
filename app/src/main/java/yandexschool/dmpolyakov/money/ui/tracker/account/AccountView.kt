package yandexschool.dmpolyakov.money.ui.tracker.account

import yandexschool.dmpolyakov.money.models.Account
import yandexschool.dmpolyakov.money.ui.base.mvp.BaseMvpView


interface AccountView : BaseMvpView {

    fun showTitle(title: String)
    fun showBalance(balance: String)
    fun showTabs(account: Account)

}