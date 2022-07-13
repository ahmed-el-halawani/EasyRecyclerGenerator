package com.newcore.easyrecyclergenerator

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

fun Fragment.rvList(
    recyclerView: RecyclerView,
    layoutManager: RecyclerView.LayoutManager? = null,
    rvListFactory: RvListFactory.() -> Unit,
): RvListFactory {
    return RvListFactoryImpl(layoutInflater).apply {
        rvListFactory(this)
        start(recyclerView, layoutManager)
    }
}

fun Activity.rvList(
    recyclerView: RecyclerView,
    layoutManager: RecyclerView.LayoutManager? = null,
    rvListFactory: RvListFactory.() -> Unit,
): RvListFactory {
    return RvListFactoryImpl(layoutInflater).apply {
        rvListFactory(this)
        start(recyclerView, layoutManager)
    }
}


