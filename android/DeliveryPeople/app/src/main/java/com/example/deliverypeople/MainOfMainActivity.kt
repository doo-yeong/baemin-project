package com.example.deliverypeople

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView

// stop 생명주기에 checkbox 초기화

class MainOfMainActivity : AppCompatActivity() {
    var k: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_of_main)
        replaceFragment(HomeFragment())
        var bottomNavigationView : BottomNavigationView = findViewById(R.id.bnv_main)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId){
                R.id.first ->{
                    replaceFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.second->{
                    replaceFragment(SearchFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.third->{
                    replaceFragment(JjimFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.fourd ->{
                    replaceFragment(OrderListFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.fifth->{
                    replaceFragment(MybaeminFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else ->{
                    return@setOnNavigationItemSelectedListener false
                }
            }
        }
    }
    private fun replaceFragment(fragment : Fragment){
        val fragmentTransaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fl_container,fragment)
        fragmentTransaction.commit()

    }
}




