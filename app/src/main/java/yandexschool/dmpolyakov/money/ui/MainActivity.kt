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

        bottomNavigation.setOnNavigationItemSelectedListener {
            presenter.onItem(it.itemId)
            true
        }

        initStartFragment()

        presenter.attachView(this)
        presenter.viewIsReady()
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
        val transaction = supportFragmentManager.beginTransaction()
        if (supportFragmentManager.fragments.size == 0) {
            transaction.add(R.id.frame, fragment)
        } else {
            transaction.replace(R.id.frame, fragment)
        }
        transaction.commit()
    }
}