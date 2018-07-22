package yandexschool.dmpolyakov.money.ui.tracker

import yandexschool.dmpolyakov.money.Currency
import yandexschool.dmpolyakov.money.mvp.MvpView


interface TrackerView : MvpView {

    fun showBalance(count: Double, currency: Currency = Currency.Rubble)
}