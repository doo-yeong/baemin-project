package com.example.deliverypeople

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

import java.util.*

class MainActivity : AppCompatActivity() {
    var result : login? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val userId : EditText = findViewById(R.id.userid_et)
        var userPassword : EditText = findViewById(R.id.userpassword_et)
        val registerBt : LinearLayout = findViewById(R.id.register_user)
        val loginText : TextView = findViewById(R.id.login_bt)
        registerBt.setOnClickListener {
            val registerIntent = Intent(this,UserRegisterActivity::class.java)
            startActivity(registerIntent)
        }

        loginText.setOnClickListener {
            var retrofit = Retrofit.Builder()
                .baseUrl("http://172.30.1.43:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            var loginService: signIn = retrofit.create(signIn::class.java)
            loginService.requestSignIn(userId.toString(),userPassword.toString()).enqueue(object : Callback<login>{
                override fun onResponse(call: Call<login>, response: Response<login>) {
                    TODO("Not yet implemented")
                    //if 달아서 false true인지 판별
                    Log.d("mainsss_login",result!!.result.toString())
                }
                //git

                override fun onFailure(call: Call<login>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }

    }
}

interface signIn {
    @FormUrlEncoded
    @POST("/users/signin")
    fun requestSignIn(
        @Field("username") username: String,
        @Field("password") password: String,
    )
            : Call<login>
}

data class login(val result : String)