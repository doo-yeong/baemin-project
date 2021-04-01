package com.example.deliverypeople


import android.app.Activity
import android.app.ActivityManager
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.annotations.SerializedName
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

// stop 생명주기에 checkbox 초기화
@Suppress("DEPRECATION")
class StoreListFragment : Fragment() {


    var list = ArrayList<recycleList>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        var root : View = inflater.inflate(R.layout.storelistfragment0,container,false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onDestroyView() {
        super.onDestroyView()

        Log.d("mainsssragment","destroyview")
    }
}

class recycleList(var name : String ,
                       var deliverTime : Int , var deliveryTip : Int , var minPrice : Int, var id : String)


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

interface StoreListService{

    @GET("/stores/{storeNum}")
    fun getStoreList(
        @Path("storeNum") storeNum : String
    ) : retrofit2.Call<StoreResponse>
}

class StoreResponse{
    @SerializedName("result")
    val result : String? =null
    @SerializedName("content")
    val content : ConTent? = null
}

class ConTent{
    @SerializedName("stores")
    val stores = ArrayList<store>()
}

class store{
    @SerializedName("id")
    val id : String? =null
    @SerializedName("name")
    val name : String? = null
    @SerializedName("category")
    val category : String? = null
    @SerializedName("regdate")
    val regdate : String? = null
    @SerializedName("minPrice")
    val minPrice : Int? = 0
    @SerializedName("deliveryTip")
    val deliveryTip : Int? = 0
    @SerializedName("deliveryTime")
    val deliverTime : Int? = 0
    @SerializedName("location")
    val location : String? = null
    @SerializedName("menuList")
    val menuList = ArrayList<menuList>()
    //    @SerializedName("menuList")
//    val menuList :
//    @SerializedName("reviewList")
    @SerializedName("storeOwner")
    val storeOwner : String? = null
    @SerializedName("storeDetail")
    val storeDetail : String? = null
}

class menuList{
    @SerializedName("id")
    val id : String? =null
    @SerializedName("name")
    val name : String? = null
    @SerializedName("price")
    val price : Int? = null
    @SerializedName("imageUrl")
    val imageUrl : String? = null
}






