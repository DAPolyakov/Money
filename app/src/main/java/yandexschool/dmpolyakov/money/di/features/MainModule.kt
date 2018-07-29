package yandexschool.dmpolyakov.money.di.features

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import yandexschool.dmpolyakov.money.navigation.MainRouter
import yandexschool.dmpolyakov.money.ui.MainPresenter
import yandexschool.dmpolyakov.money.ui.about.AboutFragment
import yandexschool.dmpolyakov.money.ui.settings.SettingsFragment
import yandexschool.dmpolyakov.money.ui.tracker.TrackerFragment
import yandexschool.dmpolyakov.money.ui.tracker.account.AccountFragment


@Module
abstract class MainModule {

    @ContributesAndroidInjector(modules = [TrackerModule::class])
    abstract fun contributeTrackerFragment(): TrackerFragment

    @ContributesAndroidInjector(modules = [SettingsModule::class])
    abstract fun contributeSettingsFragment(): SettingsFragment

    @ContributesAndroidInjector(modules = [AboutModule::class])
    abstract fun contributeAboutFragment(): AboutFragment

    @ContributesAndroidInjector(modules = [AccountModule::class])
    abstract fun contributeAccountFragment(): AccountFragment

    @Module
    companion object {

        @Provides
        fun provideMainPresenter(router: MainRouter) = MainPresenter(router)

    }
}
