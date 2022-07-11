package com.newcore.easyrecyclergenerator

import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding

class ViewGeneratorHolder<T, L>(
    private val binding: ((LayoutInflater) -> T)?,
    private val viewId: Int = -1,
    val data: L,
    private val layoutInflater: LayoutInflater,
    private val generator: (T, L) -> Unit,
) {
    val view: View by lazy {
        if (viewId == -1) {
            (binding!!(layoutInflater).apply {
                generator(this, data)
            } as ViewBinding).root
        } else {
            layoutInflater.inflate(viewId, null, false).apply {
                generator(this as T, data)
            }
        }

    }
}