package com.newcore.easyrecyclergenerator

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.newcore.easyrecyclergenerator.databinding.FragmentFlatListTestBinding
import com.newcore.easyrecyclergenerator.databinding.ItemIntentInfoButtonBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        FragmentFlatListTestBinding.inflate(layoutInflater)
    }

    private val vm by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        rvList(
            binding.rvList,
            GridLayoutManager(this, 3),
        ) {
            //with binding
            listBuilder(
                binding = ItemIntentInfoButtonBinding::inflate,
                children = vm.list,
            ) { v, data ->
                v.tvDescription.text = data
                v.tvName.text = data
                //                v.button.setOnClickListener { removeItem(data) }
                v.button.setOnClickListener {
                    addNewItem(data)
                }
            }

            //with view and resources id
            listBuilder<View, String>(
                layoutId = R.layout.item_intent_info_button,
                children = vm.list
            ) { v, data ->
                v.findViewById<TextView>(R.id.tvDescription).text = data
                v.findViewById<TextView>(R.id.tvName).text = data
                v.findViewById<CardView>(R.id.button).setOnClickListener {
                    addNewItemWithView(data)
                }
                //                v.button.setOnClickListener { removeItem(data) }
            }

        }

    }


    private fun RvListFactory.addNewItemWithView(data: String) {
        vm.list.add(data)
        addItem<View, String>(
            layoutId = R.layout.item_intent_info_button,
            child = data + "new"
        ) { v, innerData ->
            v.findViewById<TextView>(R.id.tvDescription).text = innerData
            v.findViewById<TextView>(R.id.tvName).text = innerData
            v.findViewById<CardView>(R.id.button).setOnClickListener {
                addNewItemWithView(innerData)
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