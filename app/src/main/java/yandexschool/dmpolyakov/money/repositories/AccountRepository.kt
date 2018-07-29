package yandexschool.dmpolyakov.money.repositories

import io.reactivex.Completable
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

    fun addAccount(account: Account): Completable {
        fakeAccounts.add(account.copy(id = (fakeAccounts.size + 1).toString()))
        return Completable.complete()
    }

    fun getAccount(id: String): Single<Account> {
        return Single.just(fakeAccounts.find { it.id == id }
                ?: throw Exception("Account not found"))
    }

    private fun createFakeAccounts() {
        fakeAccounts.add(Account("Рабочий", 20700.toBigDecimal(), Currency.Rubble, "1"))
        fakeAccounts.add(Account("Копилочка", 256000.toBigDecimal(), Currency.Rubble, "2"))
        fakeAccounts.add(Account("Заграничный", 500.toBigDecimal(), Currency.Dollar, "3"))
    }

}