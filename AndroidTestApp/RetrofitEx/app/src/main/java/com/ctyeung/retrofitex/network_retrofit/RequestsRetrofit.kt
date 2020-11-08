package com.ctyeung.networkrequestex.network_retrofit

import android.widget.Toast
import com.ctyeung.retrofitex.data.Run
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Exception
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class RequestsRetrofit {
    lateinit var run:String
    open var refresh:((String, String)->Unit)? = null

    constructor(refresh:((String, String)->Unit)?=null) {
        this.refresh = refresh
    }

    fun getRun() = runBlocking {

        var scope = CoroutineScope(Dispatchers.IO).launch {
            try {
                var apiService = RetrofitBuilder.apiService
                run = apiService.getRun(1)
            } catch (e: Exception) {
                throw Exception(e.toString())
            }
        }
        scope.join()
        //refresh?.invoke("size:${list?.size}", getElapsedString())
    }

    fun postRun() = runBlocking {

        var scope = CoroutineScope(Dispatchers.IO).launch {
            try {
                var apiService = RetrofitBuilder.apiService
                val run = Run(1, "runner", "stepssss")
                apiService.postRun(run).enqueue(
                    object : Callback<Run> {
                        override fun onFailure(call: Call<Run>, t: Throwable) {
                            // Handle result
                        }
                        override fun onResponse( call: Call<Run>, response: Response<Run>) {
                            val addedUser = response.body()
                            // Handle result
                        }
                    }
                )
            } catch (e: Exception) {
                throw Exception(e.toString())
            }
        }
        scope.join()
        //refresh?.invoke("size:${list?.size}", getElapsedString())
    }
}