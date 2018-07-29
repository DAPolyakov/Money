package yandexschool.dmpolyakov.money.ui.tracker.account

import com.arellomobile.mvp.InjectViewState
import yandexschool.dmpolyakov.money.navigation.MainRouter
import yandexschool.dmpolyakov.money.repositories.AccountRepository
import yandexschool.dmpolyakov.money.ui.base.mvp.BaseMvpPresenter
import javax.inject.Inject


@InjectViewState
class AccountPresenter @Inject constructor(
        val router: MainRouter,
        val accountRep: AccountRepository) : BaseMvpPresenter<AccountView>(router) {

    fun initAccount(id: String) {
        bind(onUi(accountRep.getAccount(id)).subscribe(
                {
                    viewState.showTitle(it.title)
                    viewState.showBalance(it.balance)
                },
                {
                    // TODO
                })
        )
    }

    override fun getScreenTag() = "AccountPresenter"
}