package com.newcore.easyrecyclergenerator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class FirstScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_screen)

        findViewById<Button>(R.id.btnGoToRecycler).setOnClickListener {
            val intent = Intent(this, SingleListActivity::class.java)
            startActivity(intent)
        }

    }
}