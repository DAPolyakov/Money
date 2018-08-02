package yandexschool.dmpolyakov.money.ui.tracker

import com.arellomobile.mvp.InjectViewState
import yandexschool.dmpolyakov.money.models.Account
import yandexschool.dmpolyakov.money.navigation.MainRouter
import yandexschool.dmpolyakov.money.repository.AccountRepository
import yandexschool.dmpolyakov.money.ui.base.mvp.BaseMvpPresenter
import javax.inject.Inject


@InjectViewState
class TrackerPresenter @Inject constructor(
        val router: MainRouter,
        val accountRep: AccountRepository) : BaseMvpPresenter<TrackerView>(router) {

    override fun getScreenTag(): String {
        return "TrackerPresenter"
    }

    override fun attachView(view: TrackerView?) {
        super.attachView(view)
        updateAccounts()
    }

    fun addAccount(account: Account) {
        bind(accountRep.addAccount(account).subscribe({
            updateAccounts()
        }, {
            viewState.showError(it)
        }))
    }

    fun onAccountClick(account: Account) {
        router.showAccountScreen(account.id)
    }

    private fun updateAccounts() {
        bind(onUi(accountRep.getAccounts()).subscribe({
            viewState.showAccounts(it)
        }, {
            viewState.showError(it)
        }))
    }

}