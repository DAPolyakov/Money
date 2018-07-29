package yandexschool.dmpolyakov.money.ui.tracker.account

import com.arellomobile.mvp.InjectViewState
import yandexschool.dmpolyakov.money.navigation.MainRouter
import yandexschool.dmpolyakov.money.ui.base.mvp.BaseMvpPresenter
import javax.inject.Inject


@InjectViewState
class AccountPresenter @Inject constructor(var router: MainRouter) : BaseMvpPresenter<AccountView>(router) {


    override fun getScreenTag() = "AccountPresenter"
}