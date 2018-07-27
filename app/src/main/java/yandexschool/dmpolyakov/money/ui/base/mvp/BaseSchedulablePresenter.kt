package yandexschool.dmpolyakov.money.ui.base.mvp

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BaseSchedulablePresenter<View : MvpView> : MvpPresenter<View>() {

    enum class LifeLevel {
        PER_VIEW,
        PER_PRESENTER,
        PER_UI
    }

    private val viewDisposable = CompositeDisposable()
    private val uiDisposable = CompositeDisposable()
    private val presenterDisposable = CompositeDisposable()

    protected open fun bind(disposable: Disposable, level: LifeLevel) {
        when (level) {
            BaseSchedulablePresenter.LifeLevel.PER_VIEW -> viewDisposable.add(disposable)
            BaseSchedulablePresenter.LifeLevel.PER_PRESENTER -> presenterDisposable.add(disposable)
            BaseSchedulablePresenter.LifeLevel.PER_UI -> uiDisposable.add(disposable)
        }
    }

    override fun detachView(view: View) {
        super.detachView(view)
        if (attachedViews.size == 0) {
            viewDisposable.clear()
        }
    }

    override fun destroyView(view: View) {
        super.destroyView(view)
        uiDisposable.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (attachedViews.size == 0) {
            presenterDisposable.clear()
        }
    }

    protected fun <T> onUi(single: Single<T>): Single<T> {
        return single.observeOn(AndroidSchedulers.mainThread())
    }

    protected fun <T> onUi(observable: Observable<T>): Observable<T> {
        return observable.observeOn(AndroidSchedulers.mainThread())
    }

    protected fun onUi(completable: Completable): Completable {
        return completable.observeOn(AndroidSchedulers.mainThread())
    }

    protected fun <T> onUi(flowable: Flowable<T>): Flowable<T> {
        return flowable.observeOn(AndroidSchedulers.mainThread())
    }

}