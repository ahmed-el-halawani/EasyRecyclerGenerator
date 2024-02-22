package com.newcore.core

import android.view.View

interface ViewContainer {
    fun <T : View> findViewById(id: Int): T
}
