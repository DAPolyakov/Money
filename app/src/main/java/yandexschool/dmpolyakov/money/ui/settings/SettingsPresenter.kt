package yandexschool.dmpolyakov.money.ui.settings

import com.arellomobile.mvp.InjectViewState
import yandexschool.dmpolyakov.money.navigation.MainRouter
import yandexschool.dmpolyakov.money.ui.base.mvp.BaseMvpPresenter
import javax.inject.Inject

@InjectViewState
class SettingsPresenter @Inject constructor(router: MainRouter) : BaseMvpPresenter<SettingsView>(router) {

    override fun getScreenTag(): String {
        return "SettingsPresenter"
    }

}