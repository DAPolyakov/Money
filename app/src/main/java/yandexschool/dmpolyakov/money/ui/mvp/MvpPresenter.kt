package yandexschool.dmpolyakov.money.ui.mvp


interface MvpPresenter<in V : MvpView> {
    fun attachView(mvpView: V)
    fun viewIsReady()
    fun detachView()
    fun destroy()
}