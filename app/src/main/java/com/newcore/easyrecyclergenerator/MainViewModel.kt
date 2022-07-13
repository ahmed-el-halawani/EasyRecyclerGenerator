package com.newcore.easyrecyclergenerator

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val list by lazy {
        MutableList(1000) { "ahmed $it" }
    }
}