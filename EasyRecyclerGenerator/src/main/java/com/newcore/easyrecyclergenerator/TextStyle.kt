package com.newcore.easyrecyclergenerator

import android.graphics.Typeface

data class TextStyle(
    val textSizeInSp: Double,
    val fontStyle: FontStyles = FontStyles.NORMAL,
    val fontFamily: Typeface? = Typeface.DEFAULT,
) {
    companion object {
        fun default() = TextStyle(14.0, FontStyles.NORMAL)
    }
}