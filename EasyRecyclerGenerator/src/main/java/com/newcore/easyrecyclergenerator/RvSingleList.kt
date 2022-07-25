package com.newcore.easyrecyclergenerator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.newcore.easyrecyclergenerator.RvSingleList.RvListViewHolder

class RvSingleList<T : ViewBinding, L>(val binding: (LayoutInflater, ViewGroup, Boolean) -> T) :
    RecyclerView.Adapter<RvListViewHolder<T, L>>() {
    class RvListViewHolder<T : ViewBinding, L>(private val binding: T) :
        RecyclerView.ViewHolder(binding.root) {
        operator fun invoke(viewHolder: (T, L) -> Unit, data: L) {
            viewHolder(binding, data)
        }
    }

    var viewHolder: ((T, L) -> Unit)? = null

    var children: List<L> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvListViewHolder<T, L> {
        return RvListViewHolder<T, L>(
            binding(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: RvListViewHolder<T, L>, position: Int) {
        viewHolder?.let { holder(it, children[position]) }
    }

    override fun getItemCount() = children.size


}