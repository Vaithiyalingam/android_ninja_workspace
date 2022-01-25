package com.vaithidroid.appone.designstringtask.models

import com.google.gson.annotations.SerializedName

data class Users(
    @SerializedName("data")
    val data: MutableList<Data>,
)