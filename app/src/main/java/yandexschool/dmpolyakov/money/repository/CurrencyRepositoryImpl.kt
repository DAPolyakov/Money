package yandexschool.dmpolyakov.money.repository

import io.reactivex.Single
import yandexschool.dmpolyakov.money.api.CurrencyApi
import javax.inject.Inject


class CurrencyRepositoryImpl @Inject constructor(var currencyApi: CurrencyApi) : CurrencyRepository {

    override fun getRatio(currencyFrom: String, currencyTo: String): Single<Double> {
        return currencyApi.getRatio("${currencyFrom}_$currencyTo").map {
            it.substring(it.indexOf(':') + 1, it.length - 1).toDouble()
        }
    }

}