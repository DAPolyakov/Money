package yandexschool.dmpolyakov.money.di.features

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import yandexschool.dmpolyakov.money.navigation.MainRouter
import yandexschool.dmpolyakov.money.repositories.AccountRepository
import yandexschool.dmpolyakov.money.ui.tracker.TrackerPresenter
import yandexschool.dmpolyakov.money.ui.tracker.account.AccountFragment


@Module
abstract class TrackerModule {

    @Module
    companion object {

        @Provides
        fun provideTrackerPresenter(router: MainRouter, accountRepository: AccountRepository) =
                TrackerPresenter(router, accountRepository)

    }
}