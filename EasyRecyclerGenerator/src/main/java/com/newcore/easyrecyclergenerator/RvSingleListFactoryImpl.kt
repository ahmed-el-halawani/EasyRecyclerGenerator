package com.newcore.easyrecyclergenerator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

@Suppress("UNCHECKED_CAST")
class RvSingleListFactoryImpl<T : ViewBinding, L : Any>(
    private val binding: (LayoutInflater, ViewGroup, Boolean) -> T,
    private var children: List<L>,
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
        return children.also { rvListAdapter.diffUtil.submitList(it) }
    }


    override fun filter(predicate: (L) -> Boolean): List<L> {
        return children.filter { predicate(it) }.also {
            rvListAdapter.diffUtil.submitList(it)
        }
    }

    override fun take(take: Int?): List<L> {
        return (take?.let { children.take(it) } ?: children).also {
            rvListAdapter.diffUtil.submitList(it)
        }
    }

    override fun doOnList(doIt: (List<L>) -> List<L>) {
        children = doIt(children)
        rvListAdapter.diffUtil.submitList(children)
    }

    override fun addList(list: List<L>) {
        children = children.plus(list)
        rvListAdapter.diffUtil.apply {
            submitList(children)
        }
    }

    override fun addItem(item: L) {
        children.plus(item).also {
            children = it
            rvListAdapter.diffUtil.submitList(it)
        }
    }

    override fun setList(list: List<L>) {
        children = list
        rvListAdapter.diffUtil.submitList(children)
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

