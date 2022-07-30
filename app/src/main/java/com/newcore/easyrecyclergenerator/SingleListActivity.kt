package com.newcore.easyrecyclergenerator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.newcore.easyrecyclergenerator.databinding.FragmentFlatListTestBinding
import com.newcore.easyrecyclergenerator.databinding.ItemIntentInfoButtonBinding

class SingleListActivity : AppCompatActivity() {
    private val binding by lazy {
        FragmentFlatListTestBinding.inflate(layoutInflater)
    }

    private val vm by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private val singleList by lazy {
        rvSingleList(
            binding.rvList,
            ItemIntentInfoButtonBinding::inflate,
            emptyList<Int>()
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var start = 0

        singleList.listBuilder { v, i ->
            v.tvName.text = "tvName $i"
            v.tvDescription.text = "tvDescription $i"
        }

        binding.btnAddMore.setOnClickListener {
            start++
            singleList.addList(List(10) { it + start })
        }

        binding.btnNewList.setOnClickListener {
            singleList.setList(List(10) { it + start })
            start++
        }
    }


    override fun onStart() {
        super.onStart()
    }
}