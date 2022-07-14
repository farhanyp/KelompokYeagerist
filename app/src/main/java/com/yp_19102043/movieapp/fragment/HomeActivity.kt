package com.yp_19102043.movieapp.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.yp_19102043.movieapp.R
import com.yp_19102043.movieapp.adapter.TabAdapter
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val tabAdapter = TabAdapter(supportFragmentManager, lifecycle)
        view_pager.adapter = tabAdapter

        val tabTitles = arrayListOf("Popular", "Now Playing")
        TabLayoutMediator(tab_layout, view_pager){ tab, position ->
            tab.text = tabTitles[position]
        }.attach()

    }
}