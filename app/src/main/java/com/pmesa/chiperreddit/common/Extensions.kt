package com.pmesa.chiperreddit.common

import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflate(id: Int): View =
    LayoutInflater.from(context).inflate(id, this, false)

fun debug(tag:String, message: String) = Log.d(tag, message)

fun Bitmap.getAvgRgb(): RGB {

    var redColors = 0
    var greenColors = 0
    var blueColors = 0
    var pixelCount = 0

    for (y in 0 until this.height) {
        for (x in 0 until this.width) {
            val c = this.getPixel(x, y)
            pixelCount++
            redColors += Color.red(c)
            greenColors += Color.green(c)
            blueColors += Color.blue(c)
        }
    }
    return RGB(redColors / pixelCount, greenColors / pixelCount, blueColors / pixelCount)
}

data class RGB(val red: Int, val green: Int, val blue: Int) {
    fun needContrast(): Boolean {
        return red + green + blue < THRESHOLD
    }

    companion object{
        const val THRESHOLD = 300
    }
}