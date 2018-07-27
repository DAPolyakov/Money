package yandexschool.dmpolyakov.money.di.features

import dagger.Module
import dagger.Provides
import yandexschool.dmpolyakov.money.navigation.MainRouter
import yandexschool.dmpolyakov.money.ui.about.AboutPresenter


@Module
abstract class AboutModule {

    @Module
    companion object {

        @Provides
        fun provideAboutPresenter(router: MainRouter): AboutPresenter = AboutPresenter(router)
    }

}