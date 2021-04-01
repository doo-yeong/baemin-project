package com.example.deliverypeople

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// stop 생명주기에 checkbox 초기화
class StoreMenuToMenuFragment : Fragment() {
    var recyclerView: RecyclerView? = null

    companion object{
        var list = ArrayList<recycleMenuList>()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        list.clear()
        val root = inflater.inflate(R.layout.storememulist_frag, container, false)

        var retrofit1 = Retrofit.Builder()
            .baseUrl(MainActivity.url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var adapter :RvAdapte1
        adapter = RvAdapte1(list)

        // val login = login(userId.toString(), userPassword.toString())
        recyclerView = root.findViewById(R.id.recycelerview1)


        var MenuListService = retrofit1.create(MenuListService::class.java)
        val call = MenuListService.getMenuList(StoreMenuListActivity.storeId!!)

        call.enqueue(object : Callback<MenuResponse> {

            override fun onFailure(call: retrofit2.Call<MenuResponse>, t: Throwable) {

            }

            override fun onResponse(
                call: retrofit2.Call<MenuResponse>,
                response: Response<MenuResponse>
            ) {

                val res = response.body()
                var size = res!!.content!!.stores.size
                Log.d("recycler",res!!.content!!.stores.size.toString())

                for (i in 0..size - 1) {
                    list.add(
                        recycleMenuList(res!!.content!!.stores[i]!!.name!!,
                            res!!.content!!.stores[i]!!.price!!,
                            res!!.content!!.stores[i]!!.imageUrl!!,
                            res!!.content!!.stores[i]!!.id!!)
                    )

                }
                recyclerView!!.adapter = adapter
                recyclerView!!.adapter!!.notifyDataSetChanged()

//                adapter = RvAdapte(list)
//                StoreListFragment1.recyclerView!!.adapter = adapter
//                StoreListFragment1.recyclerView!!.adapter!!.notifyDataSetChanged()
            }

        })

        recyclerView!!.layoutManager = LinearLayoutManager(context)

        Log.d("recyclerSize",list.size.toString())


        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}






