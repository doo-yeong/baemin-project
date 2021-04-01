package com.example.deliverypeople


import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(fa : Fragment) : FragmentStateAdapter(fa){
    override fun getItemCount(): Int = 2


    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> FirstFragment()
            else -> SecondFragment()
        }
    }
}




