package yandexschool.dmpolyakov.money.di.base

import android.support.v4.app.Fragment
import com.arellomobile.mvp.MvpAppCompatActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class InjectableActivity : MvpAppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return fragmentInjector
    }
}