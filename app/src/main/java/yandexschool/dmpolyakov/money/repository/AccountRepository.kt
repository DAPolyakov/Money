package yandexschool.dmpolyakov.money.repository

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.subjects.Subject
import yandexschool.dmpolyakov.money.models.Account
import yandexschool.dmpolyakov.money.models.FinanceOperation


interface AccountRepository {

    val subjectFakeAccounts: Subject<List<Account>>

    fun getAccounts(): Single<ArrayList<Account>>
    fun addAccount(account: Account): Completable
    fun getAccount(id: String): Single<Account>
    fun addFinanceOperation(accountId: String, operation: FinanceOperation): Completable
    fun renameAccount(accountId: String, title: String): Completable
}