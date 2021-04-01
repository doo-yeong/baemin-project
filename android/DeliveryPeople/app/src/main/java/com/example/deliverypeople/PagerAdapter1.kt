package com.example.deliverypeople

import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter1(fa : FragmentActivity) : FragmentStateAdapter(fa){
    override fun getItemCount(): Int = 20

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }



    override fun createFragment(position: Int): Fragment {

         return when(position){

            0 -> StoreListFragment()
            1 -> StoreListFragment()
            2 -> StoreListFragment()
            3 -> StoreListFragment()
            4 -> StoreListFragment1()
            5 -> StoreListFragment2()
            6 -> StoreListFragment3()
            7 -> StoreListFragment()
            8 -> StoreListFragment()
            9 -> StoreListFragment()
            10 -> StoreListFragment()
            11 -> StoreListFragment()
            12 -> StoreListFragment()
            13 -> StoreListFragment()
            14 -> StoreListFragment()
            15 -> StoreListFragment()
            16 -> StoreListFragment()
            17 -> StoreListFragment()
            18 -> StoreListFragment()
            19 -> StoreListFragment()
            else -> throw IndexOutOfBoundsException()

        }
    }
}




