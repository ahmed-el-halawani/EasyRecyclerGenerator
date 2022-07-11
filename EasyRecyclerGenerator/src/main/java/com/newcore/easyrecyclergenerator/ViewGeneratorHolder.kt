package com.newcore.easyrecyclergenerator

import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding

class ViewGeneratorHolder<T : ViewBinding, L>(
    private val binding: (LayoutInflater) -> T,
    val data: L,
    private val layoutInflater: LayoutInflater,
    private val generator: ((T, L) -> Unit)? = null,
) {
    val view by lazy {
        binding(layoutInflater).apply {
            generator?.invoke(this, data)
        }.root
    }
}