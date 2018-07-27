package yandexschool.dmpolyakov.money.ui.about

import yandexschool.dmpolyakov.money.ui.base.mvp.BaseMvpView


interface AboutView : BaseMvpView {
    fun showVersion(version: String)
}