package yandexschool.dmpolyakov.money.ui.tracker.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_account.*
import yandexschool.dmpolyakov.money.R
import yandexschool.dmpolyakov.money.models.Account
import yandexschool.dmpolyakov.money.navigation.MainRouter
import yandexschool.dmpolyakov.money.ui.base.ViewPagerAdapter
import yandexschool.dmpolyakov.money.ui.base.mvp.BaseMvpFragment
import yandexschool.dmpolyakov.money.ui.tracker.account.operations.OperationsFragment
import yandexschool.dmpolyakov.money.ui.tracker.account.settings.AccountSettingsFragment
import javax.inject.Inject


class AccountFragment() : BaseMvpFragment<AccountPresenter>(), AccountView {

    companion object {
        private const val EXTRA_ACCOUNT_ID = "account_id"

        fun newInstance(accountId: String): AccountFragment {
            val fragment = AccountFragment()
            fragment.arguments = Bundle(1).apply {
                putString(EXTRA_ACCOUNT_ID, accountId)
            }
            return fragment
        }
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        icBack.setOnClickListener {
            router.back()
        }

        presenter.initAccount(arguments!!.getString(EXTRA_ACCOUNT_ID, ""))
    }

    override fun showTitle(title: String) {
        this.title.text = title
    }

    override fun showBalance(balance: String) {
        this.balance.text = balance
    }

    override fun showTabs(account: Account) {
        tabs.setupWithViewPager(viewPager)

        val fragmentOperations = OperationsFragment()
        val bundle = Bundle()
        bundle.putParcelable("account", account)
        fragmentOperations.arguments = bundle

        val accountSettingsFragment = AccountSettingsFragment()
        accountSettingsFragment.arguments = bundle

        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(fragmentOperations, "История операций")
        adapter.addFragment(accountSettingsFragment, "Настройки")
        viewPager.adapter = adapter
    }

    override fun getLogName() = "AccountFragment"
}