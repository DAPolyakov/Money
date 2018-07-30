package yandexschool.dmpolyakov.money.ui.tracker.account.operations

import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Spinner
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.delegateadapter.delegate.diff.DiffUtilCompositeAdapter
import com.example.delegateadapter.delegate.diff.IComparableItem
import kotlinx.android.synthetic.main.fragment_operations.*
import yandexschool.dmpolyakov.money.Currency
import yandexschool.dmpolyakov.money.OperationCategory
import yandexschool.dmpolyakov.money.OperationType
import yandexschool.dmpolyakov.money.R
import yandexschool.dmpolyakov.money.models.Account
import yandexschool.dmpolyakov.money.models.FinanceOperation
import yandexschool.dmpolyakov.money.navigation.MainRouter
import yandexschool.dmpolyakov.money.ui.base.mvp.BaseMvpFragment
import yandexschool.dmpolyakov.money.ui.base.rv_delegates.EmptyStateDelegateAdapter
import yandexschool.dmpolyakov.money.ui.base.rv_delegates.OperationsDelegateAdapter
import yandexschool.dmpolyakov.money.ui.base.rv_delegates.view_models.EmptyStateViewModel
import yandexschool.dmpolyakov.money.ui.tracker.CurrencyArrayAdapter
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class OperationsFragment : BaseMvpFragment<OperationsPresenter>(), OperationsView {

    @Inject
    lateinit var router: MainRouter

    @Inject
    @InjectPresenter
    lateinit var presenter: OperationsPresenter

    @ProvidePresenter
    override fun providePresenter() = presenter

    private lateinit var addNewOperationDialog: AlertDialog

    private val operationsAdapter = DiffUtilCompositeAdapter.Builder()
            .add(OperationsDelegateAdapter())
            .add(EmptyStateDelegateAdapter())
            .build()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View = inflater.inflate(R.layout.fragment_operations, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv.layoutManager = LinearLayoutManager(context)
        rv.adapter = operationsAdapter

        addOperation.setOnClickListener {
            showDialog()
        }

        addNewOperationDialog = AlertDialog.Builder(view.context)
                .setView(R.layout.dialog_add_new_operation)
                .setCancelable(false)
                .create()

        val account: Account = arguments?.getParcelable("account")!!
        presenter.loadAccount(account)
    }

    private fun showDialog() {
        with(addNewOperationDialog) {
            show()

            val title = findViewById<TextInputLayout>(R.id.title)
            val amount = findViewById<TextInputLayout>(R.id.amount)
            val type = findViewById<RadioGroup>(R.id.type)

            val currency = findViewById<Spinner>(R.id.spinnerCurrency)
            val category = findViewById<Spinner>(R.id.spinnerCategory)

            currency?.adapter = CurrencyArrayAdapter(context, Currency.values().toList())
            category?.adapter = CategoryArrayAdapter(context, OperationType.Income.getCategories())

            type?.setOnCheckedChangeListener { radioGroup, id ->
                val adapter = (category?.adapter as CategoryArrayAdapter)
                adapter.clear()
                when (id) {
                    R.id.income -> adapter.addAll(OperationType.Income.getCategories())
                    R.id.expense -> adapter.addAll(OperationType.Expense.getCategories())
                }
                adapter.notifyDataSetChanged()
            }

            findViewById<View>(R.id.cancel)?.setOnClickListener {
                dismiss()
            }

            findViewById<View>(R.id.add)?.setOnClickListener {
                if (title?.editText?.text.toString().isBlank()) {
                    title?.error = getString(R.string.error_empty_title)
                    return@setOnClickListener
                }

                if (amount?.editText?.text.toString().isBlank()) {
                    amount?.error = getString(R.string.error_empty_amount)
                    return@setOnClickListener
                }

                val df = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                val currentDate = df.format(Calendar.getInstance().time)

                val operationType: OperationType = when (type?.checkedRadioButtonId) {
                    R.id.income -> OperationType.Income
                    R.id.expense -> OperationType.Expense
                    else -> throw Exception("Unknown operation type")
                }

                presenter.addOperation(
                        FinanceOperation(
                                title = title?.editText?.text.toString(),
                                currency = currency?.selectedItem as Currency,
                                amount = BigDecimal(amount?.editText?.text?.toString()),
                                type = operationType,
                                category = category?.selectedItem as OperationCategory,
                                date = currentDate
                        )
                )

                dismiss()
            }

            setOnDismissListener {
                title?.editText?.setText("")
                title?.isErrorEnabled = false
                amount?.editText?.setText("")
                amount?.isErrorEnabled = false
                currency?.setSelection(0)
                title?.requestFocus()
            }
        }
    }

    override fun showOperations(operations: List<FinanceOperation>) {
        val data = ArrayList<IComparableItem>()
        data.addAll(operations.reversed())
        data.add(EmptyStateViewModel(getString(R.string.empty_operations_list)))
        operationsAdapter.swapData(data)
        rv.scrollToPosition(0)
    }

    override fun getLogName() = "OperationsFragment"

}