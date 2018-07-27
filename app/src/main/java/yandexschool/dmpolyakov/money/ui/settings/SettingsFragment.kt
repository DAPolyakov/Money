package yandexschool.dmpolyakov.money.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import yandexschool.dmpolyakov.money.R
import yandexschool.dmpolyakov.money.navigation.MainRouter
import yandexschool.dmpolyakov.money.ui.base.mvp.BaseMvpFragment
import javax.inject.Inject


class SettingsFragment : BaseMvpFragment<SettingsPresenter>(), SettingsView {

    companion object {
        val instance = SettingsFragment()
    }

    @Inject
    lateinit var router: MainRouter

    @Inject
    @InjectPresenter
    lateinit var presenter: SettingsPresenter

    @ProvidePresenter
    override fun providePresenter() = presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? = inflater.inflate(R.layout.fragment_settings, container, false)

    override fun getLogName() = "SettingsFragment"
}