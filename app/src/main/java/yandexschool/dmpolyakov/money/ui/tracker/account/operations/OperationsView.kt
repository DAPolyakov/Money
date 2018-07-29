package yandexschool.dmpolyakov.money.ui.tracker.account.operations

import yandexschool.dmpolyakov.money.models.FinanceOperation
import yandexschool.dmpolyakov.money.ui.base.mvp.BaseMvpView


interface OperationsView : BaseMvpView {


    fun showOperations(operations: List<FinanceOperation>)
}