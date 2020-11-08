package com.ctyeung.retrofitex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.ctyeung.networkrequestex.network_retrofit.RequestsRetrofit
import com.ctyeung.retrofitex.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.layout = this

    }

    fun onClickGet() {
        try {
            var refreshUI : (String, String) -> Unit = {
                count: String, elapsed: String ->
                binding.txtRetrofitUserCount.text = elapsed
                binding.txtRetrofitElapsed.text = count
            }

            var request = RequestsRetrofit(refreshUI)
            request.getRun()
        }
        catch(ex: Exception) {
            Toast.makeText(this, "TryRxRetrofit() $ex", Toast.LENGTH_LONG).show()
        }
    }

    fun onClickPost() {
        var refreshUI : (String, String) -> Unit = {
            msg1: String, msg2: String ->
            binding.txtRxRetrofitMsg1.text = msg1
            binding.txtRxRetrofitMsg2.text = msg2
        }

        var request = RequestsRetrofit(refreshUI)
        request.postRun()
    }
}