package yandexschool.dmpolyakov.money.ui.tracker

import yandexschool.dmpolyakov.money.Currency
import yandexschool.dmpolyakov.money.ui.base.mvp.BaseMvpView

interface TrackerView : BaseMvpView {

    fun showBalance(count: Double, currency: Currency = Currency.Rubble)
}