package com.example.deliverypeople

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

// stop 생명주기에 checkbox 초기화
class HomeFragment : Fragment() {
    var tablayout : TabLayout? = null
    var viewpager2 : ViewPager2? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root : View = inflater.inflate(R.layout.home_fragment,container,false)
        tablayout= root.findViewById(R.id.tablayout)
        viewpager2 = root.findViewById(R.id.viewpager2)
        viewpager2!!.adapter = PagerAdapter(this)
        var tabLayoutTextArray = arrayOf("배달","포장")
        TabLayoutMediator(tablayout!!,viewpager2!!){ tab, position->
            //tablayout.getTabAt(1)
            tab.text = tabLayoutTextArray[position]
        }.attach()

        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main_of_main)
//        var tablayout : TabLayout = findViewById(R.id.tablayout)
//        var viewpager2 : ViewPager2 = findViewById(R.id.viewpager2)
//
//        viewpager2.adapter = PagerAdapter(this)
//        var tabLayoutTextArray = arrayOf("배달","포장")
//        TabLayoutMediator(tablayout,viewpager2){ tab, position->
//            //tablayout.getTabAt(1)
//            tab.text = tabLayoutTextArray[position]
//        }.attach()
//    }