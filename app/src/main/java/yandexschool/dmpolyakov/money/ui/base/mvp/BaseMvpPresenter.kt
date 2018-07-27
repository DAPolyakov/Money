package yandexschool.dmpolyakov.money.ui.base.mvp

import io.reactivex.disposables.Disposable
import yandexschool.dmpolyakov.money.navigation.MainRouter


abstract class BaseMvpPresenter<View : BaseMvpView>(private val router: MainRouter) : BaseSchedulablePresenter<View>() {

    private val screenTag: String

    protected abstract fun getScreenTag(): String

    init {
        screenTag = getScreenTag()
    }

    protected fun bind(disposable: Disposable) {
        bind(disposable, LifeLevel.PER_VIEW)
    }

    fun goBack() {
        router.back()
    }

}
