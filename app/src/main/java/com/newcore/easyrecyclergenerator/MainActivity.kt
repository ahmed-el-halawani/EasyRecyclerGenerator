package com.newcore.easyrecyclergenerator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.newcore.easy_recycler_generator.RvListFactory
import com.newcore.easyrecyclergenerator.databinding.FragmentFlatListTestBinding
import com.newcore.easyrecyclergenerator.databinding.ItemIntentInfoButtonBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy {
        FragmentFlatListTestBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        rvList(
            binding.rvList,
            GridLayoutManager(this, 3),
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