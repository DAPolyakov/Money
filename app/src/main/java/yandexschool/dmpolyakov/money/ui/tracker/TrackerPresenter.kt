package yandexschool.dmpolyakov.money.ui.tracker

import yandexschool.dmpolyakov.money.BALANCE_IN_RUBBLES
import yandexschool.dmpolyakov.money.Currency
import yandexschool.dmpolyakov.money.mvp.BasePresenter


class TrackerPresenter : BasePresenter<TrackerView>() {

    override fun viewIsReady() {
        showBalance()
    }

    private fun showBalance() {
        val balance = getBalanceInRubble()
        getView()?.showBalance(balance, Currency.Rubble)
    }

    private fun getBalanceInRubble(): Double {
        return BALANCE_IN_RUBBLES
    }

}