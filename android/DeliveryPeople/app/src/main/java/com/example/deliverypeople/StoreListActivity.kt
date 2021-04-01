package com.example.deliverypeople

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.util.*

// stop 생명주기에 checkbox 초기화


class StoreListActivity : AppCompatActivity() {

    companion object{
        open var tabIndex : Int? = 0
        open var tablayout : TabLayout? = null
        open var viewpager2 : ViewPager2? = null

    }

    var pagerAdapter1 : PagerAdapter1? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_select)

        tablayout = findViewById(R.id.tablayout_category)
        viewpager2 = findViewById(R.id.viewpager2_category)

        viewpager2!!.adapter = PagerAdapter1(this)

        viewpager2!!.setCurrentItem(FirstFragment.k-1,true) // 포지션 설정
        tabIndex= FirstFragment.k-1
        var tabLayoutTextArray = arrayOf("선물하기","배민라이더스","포장/방문","1인분","한식",
            "분식","카페·디저트","일식","피자","아시안·양식","중국집",
            "중국집","족발·보쌈","야식","찜·탕","도시락","패스트푸드","프렌차이즈","맛집랭킹","전국별미")

        TabLayoutMediator(tablayout!!,viewpager2!!){ tab, position->
            tab.text = tabLayoutTextArray[position]
        }.attach()
    }

}




