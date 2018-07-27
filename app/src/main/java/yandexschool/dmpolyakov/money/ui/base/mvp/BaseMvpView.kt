package yandexschool.dmpolyakov.money.ui.base.mvp

import android.support.annotation.StringRes
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType


@StateStrategyType(SkipStrategy::class)
interface BaseMvpView : MvpView {
    fun showToast(@StringRes stringId: Int)
    fun showToast(string: String)
}