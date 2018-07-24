package yandexschool.dmpolyakov.money.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import yandexschool.dmpolyakov.money.R
import yandexschool.dmpolyakov.money.ui.about.AboutFragment
import yandexschool.dmpolyakov.money.ui.settings.SettingsFragment
import yandexschool.dmpolyakov.money.ui.tracker.TrackerFragment

class MainActivity : AppCompatActivity(), MainView {

    private val presenter by lazy {
        MainPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.attachView(this)

        bottomNavigation.setOnNavigationItemSelectedListener {
            presenter.onItem(it.itemId)
            true
        }

        if (savedInstanceState == null) {
            initStartFragment()
            presenter.viewIsReady()
        }
    }

    private fun initStartFragment() {
        bottomNavigation.selectedItemId = R.id.tracker
    }

    override fun showAboutFragment() {
        showFragment(AboutFragment())
    }

    override fun showTrackerFragment() {
        showFragment(TrackerFragment())
    }

    override fun showSettingsFragment() {
        showFragment(SettingsFragment())
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame, fragment)
                .commit()
    }
}