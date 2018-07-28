package yandexschool.dmpolyakov.money.ui.tracker

import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.delegateadapter.delegate.diff.DiffUtilCompositeAdapter
import com.example.delegateadapter.delegate.diff.IComparableItem
import kotlinx.android.synthetic.main.fragment_tracker.*
import yandexschool.dmpolyakov.money.Currency
import yandexschool.dmpolyakov.money.R
import yandexschool.dmpolyakov.money.models.Account
import yandexschool.dmpolyakov.money.navigation.MainRouter
import yandexschool.dmpolyakov.money.ui.base.mvp.BaseMvpFragment
import yandexschool.dmpolyakov.money.ui.base.rv_delegates.AccountDelegateAdapter
import yandexschool.dmpolyakov.money.ui.base.rv_delegates.EmptyStateDelegateAdapter
import yandexschool.dmpolyakov.money.ui.base.rv_delegates.SubtitleDelegateAdapter
import yandexschool.dmpolyakov.money.ui.base.rv_delegates.view_models.EmptyStateViewModel
import yandexschool.dmpolyakov.money.ui.base.rv_delegates.view_models.SubtitleViewModel
import java.math.BigDecimal
import javax.inject.Inject


class TrackerFragment : BaseMvpFragment<TrackerPresenter>(), TrackerView {

    companion object {
        val instance = TrackerFragment()
    }

    @Inject
    lateinit var router: MainRouter

    @Inject
    @InjectPresenter
    lateinit var presenter: TrackerPresenter

    @ProvidePresenter
    override fun providePresenter(): TrackerPresenter {
        return presenter
    }

    private lateinit var addNewAccountDialog: AlertDialog

    private val accountAdapter = DiffUtilCompositeAdapter.Builder()
            .add(AccountDelegateAdapter())
            .add(EmptyStateDelegateAdapter())
            .add(SubtitleDelegateAdapter())
            .build()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? = inflater.inflate(R.layout.fragment_tracker, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv.layoutManager = LinearLayoutManager(context)
        rv.adapter = accountAdapter

        addAccount.setOnClickListener {
            showDialog()
        }

        addNewAccountDialog = AlertDialog.Builder(view.context)
                .setView(R.layout.dialog_add_new_account)
                .setCancelable(false)
                .create()
    }

    override fun showDialog() {
        with(addNewAccountDialog) {
            show()

            val title = findViewById<TextInputLayout>(R.id.title)
            val amountInput = findViewById<EditText>(R.id.inputAmount)

            val currency = findViewById<Spinner>(R.id.spinnerCurrency)
            currency?.adapter = CurrencyArrayAdapter(context, Currency.values().toList())

            findViewById<View>(R.id.cancel)?.setOnClickListener {
                dismiss()
            }

            findViewById<View>(R.id.create)?.setOnClickListener {
                var amount = amountInput?.text.toString()
                if (amount.isBlank()) amount = "0"

                if (title?.editText?.text.toString().isBlank()) {
                    title?.error = "Введите название счета"
                    return@setOnClickListener
                }

                presenter.addAccount(
                        Account(
                                title = title?.editText?.text.toString(),
                                amount = BigDecimal(amount),
                                currency = currency?.selectedItem as Currency))

                dismiss()
            }

            setOnDismissListener {
                Log.wtf("dima", "DISMISS")
                title?.editText?.setText("")
                title?.isErrorEnabled = false
                amountInput?.setText("")
                currency?.setSelection(0)
                title?.requestFocus()
            }
        }
    }

    override fun showBalance(count: BigDecimal, currency: Currency) {

    }

    override fun showAccounts(accounts: List<Account>) {
        val data = ArrayList<IComparableItem>()
        if (accounts.isNotEmpty()) {
            data.add(SubtitleViewModel("${getString(R.string.my_accounts)}:"))
            data.addAll(accounts)
        }
        data.add(EmptyStateViewModel(getString(R.string.no_accounts)))
        accountAdapter.swapData(data)
    }

    override fun getLogName() = "TrackerFragment"

    override fun onStop() {
        if (addNewAccountDialog.isShowing) {
            addNewAccountDialog.dismiss()
        }
        super.onStop()
    }
}