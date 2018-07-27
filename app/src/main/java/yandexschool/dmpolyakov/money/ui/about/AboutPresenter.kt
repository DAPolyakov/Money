package yandexschool.dmpolyakov.money.ui.about

import com.arellomobile.mvp.InjectViewState
import yandexschool.dmpolyakov.money.BuildConfig
import yandexschool.dmpolyakov.money.navigation.MainRouter
import yandexschool.dmpolyakov.money.ui.base.mvp.BaseMvpPresenter
import javax.inject.Inject


@InjectViewState
class AboutPresenter @Inject constructor(var router: MainRouter) : BaseMvpPresenter<AboutView>(router) {

    override fun getScreenTag(): String {
        return "AboutPresenter"
    }

    override fun attachView(view: AboutView) {
        super.attachView(view)
        viewState?.showVersion(BuildConfig.VERSION_NAME)
    }

}