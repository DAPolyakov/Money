package yandexschool.dmpolyakov.money.network.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface CurrencyApi {

    @GET("api/v6/convert?compact=ultra")
    fun getRatio(
            @Query("q") currencies: String): Single<String>

}