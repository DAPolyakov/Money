package yandexschool.dmpolyakov.money.ui.tracker.account.settings

import com.arellomobile.mvp.InjectViewState
import yandexschool.dmpolyakov.money.models.Account
import yandexschool.dmpolyakov.money.navigation.MainRouter
import yandexschool.dmpolyakov.money.repositories.AccountRepository
import yandexschool.dmpolyakov.money.ui.base.mvp.BaseMvpPresenter
import javax.inject.Inject


@InjectViewState
class AccountSettingsPresenter @Inject constructor(router: MainRouter, val accountRep: AccountRepository)
    : BaseMvpPresenter<AccountSettingsView>(router) {

    override fun getScreenTag() = "OperationsPresenter"

    private lateinit var account: Account

    fun rename(title: String) {
        bind((accountRep.rename(account.id, title)
                .subscribe({
                }, {
                    viewState.showError(it)
                }))
        )
    }

    fun loadAccount(account: Account) {
        this.account = account
    }

}