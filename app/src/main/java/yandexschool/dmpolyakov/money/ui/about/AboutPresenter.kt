package yandexschool.dmpolyakov.money.ui.about

import yandexschool.dmpolyakov.money.BuildConfig
import yandexschool.dmpolyakov.money.ui.mvp.BasePresenter


class AboutPresenter : BasePresenter<AboutView>() {

    override fun viewIsReady() {
        getView()?.showVersion(BuildConfig.VERSION_NAME)
    }

}