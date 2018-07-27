package yandexschool.dmpolyakov.money.ui

import com.arellomobile.mvp.InjectViewState
import yandexschool.dmpolyakov.money.R
import yandexschool.dmpolyakov.money.navigation.MainRouter
import yandexschool.dmpolyakov.money.ui.base.mvp.BaseMvpPresenter
import javax.inject.Inject


@InjectViewState
class MainPresenter @Inject constructor(var router: MainRouter) : BaseMvpPresenter<MainView>(router) {

    fun onItem(itemId: Int) {
        when (itemId) {
            R.id.about -> router.showAboutScreen()
            R.id.tracker -> router.showTrackerScreen()
            R.id.settings -> router.showSettingsScreen()
        }
    }

    override fun attachView(view: MainView) {
        super.attachView(view)
    }

    override fun getScreenTag(): String {
        return "tag"
    }
}