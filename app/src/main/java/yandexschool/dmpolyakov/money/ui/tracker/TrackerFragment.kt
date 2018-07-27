package yandexschool.dmpolyakov.money.ui.tracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_tracker.*
import yandexschool.dmpolyakov.money.Currency
import yandexschool.dmpolyakov.money.R
import yandexschool.dmpolyakov.money.navigation.MainRouter
import yandexschool.dmpolyakov.money.ui.base.mvp.BaseMvpFragment
import yandexschool.dmpolyakov.money.utils.toDollars
import yandexschool.dmpolyakov.money.utils.toRubbles
import java.text.DecimalFormat
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? = inflater.inflate(R.layout.fragment_tracker, container, false)

    override fun showBalance(count: Double, currency: Currency) {
        val df = DecimalFormat("0.00")

        val rubbles = "${df.format(count.toRubbles(currency))} ${Currency.Rubble.sign}"
        val dollars = "${df.format(count.toDollars(currency))} ${Currency.Dollar.sign}"

        this.rubbles.text = rubbles
        this.dollars.text = dollars
    }

    override fun getLogName() = "TrackerFragment"
}