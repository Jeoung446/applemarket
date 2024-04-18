package com.example.applemarket

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MyItem(
    val aIcon:Int, val aName: String,
    val aAdress: String, val aPrice:String, val aProduct: String,
    val aId: String,
    var aGood: Int,
    var aTrue: Boolean = false) : Parcelable



