package com.newcore.easyrecyclergenerator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.newcore.easyrecyclergenerator.databinding.ItemContainerBinding

class RvList : RecyclerView.Adapter<RvList.RvListViewHolder>() {
    class RvListViewHolder(private val viewHolder: ItemContainerBinding) :
        RecyclerView.ViewHolder(viewHolder.root) {
        operator fun invoke(view: View) {
            if (view.parent != null) {
                (view.parent as ViewGroup).removeView(view)
            }
            viewHolder.frameLayout.removeAllViews()
            viewHolder.frameLayout.addView(view)
        }
    }

    val children: MutableList<ViewGeneratorHolder<*, *>> = mutableListOf()


    fun findChildById(id: Int): ViewGeneratorHolder<*, *>? {
        return children.firstOrNull { it.view?.id == id }
    }

    fun addAll(listOfAddedViews: List<ViewGeneratorHolder<*, *>>) {
        val positionStart = children.size
        children.addAll(listOfAddedViews)
        notifyItemRangeChanged(positionStart, listOfAddedViews.size)
    }

    fun add(view: ViewGeneratorHolder<*, *>) {
        children.add(view)
        notifyItemInserted(children.size)
    }

    fun remove(viewGeneratorHolder: ViewGeneratorHolder<*, *>) {
        val itemPosition = children.indexOf(viewGeneratorHolder)
        remove(itemPosition)
    }

    fun remove(data: Any) {
        children.firstOrNull { it.data == data }?.also {
            remove(children.indexOf(it))
        }
    }

    fun remove(position: Int) {
        children.removeAt(position)
        notifyItemRemoved(position)
    }

    fun removeAll() {
        val size = children.size
        children.clear()
        notifyItemRangeChanged(0, size)
    }

    fun replace(list: List<ViewGeneratorHolder<*, *>>) {
        val size = children.size
        children.clear()
        children.addAll(list)
        notifyItemRangeChanged(0, size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvListViewHolder {
        return RvListViewHolder(
            ItemContainerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: RvListViewHolder, position: Int) {
        holder(ViewGeneratorHolder.generateView(children[position]))
    }

    override fun getItemCount() = children.size


}