package yandexschool.dmpolyakov.money.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import yandexschool.dmpolyakov.money.R

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

        presenter.attachView(this)
        presenter.viewIsReady()
    }

    private fun showFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun showAboutFragment() {
        Log.d("myfragment", "On About!")
    }

    override fun showTrackerFragment() {
        Log.d("myfragment", "On Tracker!")
    }

    override fun showSettingsFragment() {
        Log.d("myfragment", "On Settings!")
    }
}
