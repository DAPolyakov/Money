package yandexschool.dmpolyakov.money.di.features

import dagger.Module
import dagger.Provides
import yandexschool.dmpolyakov.money.navigation.MainRouter
import yandexschool.dmpolyakov.money.ui.about.AboutPresenter


@Module
object AboutModule {

    @JvmStatic
    @Provides
    fun provideAboutPresenter(router: MainRouter): AboutPresenter = AboutPresenter(router)

}