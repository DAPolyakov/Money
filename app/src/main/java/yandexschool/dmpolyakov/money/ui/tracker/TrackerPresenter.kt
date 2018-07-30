package yandexschool.dmpolyakov.money.ui.tracker

import com.arellomobile.mvp.InjectViewState
import yandexschool.dmpolyakov.money.models.Account
import yandexschool.dmpolyakov.money.navigation.MainRouter
import yandexschool.dmpolyakov.money.repositories.AccountRepository
import yandexschool.dmpolyakov.money.ui.base.mvp.BaseMvpPresenter
import javax.inject.Inject


@InjectViewState
class TrackerPresenter @Inject constructor(private val router: MainRouter,
                                           private val accountRep: AccountRepository) : BaseMvpPresenter<TrackerView>(router) {

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
            // TODO
        }))
    }

    fun onAccountClick(account: Account) {
        router.showAccountScreen(account.id)
    }

    private fun updateAccounts() {
        bind(onUi(accountRep.getAccounts()).subscribe({
            viewState.showAccounts(it)
        }, {
            // TODO
        }))
    }
    
}