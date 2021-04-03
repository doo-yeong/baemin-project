package com.example.deliverypeople

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*
import kotlin.collections.ArrayList

// stop 생명주기에 checkbox 초기화

class StoreMenuListActivity : AppCompatActivity() {

    companion object{
        var storeId : String? = null
        var list = ArrayList<recycleMenuList>() // 메뉴 list
        var userMenuList = ArrayList<usermenu>()
    }
// userorder 처리
    var tablayout : TabLayout? = null
    var viewpager2 :ViewPager2? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.storemenuofmenu)
        var userordermenu : ImageButton = findViewById(R.id.user_order)
        userordermenu.setOnClickListener {
            val intentUserOrder = Intent(this,CheckOrderMenu::class.java)
            startActivity(intentUserOrder)
        }
        tablayout = findViewById(R.id.tablayout_menulist)
        viewpager2 = findViewById(R.id.viewpager2_menulist)

        viewpager2!!.adapter = PagerAdapter2(this)

        var tabLayoutTextArray = arrayOf("메뉴","정보","리뷰")
        TabLayoutMediator(tablayout!!,viewpager2!!){ tab, position->
            //tablayout.getTabAt(1)
            tab.text = tabLayoutTextArray[position]
        }.attach()
    }

    override fun onStop() {
        super.onStop()

    }

    override fun onDestroy() {
        super.onDestroy()
        StoreMenuToMenuFragment.list.clear()
        list.clear()
        this.finish()
    }

}

class recycleMenuList(var name : String ,
                  var price : Int , var imageUrl : String , var id : String)


//interface listdata {
//    @GET("/stores/1")
//    fun getCurrentWeatherData(
//        @Query("lat") lat: String,
//        @Query("lon") lon: String,
//        @Query("appid") appid: String
//    ):
//            Call<WeatherResponse>
//}

//data class login(
//    @SerializedName("email") val email: String,
//    @SerializedName("password") val password: String,
//)
//
//data class result(
//    @SerializedName("result") val result : String
//)
// tab index를 path에?
// 처음에 k값을 받아오기

data class usermenu(var menuname : String,var base : String, var price : String)

interface MenuListService{

    @GET("/menus/{storeId}")
    fun getMenuList(
        @Path("storeId") storeId : String
    ) : retrofit2.Call<MenuResponse>
}

class MenuResponse{
    @SerializedName("result")
    val result : String? =null
    @SerializedName("content")
    val content : ConTentMenu? = null
}

class ConTentMenu{
    @SerializedName("menus")
    val stores = ArrayList<menus>()
}

class menus{
    @SerializedName("id")
    val id : String? =null
    @SerializedName("name")
    val name : String? = null
    @SerializedName("price")
    val price : Int? = null
    @SerializedName("imageUrl")
    val imageUrl : String? = null
}







