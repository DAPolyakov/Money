package yandexschool.dmpolyakov.money.ui

import yandexschool.dmpolyakov.money.R
import yandexschool.dmpolyakov.money.ui.mvp.BasePresenter


class MainPresenter() : BasePresenter<MainView>() {

    override fun viewIsReady() {
        getView()?.showTrackerFragment()
    }

    fun onItem(itemId: Int) {
        when (itemId) {
            R.id.about -> getView()?.showAboutFragment()
            R.id.tracker -> getView()?.showTrackerFragment()
            R.id.settings -> getView()?.showSettingsFragment()
        }
    }

}