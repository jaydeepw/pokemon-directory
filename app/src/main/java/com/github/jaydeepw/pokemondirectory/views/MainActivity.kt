package com.github.jaydeepw.pokemondirectory.views

import android.os.Bundle
import com.github.jaydeepw.pokemondirectory.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        showMainFragment()
    }

    private fun showMainFragment() {
        val ft = supportFragmentManager?.beginTransaction()
        ft?.add(R.id.dynamic_fragment_frame_layout, MainFragment(), "main")
        ft?.commit()
    }
}
