package com.ctyeung.networkrequestex.network_retrofit

import android.telecom.Call
import com.ctyeung.retrofitex.data.Run
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("run/{index}")
    suspend fun getRun(@Path("index") index:Int): String

    @POST("run")
    suspend fun postRun(@Body run:Run):retrofit2.Call<Run>
}