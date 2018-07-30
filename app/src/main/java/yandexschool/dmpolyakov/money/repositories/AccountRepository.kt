package yandexschool.dmpolyakov.money.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject
import yandexschool.dmpolyakov.money.Currency
import yandexschool.dmpolyakov.money.OperationCategory
import yandexschool.dmpolyakov.money.OperationType
import yandexschool.dmpolyakov.money.models.Account
import yandexschool.dmpolyakov.money.models.FinanceOperation


class AccountRepository() {

    private val fakeAccounts = ArrayList<Account>()
    val subjectFakeAccounts: Subject<List<Account>> = BehaviorSubject.create<List<Account>>()

    init {
        createFakeAccounts()
    }

    fun getAccounts(): Single<ArrayList<Account>> =
            Single.fromObservable(Observable.fromArray(fakeAccounts))

    fun addAccount(account: Account): Completable {
        fakeAccounts.add(account.copy(id = (fakeAccounts.size + 1).toString()))
        subjectFakeAccounts.onNext(fakeAccounts)
        return Completable.complete()
    }

    fun getAccount(id: String): Single<Account> {
        return Single.just(fakeAccounts.find { it.id == id }
                ?: throw Exception("Account not found"))
    }

    fun addFinanceOperation(accountId: String, operation: FinanceOperation): Completable {
        val account = fakeAccounts.find { it.id == accountId }
                ?: throw Exception("Account not found")

        account.addFinanceOperation(operation)
        subjectFakeAccounts.onNext(fakeAccounts)
        return Completable.complete()
    }

    fun rename(accountId: String, title: String): Completable {
        val account = fakeAccounts.find { it.id == accountId }
                ?: throw Exception("Account not found")

        account.title = title
        subjectFakeAccounts.onNext(fakeAccounts)
        return Completable.complete()
    }

    private fun createFakeAccounts() {

        val operations = ArrayList<FinanceOperation>()
        operations.add(FinanceOperation(
                "Магазин у Петра", 750.toBigDecimal(), OperationType.Expense, OperationCategory.Products, Currency.Rubble, "25.07.2018", "1"))
        operations.add(FinanceOperation(
                "Аренда ламзаков", 20000.toBigDecimal(), OperationType.Expense, OperationCategory.Other, Currency.Rubble, "26.07.2018", "2"))
        operations.add(FinanceOperation(
                "Доход с аренды ламзаков", 35000.toBigDecimal(), OperationType.Income, OperationCategory.Salary, Currency.Rubble, "27.07.2018", "3"))

        fakeAccounts.add(Account("Рабочий", 20700.toBigDecimal(), Currency.Rubble, ArrayList(operations), "1"))
        fakeAccounts.add(Account("Копилочка", 256000.toBigDecimal(), Currency.Rubble, ArrayList(operations), "2"))
        fakeAccounts.add(Account("Заграничный", 500.toBigDecimal(), Currency.Dollar, ArrayList(operations), "3"))
    }

}