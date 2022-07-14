package com.yp_19102043.movieapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yp_19102043.movieapp.fragment.NowPlayingFragment
import com.yp_19102043.movieapp.fragment.PopularFragment

class TabAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle)
    : FragmentStateAdapter(fragmentManager, lifecycle) {

        val fragment: ArrayList<Fragment> = arrayListOf(
            PopularFragment(),
            NowPlayingFragment()
        )

    override fun getItemCount(): Int {
        return fragment.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragment[position]
    }


}