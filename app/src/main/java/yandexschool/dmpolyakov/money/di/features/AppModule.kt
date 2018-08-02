package yandexschool.dmpolyakov.money.di.features

import android.app.Application
import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import yandexschool.dmpolyakov.money.repository.AccountRepository
import yandexschool.dmpolyakov.money.repository.AccountRepositoryImpl
import yandexschool.dmpolyakov.money.ui.MainActivity
import javax.inject.Singleton


@Module
abstract class AppModule {

    @ContributesAndroidInjector(modules = [(MainModule::class)])
    abstract fun contributeMainActivity(): MainActivity

    @Module
    companion object {
        @JvmStatic
        @Singleton
        @Provides
        fun provideContext(app: Application): Context = app

        @JvmStatic
        @Singleton
        @Provides
        fun provideResources(context: Context): Resources = context.resources

        @JvmStatic
        @Singleton
        @Provides
        fun provideAccountRepository(): AccountRepository = AccountRepositoryImpl()
    }

}