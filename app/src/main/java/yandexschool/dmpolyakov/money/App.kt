package yandexschool.dmpolyakov.money

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import yandexschool.dmpolyakov.money.di.base.AppInjector
import yandexschool.dmpolyakov.money.di.component.AppComponent
import javax.inject.Inject


class App : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    companion object {
        private lateinit var instance: App
        fun getInstance(): App = instance

        fun getAppComponent(): AppComponent = AppInjector.appComponent

    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        AppInjector.init(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }

}