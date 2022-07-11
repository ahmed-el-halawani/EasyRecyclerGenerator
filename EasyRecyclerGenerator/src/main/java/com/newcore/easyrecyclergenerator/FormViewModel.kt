package com.newcore.easyrecyclergenerator

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.newcore.easy_recycler_generator.RvListFactory


fun Fragment.rvList(
    recyclerView: RecyclerView,
    layoutManager: RecyclerView.LayoutManager? = null,
    rvListFactory: RvListFactory.() -> Unit,
): RvListFactory {
    return RvListFactory(requireContext().applicationContext).apply {
        rvListFactory(this)
        start(recyclerView, layoutManager)
    }
}


fun Activity.rvList(
    recyclerView: RecyclerView,
    layoutManager: RecyclerView.LayoutManager? = null,
    rvListFactory: RvListFactory.() -> Unit,
): RvListFactory {
    return RvListFactory(applicationContext).apply {
        rvListFactory(this)
        start(recyclerView, layoutManager)
    }
}


