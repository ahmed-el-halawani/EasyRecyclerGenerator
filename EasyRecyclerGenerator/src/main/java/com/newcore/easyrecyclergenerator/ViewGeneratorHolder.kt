package com.newcore.easyrecyclergenerator

import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding

class ViewGeneratorHolder<T, L>(
    private val binding: ((LayoutInflater) -> T)? = null,
    private val layoutId: Int = -1,
    val data: L,
    private val layoutInflater: LayoutInflater,
    private val generator: (T, L) -> Unit,
) {
    var view: View? = null
        private set

    companion object {
        fun <T, L> generateView(v: ViewGeneratorHolder<T, L>): View {
            v.run {
                if (view != null) return view!!

                view = if (layoutId == -1)
                    (binding!!(layoutInflater)
                        .apply { generator(this, data) } as ViewBinding).root
                else
                    layoutInflater.inflate(layoutId, null, false)
                        .apply { generator(this as T, data) }

                return view!!
            }
        }
    }
}