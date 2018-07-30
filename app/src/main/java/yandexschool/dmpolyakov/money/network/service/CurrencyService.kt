package yandexschool.dmpolyakov.money.network.service

import io.reactivex.Single
import yandexschool.dmpolyakov.money.network.api.CurrencyApi


class CurrencyService(private val api: CurrencyApi) {

    fun getRatio(currencyFrom: String, currencyTo: String): Single<Double> {
        return api.getRatio("${currencyFrom}_$currencyTo").map {
            it.substring(it.indexOf(':') + 1, it.length - 1).toDouble()
        }
    }

}