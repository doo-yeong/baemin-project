package com.example.deliverypeople

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

class CheckOrderMenu :AppCompatActivity() {
    var result : paymentResult? = null
    companion object{
        var userId : String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.check_orderlist_activity)

        var payemntBt : Button = findViewById(R.id.payment_bt)
        var adapter : RvAdapte2
        var recyclerview : RecyclerView? = null
        var totalprice = 0

        recyclerview = findViewById(R.id.recycelerview_checkorder)
        val totalprice_tv : TextView = findViewById(R.id.total_bill)
        adapter = RvAdapte2(StoreMenuListActivity.userMenuList)
        recyclerview!!.adapter= adapter
        recyclerview!!.adapter!!.notifyDataSetChanged()
        Log.d("mainsss",MainActivity.userId1.toString())
        recyclerview!!.layoutManager = LinearLayoutManager(this)

        for(i in 0..StoreMenuListActivity.userMenuList.size-1)
        {
            totalprice=totalprice.toInt()+StoreMenuListActivity.userMenuList[i].price.toInt()
        }
        totalprice_tv.text= totalprice.toString()

        var retrofit = Retrofit.Builder()
            .baseUrl(MainActivity.url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val payment1 = paymentRequest(userId.toString(),3000) // 메뉴까지

        var paymentService: payment = retrofit.create(payment::class.java)

        payemntBt.setOnClickListener {
            paymentService.requestPayment(payment1).enqueue(object : Callback<paymentResult> {
                override fun onResponse(call: Call<paymentResult>, response: Response<paymentResult>) {
                   result = response.body()
                    Log.d("mainsss_payment",result!!.result.toString())
                    var intentMain = Intent(this@CheckOrderMenu,MainOfMainActivity::class.java)
                    startActivity(intentMain)
                    Toast.makeText(this@CheckOrderMenu,totalprice.toString()+" 결제완료!\n현재잔액 :"+(result!!.content!!.account!!-totalprice).toString(),Toast.LENGTH_LONG).show()


//                    //if 달아서 false true인지 판별
                }

                override fun onFailure(call: Call<paymentResult>, t: Throwable) {
                    Log.d("mainsss",t.message.toString())
                }
            })
        }
    }
}
interface payment{
    @POST("/payment")
    fun requestPayment(
        @Body body: paymentRequest,
    )
            : Call<paymentResult>
}

data class paymentRequest(
    @SerializedName("email") val email: String,
    @SerializedName("totalPrice") val totalPrice: Int,

)

data class paymentResult(
    @SerializedName("result") val result : Boolean,
    @SerializedName("content") val content : conTent1
)
 class conTent1{
    @SerializedName("account") val account : Int? = 0
 }


