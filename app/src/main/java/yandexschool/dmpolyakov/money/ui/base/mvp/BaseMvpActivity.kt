package yandexschool.dmpolyakov.money.ui.base.mvp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.widget.Toast
import ru.terrakok.cicerone.NavigatorHolder
import yandexschool.dmpolyakov.money.App
import yandexschool.dmpolyakov.money.di.base.InjectableActivity
import yandexschool.dmpolyakov.money.navigation.BaseNavigator
import yandexschool.dmpolyakov.money.navigation.MainRouter
import javax.inject.Inject


abstract class BaseMvpActivity<Presenter : BaseMvpPresenter<*>> : InjectableActivity(), BaseMvpView {

    private val navigateInjector = NavigateInjector()

    private lateinit var presenter: Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = providePresenter()
        navigateInjector.router.setNavigator(navigator)
    }

    protected open fun createFragment(screenKey: String, data: Any?): Fragment? {
        return null
    }

    @IdRes
    abstract fun getNavigationContainerId(): Int

    private val navigator = object : BaseNavigator(this@BaseMvpActivity, getNavigationContainerId()) {
        override fun createOrApply(screenKey: String, transitionData: Array<Any>): Intent {
            return Intent()
        }

        override fun createFragment(screenKey: String, data: Any): Fragment? {
            return if (containerId != 0) {
                this@BaseMvpActivity.createFragment(screenKey, data)
            } else {
                null
            }
        }

        override fun createActivityIntent(context: Context?, screenKey: String?, data: Any?): Intent? {
            return null
        }
    }

    protected abstract fun providePresenter(): Presenter

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigateInjector.navigatorHolder.setNavigator(navigator)
        navigateInjector.router.setNavigator(navigator)
    }

    override fun showToast(stringId: Int) {
        Toast.makeText(this, stringId, Toast.LENGTH_SHORT).show()
    }

    override fun showToast(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private val LOG_TAG = "BaseActivity"

        class NavigateInjector {
            @Inject
            lateinit var navigatorHolder: NavigatorHolder
            @Inject
            lateinit var router: MainRouter

            init {
                App.getAppComponent().inject(this)
            }
        }
    }

}
