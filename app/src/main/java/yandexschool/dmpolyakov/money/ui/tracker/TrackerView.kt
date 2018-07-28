package yandexschool.dmpolyakov.money.ui.tracker

import yandexschool.dmpolyakov.money.Currency
import yandexschool.dmpolyakov.money.models.Account
import yandexschool.dmpolyakov.money.ui.base.mvp.BaseMvpView
import java.math.BigDecimal

interface TrackerView : BaseMvpView {

    fun showBalance(count: BigDecimal, currency: Currency = Currency.Rubble)
    fun showAccounts(accounts: List<Account>)
    fun showDialog()
}