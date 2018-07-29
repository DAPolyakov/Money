package yandexschool.dmpolyakov.money.di.features

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import yandexschool.dmpolyakov.money.navigation.MainRouter
import yandexschool.dmpolyakov.money.ui.tracker.account.AccountPresenter


@Module
abstract class AccountModule {

    @Module
    companion object {

        @Provides
        fun provideAccountPresenter(router: MainRouter) = AccountPresenter(router)
    }

}