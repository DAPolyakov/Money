package yandexschool.dmpolyakov.money.repository

import io.reactivex.Single


interface CurrencyRepository {

    fun getRatio(currencyFrom: String, currencyTo: String): Single<Double>
}