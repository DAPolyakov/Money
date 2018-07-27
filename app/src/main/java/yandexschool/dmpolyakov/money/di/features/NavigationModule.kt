package yandexschool.dmpolyakov.money.di.features

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import yandexschool.dmpolyakov.money.navigation.MainRouter
import javax.inject.Singleton


@Module
class NavigationModule {
    private var cicerone: Cicerone<MainRouter> = Cicerone.create(MainRouter())

    @Provides
    @Singleton
    fun provideRouter(): MainRouter {
        return cicerone.router
    }

    @Provides
    @Singleton
    fun provideNavigatorHolder(): NavigatorHolder {
        return cicerone.navigatorHolder
    }

}