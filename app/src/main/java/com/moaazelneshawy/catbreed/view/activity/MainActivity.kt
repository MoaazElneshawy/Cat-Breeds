package com.moaazelneshawy.catbreed.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.moaazelneshawy.catbreed.R


class MainActivity : AppCompatActivity() {


    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

    }
}
