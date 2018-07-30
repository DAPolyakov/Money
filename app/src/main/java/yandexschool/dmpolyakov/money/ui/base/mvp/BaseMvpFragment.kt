package yandexschool.dmpolyakov.money.ui.base.mvp

import android.os.Bundle
import android.widget.Toast
import yandexschool.dmpolyakov.money.di.base.Injectable

import yandexschool.dmpolyakov.money.di.base.InjectableFragment
import yandexschool.dmpolyakov.money.ui.base.LogNamed

abstract class BaseMvpFragment<Presenter : BaseMvpPresenter<*>>
    : InjectableFragment(), Injectable, LogNamed, BaseMvpView {

    private lateinit var presenter: Presenter

    protected abstract fun providePresenter(): Presenter

    protected fun getPresenter(): Presenter {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = providePresenter()
    }

    override fun showToast(stringId: Int) {
        Toast.makeText(activity, stringId, Toast.LENGTH_SHORT).show()
    }

    override fun showToast(string: String) {
        Toast.makeText(activity, string, Toast.LENGTH_SHORT).show()
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(context, throwable.message, Toast.LENGTH_SHORT).show()
    }
}