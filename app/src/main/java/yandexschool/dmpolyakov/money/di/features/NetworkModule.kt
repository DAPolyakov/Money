package yandexschool.dmpolyakov.money.di.features

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import yandexschool.dmpolyakov.money.api.CurrencyApi
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    private val DEFAULT_CONNECT_TIMEOUT = 30000L
    private val API_BASE_URL = "http://free.currencyconverterapi.com/"

    @Singleton
    @Provides
    fun provideCurrencyApi(): CurrencyApi = retrofit.create(CurrencyApi::class.java)

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
                .connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .build()
    }

    private val moshi: Moshi by lazy {
        Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(okHttpClient)
                .baseUrl(API_BASE_URL)
                .build()
    }

}