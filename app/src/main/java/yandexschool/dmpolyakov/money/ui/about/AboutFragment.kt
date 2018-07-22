package yandexschool.dmpolyakov.money.ui.about

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_about.*
import yandexschool.dmpolyakov.money.R


class AboutFragment : Fragment(), AboutView {

    private val presenter by lazy {
        AboutPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)
        presenter.viewIsReady()
    }

    override fun showVersion(version: String) {
        val s = "${getString(R.string.about_app_title)} v$version"
        title.text = s
    }
}