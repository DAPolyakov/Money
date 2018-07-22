package yandexschool.dmpolyakov.money.ui.tracker

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_tracker.*
import yandexschool.dmpolyakov.money.Currency
import yandexschool.dmpolyakov.money.R
import yandexschool.dmpolyakov.money.utils.toDollars
import yandexschool.dmpolyakov.money.utils.toRubbles
import java.text.DecimalFormat


class TrackerFragment : Fragment(), TrackerView {

    private val presenter: TrackerPresenter by lazy {
        TrackerPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_tracker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)
        presenter.viewIsReady()
    }

    override fun showBalance(count: Double, currency: Currency) {
        val df = DecimalFormat("0.00")

        val rubbles = "${df.format(count.toRubbles(currency))} ${Currency.Rubble.sign}"
        val dollars = "${df.format(count.toDollars(currency))} ${Currency.Dollar.sign}"

        this.rubbles.text = rubbles
        this.dollars.text = dollars
    }
}