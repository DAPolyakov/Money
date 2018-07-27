package yandexschool.dmpolyakov.money.di.features

import dagger.Module
import dagger.Provides
import yandexschool.dmpolyakov.money.navigation.MainRouter
import yandexschool.dmpolyakov.money.ui.tracker.TrackerPresenter


@Module
abstract class TrackerModule {

    @Module
    companion object {

        @Provides
        fun provideTrackerPresenter(router: MainRouter) = TrackerPresenter(router)
    }
}