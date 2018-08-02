package yandexschool.dmpolyakov.money.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import yandexschool.dmpolyakov.money.App
import yandexschool.dmpolyakov.money.di.features.AppModule
import yandexschool.dmpolyakov.money.di.features.NavigationModule
import yandexschool.dmpolyakov.money.di.features.NetworkModule
import yandexschool.dmpolyakov.money.ui.base.mvp.BaseMvpActivity
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AppModule::class,
    AndroidSupportInjectionModule::class,
    AndroidInjectionModule::class,
    NavigationModule::class,
    NetworkModule::class]
)
interface AppComponent {
    fun inject(navigateInjector: BaseMvpActivity.Companion.NavigateInjector)

    fun inject(instance: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
