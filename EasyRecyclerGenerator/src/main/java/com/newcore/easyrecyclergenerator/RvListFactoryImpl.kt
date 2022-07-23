package com.newcore.easyrecyclergenerator

import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

@Suppress("UNCHECKED_CAST")
class RvListFactoryImpl(
    private val layoutInflater: LayoutInflater,
    private val isLazyLoading: Boolean = true,
) : RvListFactory {

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
    override fun <T : ViewBinding, L> addItem(
        binding: (LayoutInflater) -> T,
        child: L,
        generator: ((T, L) -> Unit)?,
    ): ViewGeneratorHolder<T, L> {
        return addItem(
            ViewGeneratorHolder(
                binding = binding,
                data = child,
                layoutInflater = layoutInflater,
                generator = generator
            )
        )
    }


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
    override fun <L> addItem(
        @LayoutRes layoutId: Int,
        child: L,
        generator: ((View, L) -> Unit)?,
    ): ViewGeneratorHolder<View, L> {
        return addItem(ViewGeneratorHolder(
            layoutId = layoutId,
            data = child,
            layoutInflater = layoutInflater,
            generator = generator
        ))
    }

    /**
     * it give you more flexibility to create view
     * item : object of ViewGeneratorHolder
     *
     * return object of ViewGeneratorHolder it is reference of data in recycler just used for
     *      getting data or view or remove it from recycler
     */
    override fun <T, L> addItem(
        item: ViewGeneratorHolder<T, L>,
    ): ViewGeneratorHolder<T, L> {
        return item.also {
            rvListAdapter.add(it)
            if (!isLazyLoading)
                ViewGeneratorHolder.generateView(it)
        }
    }


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
    override fun <T : ViewBinding, L> listBuilder(
        binding: (LayoutInflater) -> T,
        children: List<L>,
        generator: ((T, L) -> Unit)?,
    ): List<ViewGeneratorHolder<T, L>> {
        return addItems(children.map {
            ViewGeneratorHolder(
                binding = binding,
                data = it,
                layoutInflater = layoutInflater,
                generator = generator
            )
        })
    }

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

    override fun <L> listBuilder(
        @LayoutRes layoutId: Int,
        children: List<L>,
        generator: ((View, L) -> Unit)?,
    ): List<ViewGeneratorHolder<View, L>> {
        return addItems(children.map {
            ViewGeneratorHolder(
                layoutId = layoutId,
                data = it,
                layoutInflater = layoutInflater,
                generator = generator
            )
        })

    }

    /**
     * it give you more flexibility to create view
     * items : list of ViewGeneratorHolder
     *
     * return list of ViewGeneratorHolder it is reference of data in recycler just used for
     *      getting data or view or remove it from recycler
     */
    override fun <T, L> addItems(
        items: List<ViewGeneratorHolder<T, L>>,
    ): List<ViewGeneratorHolder<T, L>> {
        return items.also {
            rvListAdapter.addAll(it)
            if (!isLazyLoading)
                for (i in it)
                    ViewGeneratorHolder.generateView(i)
        }
    }

    override fun <L> filter(predicate: (L) -> Boolean): List<ViewGeneratorHolder<*, *>> {
        return rvListAdapter.children.filter {
            try {
                predicate(it.data as L)
            } catch (e: Throwable) {
                true
            }
        }.also { rvListAdapter.replace(it) }
    }

    override fun removeAll() {
        rvListAdapter.removeAll()
    }

    override fun removeItemWithViewGenerator(viewGenerator: ViewGeneratorHolder<*, *>) {
        rvListAdapter.remove(viewGenerator)
    }

    override fun removeItemWithPosition(position: Int) {
        rvListAdapter.remove(position)
    }

    override fun removeItemWithData(data: Any) {
        rvListAdapter.remove(data)
    }


    override fun findViewById(id: Int): View? =
        rvListAdapter.findChildById(id)?.view

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


    private val rvListAdapter = RvList()


}

