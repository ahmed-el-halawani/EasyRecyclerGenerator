package com.newcore.easy_recycler_generator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.newcore.easyrecyclergenerator.RvList
import com.newcore.easyrecyclergenerator.ViewGeneratorHolder

class RvListFactory(context: Context) {

    private val rvListAdapter = RvList()


    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    fun start(recyclerView: RecyclerView, customLayoutManager: RecyclerView.LayoutManager? = null) {
        recyclerView.apply {
            adapter = rvListAdapter
            layoutManager = customLayoutManager ?: LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false,
            )
        }
    }


    fun removeItem(view: ViewGeneratorHolder<*, *>) {
        rvListAdapter.remove(view)
    }

    fun removeItem(position: Int) {
        rvListAdapter.remove(position)
    }

    fun removeItem(data: Any) {
        rvListAdapter.remove(data)
    }

    fun <T : ViewBinding, L> addItem(
        binding: (LayoutInflater) -> T,
        child: L,
        generator: (T, L) -> Unit,
    ) {
        rvListAdapter.add(
            ViewGeneratorHolder(binding, -1, child, layoutInflater, generator)
        )
    }

    fun <T : View, L> addItem(
        @LayoutRes layoutId: Int,
        child: L,
        generator: (T, L) -> Unit,
    ) {
        rvListAdapter.add(
            ViewGeneratorHolder(null, layoutId, child, layoutInflater, generator)
        )
    }

    fun <T : ViewBinding, L> listBuilder(
        binding: (LayoutInflater) -> T,
        children: List<L>,
        generator: (T, L) -> Unit,
    ) {
        rvListAdapter.addAll(
            children.map {
                ViewGeneratorHolder(binding, -1, it, layoutInflater, generator)
            }
        )
    }

    fun <T : View, L> listBuilder(
        @LayoutRes layoutId: Int,
        children: List<L>,
        generator: (T, L) -> Unit,
    ) {
        rvListAdapter.addAll(
            children.map {
                ViewGeneratorHolder(null, layoutId, it, layoutInflater, generator)
            }
        )
    }


}

//
//fun <T : ViewBinding, L> Fragment.rvList(
//    binding: (LayoutInflater) -> T,
//    children: List<L>,
//    recyclerView: RecyclerView,
//    generator: (T, L) -> Unit,
//) {
//    val rvListAdapter = RvList()
//    val view = binding(layoutInflater)
//    rvListAdapter.addAll(
//        children.map {
//            generator(view, it)
//            view.root
//        }
//    )
//    recyclerView.apply {
//        adapter = rvListAdapter
//        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//    }
//}

