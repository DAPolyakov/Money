package yandexschool.dmpolyakov.money.ui.tracker

import com.arellomobile.mvp.InjectViewState
import yandexschool.dmpolyakov.money.BALANCE_IN_RUBBLES
import yandexschool.dmpolyakov.money.Currency
import yandexschool.dmpolyakov.money.navigation.MainRouter
import yandexschool.dmpolyakov.money.ui.base.mvp.BaseMvpPresenter
import java.math.BigDecimal
import javax.inject.Inject


@InjectViewState
class TrackerPresenter @Inject constructor(router: MainRouter) : BaseMvpPresenter<TrackerView>(router) {
    override fun getScreenTag(): String {
        return "TrackerPresenter"
    }

    override fun attachView(view: TrackerView?) {
        super.attachView(view)
        showBalance()
    }

    private fun showBalance() {
        val balance = getBalanceInRubble()
        viewState?.showBalance(balance, Currency.Rubble)
    }

    private fun getBalanceInRubble(): BigDecimal {
        return BALANCE_IN_RUBBLES
    }

}