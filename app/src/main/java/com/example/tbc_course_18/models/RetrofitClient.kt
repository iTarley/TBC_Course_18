package com.example.tbc_course_18.models

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


object RetrofitClient{
    private const val BASE_URL = "https://run.mocky.io/v3/"
    private val retrofitBuilder by lazy{
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    fun getInformation(): Apartments = retrofitBuilder.create(Apartments::class.java)
}


interface Apartments{
    @GET("f4864c66-ee04-4e7f-88a2-2fbd912ca5ab")
    suspend fun getInfo(): Response<ApartmentsModel>
}