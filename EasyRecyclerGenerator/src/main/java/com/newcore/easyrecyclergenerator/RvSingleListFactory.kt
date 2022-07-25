package com.newcore.easyrecyclergenerator

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

interface RvSingleListFactory<T : ViewBinding, L> {


    /**
     * layoutId : layout resource id
     *      ex: layoutId = R.layout.layout_id
     * children : list of recyclerView data
     * generator : function used for bind data into view
     *      ex: adding onClickListener or setText to view etc...
     *
     * return list of ViewGeneratorHolder it is reference of data in recycler just used for
     * getting data or view or remove it from recycler
     */

    fun listBuilder(
        generator: ((T, L) -> Unit)?,
    ): List<L>

    fun filter(predicate: (L) -> Boolean): List<L>

    fun take(take: Int): List<L>


    fun start(recyclerView: RecyclerView, customLayoutManager: RecyclerView.LayoutManager? = null)
}

