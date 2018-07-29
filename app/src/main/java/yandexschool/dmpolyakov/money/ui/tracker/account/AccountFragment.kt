package yandexschool.dmpolyakov.money.ui.tracker.account

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


class AccountFragment() : BaseMvpFragment<AccountPresenter>(), AccountView {

    companion object {
        val instance = AccountFragment()
    }

    @Inject
    lateinit var router: MainRouter

    @Inject
    @InjectPresenter
    lateinit var presenter: AccountPresenter

    @ProvidePresenter
    override fun providePresenter() = presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View = inflater.inflate(R.layout.fragment_account, container, false)

    override fun getLogName() = "AccountFragment"
}