package com.example.islamiapplication.api
import com.example.islamiapplication.model.RadioResponse
import retrofit2.Call
import retrofit2.http.GET
interface RadioService {

    @GET("radios")
    fun getRadios():Call<RadioResponse>



}