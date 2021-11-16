package com.shaparapatah.chuckjoke.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shaparapatah.chuckjoke.ChuckFragment
import com.shaparapatah.chuckjoke.R


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ChuckFragment.newInstance())
                .commitNow()
        }
    }

}
