package com.kodegakure.sistempemesanancleaningservice.api

import com.kodegakure.sistempemesanancleaningservice.model.Register
import com.kodegakure.sistempemesanancleaningservice.model.Login
import com.kodegakure.sistempemesanancleaningservice.model.LoginResponse
import com.kodegakure.sistempemesanancleaningservice.model.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

class NetworkConfiguration {
    private fun getClient(): Retrofit {
        val localEndpoint = "http://10.0.2.2:8000/api/v1/"

        return Retrofit.Builder()
            .baseUrl(localEndpoint)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService(): Endpoint = getClient().create(Endpoint::class.java)
}

interface Endpoint {
    @Headers("Accept: application/json")
    @POST("register")
    fun register(@Body registerRequest: Register): Call<Response>

    @Headers("Accept: application/json")
    @POST("login")
    fun login(@Body loginRequest: Login): Call<LoginResponse>


}