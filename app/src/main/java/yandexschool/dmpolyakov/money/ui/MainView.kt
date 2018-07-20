package yandexschool.dmpolyakov.money.ui

import yandexschool.dmpolyakov.money.ui.mvp.MvpView


interface MainView : MvpView {
    fun showAboutFragment()
    fun showTrackerFragment()
    fun showSettingsFragment()
}