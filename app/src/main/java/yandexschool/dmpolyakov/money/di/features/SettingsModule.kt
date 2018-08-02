package yandexschool.dmpolyakov.money.di.features

import dagger.Module
import dagger.Provides
import yandexschool.dmpolyakov.money.navigation.MainRouter
import yandexschool.dmpolyakov.money.ui.settings.SettingsPresenter


@Module
object SettingsModule {

    @JvmStatic
    @Provides
    fun provideSettingsPresenter(router: MainRouter) = SettingsPresenter(router)

}