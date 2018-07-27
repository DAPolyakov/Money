package yandexschool.dmpolyakov.money.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_about.*
import yandexschool.dmpolyakov.money.R
import yandexschool.dmpolyakov.money.navigation.MainRouter
import yandexschool.dmpolyakov.money.ui.base.mvp.BaseMvpFragment
import javax.inject.Inject


class AboutFragment : BaseMvpFragment<AboutPresenter>(), AboutView {

    companion object {
        val instance = AboutFragment()
    }

    @Inject
    lateinit var router: MainRouter

    @Inject
    @InjectPresenter
    lateinit var presenter: AboutPresenter

    @ProvidePresenter
    override fun providePresenter() = presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun showVersion(version: String) {
        val s = "${getString(R.string.about_app_title)} v$version"
        title.text = s
    }

    override fun getLogName() = "AboutFragment"
}