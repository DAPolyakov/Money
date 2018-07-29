package yandexschool.dmpolyakov.money.ui.tracker.account.operations

import com.arellomobile.mvp.InjectViewState
import yandexschool.dmpolyakov.money.navigation.MainRouter
import yandexschool.dmpolyakov.money.ui.base.mvp.BaseMvpPresenter
import javax.inject.Inject


@InjectViewState
class OperationsPresenter @Inject constructor(router: MainRouter)
    : BaseMvpPresenter<OperationsView>(router) {


    override fun getScreenTag() = "OperationsPresenter"

}