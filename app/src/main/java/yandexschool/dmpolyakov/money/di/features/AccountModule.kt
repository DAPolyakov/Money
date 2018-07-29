package yandexschool.dmpolyakov.money.di.features

import dagger.Module
import dagger.Provides
import yandexschool.dmpolyakov.money.navigation.MainRouter
import yandexschool.dmpolyakov.money.repositories.AccountRepository
import yandexschool.dmpolyakov.money.ui.tracker.account.AccountPresenter
import yandexschool.dmpolyakov.money.ui.tracker.account.operations.OperationsPresenter


@Module
abstract class AccountModule {

    @Module
    companion object {

        @Provides
        fun provideAccountPresenter(router: MainRouter, accountRep: AccountRepository) =
                AccountPresenter(router, accountRep)

        @Provides
        fun provideOperationsPresenter(router: MainRouter, accountRep: AccountRepository) =
                OperationsPresenter(router, accountRep)

    }

}