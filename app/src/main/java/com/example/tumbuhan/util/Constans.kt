package com.example.alcatel_dasar_android.data

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat.getSystemService

const val PLANT_DATA_FILENAME = "plants.json"

object Util {
    fun isNetWorkConnected(context: Context) : Boolean{
        val connectivityManager=context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected

    }
}