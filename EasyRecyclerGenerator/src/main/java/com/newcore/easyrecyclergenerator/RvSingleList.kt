package com.newcore.easyrecyclergenerator

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.newcore.easyrecyclergenerator.RvSingleList.RvListViewHolder

class RvSingleList<T : ViewBinding, L : Any>(
    val binding: (LayoutInflater, ViewGroup, Boolean) -> T,
) : RecyclerView.Adapter<RvListViewHolder<T, L>>() {
    class RvListViewHolder<T : ViewBinding, L>(private val binding: T) :
        RecyclerView.ViewHolder(binding.root) {
        operator fun invoke(viewHolder: (T, L) -> Unit, data: L) {
            viewHolder(binding, data)
        }
    }

    var viewHolder: ((T, L) -> Unit)? = null


    val diffUtillCallBack = object : DiffUtil.ItemCallback<L>() {
        override fun areItemsTheSame(oldItem: L, newItem: L): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: L, newItem: L): Boolean {
            return oldItem == newItem
        }
    }

    val diffUtil = AsyncListDiffer(this, diffUtillCallBack)


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
        viewHolder?.let { holder(it, diffUtil.currentList[position]) }
    }

    override fun getItemCount() = diffUtil.currentList.size


}