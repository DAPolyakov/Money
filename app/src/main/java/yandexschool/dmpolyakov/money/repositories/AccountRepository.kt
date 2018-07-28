package yandexschool.dmpolyakov.money.repositories

import io.reactivex.Observable
import io.reactivex.Single
import yandexschool.dmpolyakov.money.Currency
import yandexschool.dmpolyakov.money.models.Account


class AccountRepository() {

    private val fakeAccounts = ArrayList<Account>()

    init {
        createFakeAccounts()
    }

    fun getAccounts(): Single<ArrayList<Account>> =
            Single.fromObservable(Observable.fromArray(fakeAccounts))

    private fun createFakeAccounts() {
        fakeAccounts.add(Account("1", "Рабочий", 20700.toBigDecimal(), Currency.Rubble))
        fakeAccounts.add(Account("2", "Копилочка", 256000.toBigDecimal(), Currency.Rubble))
        fakeAccounts.add(Account("3", "Заграничный", 500.toBigDecimal(), Currency.Dollar))
    }

}