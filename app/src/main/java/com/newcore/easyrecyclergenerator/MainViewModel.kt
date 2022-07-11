package com.newcore.easyrecyclergenerator

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val list by lazy {
        MutableList(10) { "ahmed $it" }
    }
}