package yandexschool.dmpolyakov.money.repositories

import io.reactivex.Observable
import io.reactivex.Single
import yandexschool.dmpolyakov.money.Currency
import yandexschool.dmpolyakov.money.models.Account


class AccountRepository() {

    fun getAccounts() = getFakeAccounts()

    private fun getFakeAccounts(): Single<List<Account>> {
        val list = ArrayList<Account>()

        list.add(Account("1", "Рабочий", 20700.toBigDecimal(), Currency.Rubble))
        list.add(Account("2", "Копилочка", 256000.toBigDecimal(), Currency.Rubble))
        list.add(Account("3", "Заграничный", 500.toBigDecimal(), Currency.Dollar))

        list.add(Account("1", "Рабочий", 20700.toBigDecimal(), Currency.Rubble))
        list.add(Account("2", "Копилочка", 256000.toBigDecimal(), Currency.Rubble))
        list.add(Account("3", "Заграничный", 500.toBigDecimal(), Currency.Dollar))

        list.add(Account("1", "Рабочий", 20700.toBigDecimal(), Currency.Rubble))
        list.add(Account("2", "Копилочка", 256000.toBigDecimal(), Currency.Rubble))
        list.add(Account("3", "Заграничный", 500.toBigDecimal(), Currency.Dollar))

        list.add(Account("1", "Рабочий", 20700.toBigDecimal(), Currency.Rubble))
        list.add(Account("2", "Копилочка", 256000.toBigDecimal(), Currency.Rubble))
        list.add(Account("3", "Заграничный", 500.toBigDecimal(), Currency.Dollar))

        list.add(Account("1", "Рабочий", 20700.toBigDecimal(), Currency.Rubble))
        list.add(Account("2", "Копилочка", 256000.toBigDecimal(), Currency.Rubble))
        list.add(Account("3", "Заграничный", 500.toBigDecimal(), Currency.Dollar))

        return Single.fromObservable(Observable.fromArray(list))
    }
}