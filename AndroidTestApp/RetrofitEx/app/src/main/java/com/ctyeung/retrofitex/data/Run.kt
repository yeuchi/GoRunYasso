package com.ctyeung.retrofitex.data

import com.google.gson.annotations.SerializedName

data class Run(
    @SerializedName("index") val index:Int,
    @SerializedName("name") val name:String,
    @SerializedName("steps") val steps:String) {}