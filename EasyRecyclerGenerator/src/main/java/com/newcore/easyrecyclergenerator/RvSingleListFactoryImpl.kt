package com.newcore.easyrecyclergenerator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

@Suppress("UNCHECKED_CAST")
class RvSingleListFactoryImpl<T : ViewBinding, L>(
    private val binding: (LayoutInflater, ViewGroup, Boolean) -> T,
    private val children: List<L>,
) : RvSingleListFactory<T, L> {

    var filteredList = children;

    /**
     * binding : binding inflate functions
     *      ex: binding = ItemViewBinding::inflate  where ItemView is layout
     * children : list of recyclerView data
     * generator : used for bind data into view
     *      ex: adding onClickListener or setText to view etc...
     *
     * return list of ViewGeneratorHolder it is reference of data in recycler just used for
     * getting data or view or remove it from recycler
     */
    override fun listBuilder(
        generator: ((T, L) -> Unit)?,
    ): List<L> {
        rvListAdapter.viewHolder = generator
        return children.also { rvListAdapter.children = it }
    }


    override fun filter(predicate: (L) -> Boolean): List<L> {
        rvListAdapter.children = children.filter {
            try {
                predicate(it)
            } catch (e: Throwable) {
                true
            }
        }
        return rvListAdapter.children
    }


    override fun start(
        recyclerView: RecyclerView,
        customLayoutManager: RecyclerView.LayoutManager?,
    ) {
        recyclerView.apply {
            adapter = rvListAdapter
            layoutManager = customLayoutManager ?: LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false,
            )
        }
    }


    private val rvListAdapter = RvSingleList<T, L>(binding)


}

