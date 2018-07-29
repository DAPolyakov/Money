package yandexschool.dmpolyakov.money.ui.tracker.account.operations

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.delegateadapter.delegate.diff.DiffUtilCompositeAdapter
import com.example.delegateadapter.delegate.diff.IComparableItem
import kotlinx.android.synthetic.main.fragment_operations.*
import yandexschool.dmpolyakov.money.R
import yandexschool.dmpolyakov.money.models.FinanceOperation
import yandexschool.dmpolyakov.money.navigation.MainRouter
import yandexschool.dmpolyakov.money.ui.base.mvp.BaseMvpFragment
import yandexschool.dmpolyakov.money.ui.base.rv_delegates.EmptyStateDelegateAdapter
import yandexschool.dmpolyakov.money.ui.base.rv_delegates.OperationsDelegateAdapter
import yandexschool.dmpolyakov.money.ui.base.rv_delegates.view_models.EmptyStateViewModel
import javax.inject.Inject


class OperationsFragment : BaseMvpFragment<OperationsPresenter>(), OperationsView {

    @Inject
    lateinit var router: MainRouter

    @Inject
    @InjectPresenter
    lateinit var presenter: OperationsPresenter

    @ProvidePresenter
    override fun providePresenter() = presenter

    private val operationsAdapter = DiffUtilCompositeAdapter.Builder()
            .add(OperationsDelegateAdapter())
            .add(EmptyStateDelegateAdapter())
            .build()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View = inflater.inflate(R.layout.fragment_operations, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)
        rv.adapter = operationsAdapter

        val operations: ArrayList<FinanceOperation> = arguments?.getParcelableArrayList("operations")!!
        showOperations(operations)
    }

    private fun showOperations(operations: List<FinanceOperation>) {
        val data = ArrayList<IComparableItem>()
        data.addAll(operations)
        data.add(EmptyStateViewModel(getString(R.string.empty_operations_list)))
        operationsAdapter.swapData(data)

        rv.scrollToPosition(operationsAdapter.itemCount - 1)
    }

    override fun getLogName() = "OperationsFragment"

}