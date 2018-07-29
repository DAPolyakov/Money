package yandexschool.dmpolyakov.money.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import yandexschool.dmpolyakov.money.Currency
import yandexschool.dmpolyakov.money.OperationType
import yandexschool.dmpolyakov.money.models.Account
import yandexschool.dmpolyakov.money.models.FinanceOperation


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

        val operations = ArrayList<FinanceOperation>()
        operations.add(FinanceOperation(
                "Магазин у Петра", 750.toBigDecimal(), OperationType.Paid, Currency.Rubble, "25.07.2018", "1"))
        operations.add(FinanceOperation(
                "Аренда ламзаков", 20000.toBigDecimal(), OperationType.Paid, Currency.Rubble, "26.07.2018", "2"))
        operations.add(FinanceOperation(
                "Доход с аренды ламзаков", 35000.toBigDecimal(), OperationType.Income, Currency.Rubble, "27.07.2018", "3"))

        fakeAccounts.add(Account("Рабочий", 20700.toBigDecimal(), Currency.Rubble, operations, "1"))
        fakeAccounts.add(Account("Копилочка", 256000.toBigDecimal(), Currency.Rubble, emptyList(), "2"))
        fakeAccounts.add(Account("Заграничный", 500.toBigDecimal(), Currency.Dollar, operations, "3"))
    }

}