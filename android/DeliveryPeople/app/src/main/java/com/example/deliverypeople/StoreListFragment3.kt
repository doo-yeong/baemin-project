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
class StoreListFragment3 : Fragment() {

    companion object{
        var position = 0
        var recyclerView : RecyclerView? = null
    }

    var list = ArrayList<recycleList>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        Log.d("mainsss_position_Creat",position.toString())
        Log.d("mainsss","Fragment_ViewCreated")
        var root : View = inflater.inflate(R.layout.storelistfragment0,container,false)
        list.clear()
        var retrofit = Retrofit.Builder()
            .baseUrl(MainActivity.url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // val login = login(userId.toString(), userPassword.toString())
        recyclerView= root.findViewById(R.id.recycelerview)
        var adapter : RvAdapte

            var StoreListService = retrofit.create(StoreListService::class.java)
            val call = StoreListService.getStoreList((3).toString())

            call.enqueue(object : Callback<StoreResponse>{

                override fun onFailure(call: retrofit2.Call<StoreResponse>, t: Throwable) {

                }

                override fun onResponse(
                    call: retrofit2.Call<StoreResponse>,
                    response: Response<StoreResponse>
                )

                {

                    val res =response.body()
                    var size = res!!.content!!.stores.size

                    for(i in 0..size-1){
                        list.add(recycleList(res!!.content!!.stores[i]!!.name!!,res!!.content!!.stores[i]!!.deliverTime!!,
                            res!!.content!!.stores[i]!!.deliveryTip!!,res!!.content!!.stores[i]!!.minPrice!!,res!!.content!!.stores[i]!!.id!!))
                    }

                    adapter =RvAdapte(list)
                    Log.d("mainsss_adapter",adapter.itemCount.toString())
                    recyclerView!!.adapter = adapter
                    Log.d("mainsss_recycleradapter",recyclerView!!.adapter!!.itemCount.toString())
                    recyclerView!!.adapter!!.notifyDataSetChanged()
                }
            })

        recyclerView!!.layoutManager = LinearLayoutManager(context)
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






