package yandexschool.dmpolyakov.money.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_main.*
import yandexschool.dmpolyakov.money.R
import yandexschool.dmpolyakov.money.navigation.MainRouter
import yandexschool.dmpolyakov.money.navigation.Screens
import yandexschool.dmpolyakov.money.ui.about.AboutFragment
import yandexschool.dmpolyakov.money.ui.base.mvp.BaseMvpActivity
import yandexschool.dmpolyakov.money.ui.settings.SettingsFragment
import yandexschool.dmpolyakov.money.ui.tracker.TrackerFragment
import yandexschool.dmpolyakov.money.ui.tracker.account.AccountFragment
import javax.inject.Inject

class MainActivity : BaseMvpActivity<MainPresenter>(), MainView {

    @Inject
    lateinit var router: MainRouter

    @Inject
    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    override fun providePresenter() = presenter

    override fun getNavigationContainerId(): Int = R.id.frame

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState ?: Bundle())
        setContentView(R.layout.activity_main)

        bottomNavigation.setOnNavigationItemSelectedListener {
            presenter.onItem(it.itemId)
            true
        }

        if (savedInstanceState == null) {
            showInitialFragment()
        }
    }

    fun showBottomNavigationMenu() {
        bottomNavigation.visibility = View.VISIBLE
    }

    private fun hideBottomNavigationMenu() {
        bottomNavigation.visibility = View.GONE
    }

    private fun showInitialFragment() {
        bottomNavigation.selectedItemId = R.id.tracker
    }

    override fun createFragment(screenKey: String, data: Any?): Fragment? {
        return when (Screens.valueOf(screenKey)) {
            Screens.TRACKER -> TrackerFragment.instance
            Screens.SETTINGS -> SettingsFragment.instance
            Screens.ABOUT -> AboutFragment.instance
            Screens.ACCOUNT -> {
                hideBottomNavigationMenu()
                AccountFragment.newInstance(data.toString())
            }
        }
    }

}