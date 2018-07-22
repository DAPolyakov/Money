package yandexschool.dmpolyakov.money.mvp


abstract class BasePresenter<T : MvpView> : MvpPresenter<T> {

    private var view: T? = null
    protected var isLoading: Boolean = false

    override fun attachView(mvpView: T) {
        view = mvpView
    }

    override fun detachView() {
        view = null
    }

    override fun destroy() {
    }

    fun getView(): T? {
        return view
    }

    protected fun isViewAttached(): Boolean {
        return view != null
    }

}