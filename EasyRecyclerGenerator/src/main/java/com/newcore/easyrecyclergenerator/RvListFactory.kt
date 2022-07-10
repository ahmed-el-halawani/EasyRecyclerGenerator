package com.newcore.easy_recycler_generator

import android.content.Context
import android.view.LayoutInflater
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


    fun remove(view: ViewGeneratorHolder<*, *>) {
        rvListAdapter.remove(view)
    }

    fun remove(position: Int) {
        rvListAdapter.remove(position)
    }

    fun <T : ViewBinding, L> addItem(
        binding: (LayoutInflater) -> T,
        child: L,
        generator: (T, L) -> Unit,
    ) {
        rvListAdapter.add(
            ViewGeneratorHolder(binding, child, layoutInflater, generator)
        )
    }

    fun <T : ViewBinding, L> listBuilder(
        binding: (LayoutInflater) -> T,
        children: List<L>,
        generator: (T, L) -> Unit,
    ) {
        rvListAdapter.addAll(
            children.map {
                ViewGeneratorHolder(binding, it, layoutInflater, generator)
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

