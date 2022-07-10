package com.newcore.easyrecyclergenerator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.newcore.easyrecyclergenerator.databinding.FragmentFlatListTestBinding
import com.newcore.easyrecyclergenerator.databinding.ItemIntentInfoButtonBinding
import com.newcore.easyrecyclergenerator.databinding.ItemTableRowBinding

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
                children = List(1000) { "ahmed $it" }
            ) { v, i ->
                v.tvDescription.text = i
                v.tvName.text = i
            }

            listBuilder(
                binding = ItemTableRowBinding::inflate,
                children = List(1000) { "gomaa $it" }
            ) { v, i ->
                v.tvColumn1.text = i
                v.tvColumn2.text = i
                v.tvColumn3.text = i
                v.tvColumn4.text = i
            }
        }

    }
}