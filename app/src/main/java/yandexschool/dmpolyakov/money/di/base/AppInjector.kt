package yandexschool.dmpolyakov.money.di.base

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.util.Log
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import yandexschool.dmpolyakov.money.App
import yandexschool.dmpolyakov.money.BuildConfig
import yandexschool.dmpolyakov.money.di.component.AppComponent
import yandexschool.dmpolyakov.money.di.component.DaggerAppComponent
import yandexschool.dmpolyakov.money.ui.base.LogNamed

object AppInjector {

    private val logger = ScreenNavigateLogger

    lateinit var appComponent: AppComponent

    fun init(app: App) {

        appComponent = DaggerAppComponent.builder().application(app)
                .build()

        appComponent.inject(app)

        app.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle) {
                if (BuildConfig.DEBUG && activity is LogNamed) {
                    logger.onActivityOpened(activity as LogNamed)
                }
                handleActivity(activity)
            }

            override fun onActivityStarted(activity: Activity) {

            }

            override fun onActivityResumed(activity: Activity) {

            }

            override fun onActivityPaused(activity: Activity) {

            }

            override fun onActivityStopped(activity: Activity) {

            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

            }

            override fun onActivityDestroyed(activity: Activity) {

            }
        })
    }


    private fun handleActivity(activity: Activity) {
        if (activity is HasSupportFragmentInjector) {
            AndroidInjection.inject(activity)
        }
        if (activity is FragmentActivity) {
            activity.supportFragmentManager
                    .registerFragmentLifecycleCallbacks(
                            object : FragmentManager.FragmentLifecycleCallbacks() {
                                override fun onFragmentAttached(fm: FragmentManager,
                                                                f: Fragment,
                                                                context: Context) {
                                    if (BuildConfig.DEBUG && f is LogNamed) {
                                        logger.onFragmentOpened(f as LogNamed)
                                    }
                                    if (f is Injectable) {
                                        AndroidSupportInjection.inject(f)
                                    }
                                }

                                override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
                                    super.onFragmentDestroyed(fm, f)
                                    if (BuildConfig.DEBUG && f is LogNamed) {
                                        logger.onFragmentDestroyed(f as LogNamed)
                                    }
                                }
                            }, true)
        }
    }

    object ScreenNavigateLogger {

        fun onFragmentOpened(fragment: LogNamed) {
            Log.d(TAG, "fragment opened " + fragment.getLogName())
        }

        fun onActivityOpened(activity: LogNamed) {
            Log.d(TAG, "activity opened " + activity.getLogName())
        }

        fun onFragmentDestroyed(fragment: LogNamed) {
            Log.d(TAG, "fragment destroyed " + fragment.getLogName())
        }

        private val TAG = "screenLogger"

    }
}
