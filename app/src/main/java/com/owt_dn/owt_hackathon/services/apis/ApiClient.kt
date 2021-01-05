package com.owt_dn.owt_hackathon.services.apis

import com.owt_dn.owt_hackathon.environments.Environment
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private var retrofit: Retrofit? = null

    private val builder = Retrofit.Builder()
        .baseUrl(Environment.Api.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    private val httpClient = OkHttpClient.Builder()

    fun <C> createService(serviceClass: Class<C>): C? {
        builder.client(httpClient.build())
        retrofit = builder.build()
        return retrofit?.create(serviceClass)
    }
}