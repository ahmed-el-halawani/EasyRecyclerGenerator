package com.newcore.easyrecyclergenerator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.newcore.easy_recycler_generator.RvListFactory
import com.newcore.easyrecyclergenerator.databinding.FragmentFlatListTestBinding
import com.newcore.easyrecyclergenerator.databinding.ItemIntentInfoButtonBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy {
        FragmentFlatListTestBinding.inflate(layoutInflater)
    }

    val vm by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
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
                children = vm.list
            ) { v, data ->
                v.tvDescription.text = data
                v.tvName.text = data
                //                v.button.setOnClickListener { removeItem(data) }
                v.button.setOnClickListener {
                    addNewItem(data)
                }
            }

        }

    }

    private fun RvListFactory.addNewItem(data: String) {
        vm.list.add(data)
        addItem(
            binding = ItemIntentInfoButtonBinding::inflate,
            child = data + "new"
        ) { binding, innerData ->
            binding.tvDescription.text = innerData
            binding.tvName.text = innerData
            binding.button.setOnClickListener {
                addNewItem(innerData)
            }
        }
    }
}