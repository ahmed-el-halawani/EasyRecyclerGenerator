package com.newcore.easyrecyclergenerator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.newcore.easy_recycler_generator.RvListFactory
import com.newcore.easyrecyclergenerator.databinding.FragmentBlankBinding
import com.newcore.easyrecyclergenerator.databinding.ItemIntentInfoButtonBinding

class BlankFragment : Fragment() {

    val binding by lazy {
        FragmentBlankBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        rvList(
            binding.rvList,
            GridLayoutManager(requireContext(), 3),
        ) {
            listBuilder(
                binding = ItemIntentInfoButtonBinding::inflate,
                children = List(10) { "ahmed $it" }
            ) { v, data ->
                v.tvDescription.text = data
                v.tvName.text = data
                //                v.button.setOnClickListener { removeItem(data) }
                v.button.setOnClickListener {
                    func(data)
                }
            }

        }

    }


    private fun RvListFactory.func(data: String) {
        addItem(
            binding = ItemIntentInfoButtonBinding::inflate,
            child = data + "new"
        ) { binding, innerData ->
            binding.tvDescription.text = innerData
            binding.tvName.text = innerData
            binding.button.setOnClickListener { func(innerData) }
        }
    }
}