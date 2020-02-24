package com.pmesa.chiperreddit.common

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflate(id: Int): View =
    LayoutInflater.from(context).inflate(id, this, false)

fun debug(tag:String, message: String) = Log.d(tag, message)