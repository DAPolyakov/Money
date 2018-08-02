package yandexschool.dmpolyakov.money.di.features

import dagger.Module
import dagger.Provides
import yandexschool.dmpolyakov.money.navigation.MainRouter
import yandexschool.dmpolyakov.money.repository.AccountRepository
import yandexschool.dmpolyakov.money.ui.tracker.TrackerPresenter


@Module
object TrackerModule {

    @JvmStatic
    @Provides
    fun provideTrackerPresenter(router: MainRouter, accountRepository: AccountRepository): TrackerPresenter =
            TrackerPresenter(router, accountRepository)
    
}