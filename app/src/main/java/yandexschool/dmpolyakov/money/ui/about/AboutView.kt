package yandexschool.dmpolyakov.money.ui.about

import yandexschool.dmpolyakov.money.mvp.MvpView


interface AboutView : MvpView {
    fun showVersion(version: String)
}