package com.newcore.easyrecyclergenerator

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

fun Fragment.rvList(
    recyclerView: RecyclerView,
    layoutManager: RecyclerView.LayoutManager? = null,
    isLazyLoading: Boolean = true,
    rvListFactory: RvListFactory.() -> Unit,
): RvListFactory {
    return RvListFactoryImpl(layoutInflater, isLazyLoading).apply {
        rvListFactory(this)
        start(recyclerView, layoutManager)
    }
}

fun Activity.rvList(
    recyclerView: RecyclerView,
    layoutManager: RecyclerView.LayoutManager? = null,
    isLazyLoading: Boolean = true,
    rvListFactory: RvListFactory .() -> Unit,
): RvListFactory {
    return RvListFactoryImpl(layoutInflater, isLazyLoading).apply {
        rvListFactory(this)
        start(recyclerView, layoutManager)
    }
}


fun <T : ViewBinding, L : Any> Fragment.rvSingleList(
    recyclerView: RecyclerView,
    binding: (LayoutInflater, ViewGroup, Boolean) -> T,
    children: List<L>,

    layoutManager: RecyclerView.LayoutManager? = null,
    rvListFactory: RvSingleListFactory<T, L>.() -> Unit,
): RvSingleListFactoryImpl<T, L> {
    return RvSingleListFactoryImpl(binding, children).apply {
        rvListFactory(this)
        start(recyclerView, layoutManager)
    }
}

fun <T : ViewBinding, L : Any> Activity.rvSingleList(
    recyclerView: RecyclerView,
    binding: (LayoutInflater, ViewGroup, Boolean) -> T,
    children: List<L>,

    layoutManager: RecyclerView.LayoutManager? = null,
    rvListFactory: (RvSingleListFactory<T, L>.() -> Unit)? = null,
): RvSingleListFactoryImpl<T, L> {
    return RvSingleListFactoryImpl(binding, children).apply {
        rvListFactory?.invoke(this)
        start(recyclerView, layoutManager)
    }
}

