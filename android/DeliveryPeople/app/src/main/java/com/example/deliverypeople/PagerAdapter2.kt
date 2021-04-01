package com.example.deliverypeople

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter2(fa : AppCompatActivity) : FragmentStateAdapter(fa){
    override fun getItemCount(): Int = 3

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun createFragment(position: Int): Fragment {

         return when(position){

            0 -> StoreMenuToMenuFragment()
            1 -> StoreMenuToInfoFragment()
            2 -> StoreMenuToReviewFragment()
            else -> throw IndexOutOfBoundsException()

        }
    }
}




