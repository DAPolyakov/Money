package yandexschool.dmpolyakov.money.ui.tracker.account.settings

import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.delegateadapter.delegate.diff.DiffUtilCompositeAdapter
import kotlinx.android.synthetic.main.fragment_account_settings.*
import yandexschool.dmpolyakov.money.R
import yandexschool.dmpolyakov.money.models.Account
import yandexschool.dmpolyakov.money.navigation.MainRouter
import yandexschool.dmpolyakov.money.ui.base.mvp.BaseMvpFragment
import yandexschool.dmpolyakov.money.ui.base.rv_delegates.EmptyStateDelegateAdapter
import yandexschool.dmpolyakov.money.ui.base.rv_delegates.OperationsDelegateAdapter
import javax.inject.Inject


class AccountSettingsFragment : BaseMvpFragment<AccountSettingsPresenter>(), AccountSettingsView {

    @Inject
    lateinit var router: MainRouter

    @Inject
    @InjectPresenter
    lateinit var presenter: AccountSettingsPresenter

    @ProvidePresenter
    override fun providePresenter() = presenter

    private lateinit var renameDialog: AlertDialog

    private val operationsAdapter = DiffUtilCompositeAdapter.Builder()
            .add(OperationsDelegateAdapter())
            .add(EmptyStateDelegateAdapter())
            .build()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View = inflater.inflate(R.layout.fragment_account_settings, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rename.setOnClickListener {
            showDialog()
        }

        renameDialog = AlertDialog.Builder(view.context)
                .setView(R.layout.dialog_rename)
                .setCancelable(false)
                .create()

        val account: Account = arguments?.getParcelable("account")!!
        presenter.loadAccount(account)
    }

    private fun showDialog() {
        with(renameDialog) {
            show()

            val title = findViewById<TextInputLayout>(R.id.title)

            findViewById<View>(R.id.cancel)?.setOnClickListener {
                dismiss()
            }

            findViewById<View>(R.id.add)?.setOnClickListener {
                if (title?.editText?.text.toString().isBlank()) {
                    title?.error = getString(R.string.error_empty_title)
                    return@setOnClickListener
                }

                presenter.rename(title?.editText?.text?.toString()!!)

                dismiss()
            }

            setOnDismissListener {
                title?.editText?.setText("")
                title?.isErrorEnabled = false
            }
        }
    }

    override fun getLogName() = "AccountSettingsFragment"

}