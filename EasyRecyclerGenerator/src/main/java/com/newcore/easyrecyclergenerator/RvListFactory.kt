package com.newcore.easyrecyclergenerator

import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.newcore.core.ViewContainer

interface RvListFactory {

    fun findViewById(id: Int): View?

    /**
     * binding : binding inflate functions
     *      ex: binding = ItemViewBinding::inflate  where ItemView is layout
     * children :  recyclerView data
     * generator : used for bind data into view
     *      ex: adding onClickListener or setText to view etc...
     *
     * return ViewGeneratorHolder it is reference of data in recycler just used for
     * getting data or view or remove it from recycler
     */
    fun <T : ViewBinding, L> addItem(
        binding: (LayoutInflater) -> T,
        child: L,

        generator: ((T, L) -> Unit)? = null,
    ): ViewGeneratorHolder<T, L>


    /**
     * layoutId : layout resource id
     *      ex: layoutId = R.layout.layout_id
     * data : recyclerView data
     * generator : function used for bind data into view
     *      ex: adding onClickListener or setText to view etc...
     *
     * return ViewGeneratorHolder it is reference of data in recycler just used for
     *      getting data or view or remove it from recycler
     */
    fun <L> addItem(
        @LayoutRes layoutId: Int,
        child: L,
        generator: ((View, L) -> Unit)? = null,
    ): ViewGeneratorHolder<View, L>

    /**
     * it give you more flexibility to create view
     * item : object of ViewGeneratorHolder
     *
     * return object of ViewGeneratorHolder it is reference of data in recycler just used for
     *      getting data or view or remove it from recycler
     */
    fun <T, L> addItem(
        item: ViewGeneratorHolder<T, L>,
    ): ViewGeneratorHolder<T, L>

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
    fun <T : ViewBinding, L> listBuilder(
        binding: (LayoutInflater) -> T,
        children: List<L>,
        generator: ((T, L) -> Unit)? = null,
    ): List<ViewGeneratorHolder<T, L>>

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

    fun <L> listBuilder(
        @LayoutRes layoutId: Int,
        children: List<L>,
        generator: ((View, L) -> Unit)? = null,
    ): List<ViewGeneratorHolder<View, L>>

    /**
     * it give you more flexibility to create view
     * items : list of ViewGeneratorHolder
     *
     * return list of ViewGeneratorHolder it is reference of data in recycler just used for
     *      getting data or view or remove it from recycler
     */


    fun <T, L> addItems(
        items: List<ViewGeneratorHolder<T, L>>,
    ): List<ViewGeneratorHolder<T, L>>


    fun <L> filter(predicate: (L) -> Boolean): List<ViewGeneratorHolder<*, *>>

    fun removeItemWithViewGenerator(viewGenerator: ViewGeneratorHolder<*, *>)
    fun removeItemWithPosition(position: Int)
    fun removeItemWithData(data: Any)
    fun removeAll()
    fun start(recyclerView: RecyclerView, customLayoutManager: RecyclerView.LayoutManager? = null)
}

fun RvListFactory.rvListLayoutView(view: View) = object : ViewContainer {
    override fun <T : View> findViewById(id: Int): T {
        return view as T;
    }
}

