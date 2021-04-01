package com.example.deliverypeople

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

class MainActivity : AppCompatActivity() {
    var result: result? = null

    companion object{
        val url = "http://10.0.2.2:8080"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val userId: EditText = findViewById(R.id.userid_et)
        var userPassword: EditText = findViewById(R.id.userpassword_et)
        val registerBt: LinearLayout = findViewById(R.id.register_user)
        val loginText: TextView = findViewById(R.id.login_bt)
        val mainOfMainIntent  = Intent(this,MainOfMainActivity::class.java)
        registerBt.setOnClickListener {
            val registerIntent = Intent(this, UserRegisterActivity::class.java)
            startActivity(registerIntent)
        }

        loginText.setOnClickListener {
            var retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val login = login(userId.toString(), userPassword.toString())
            var loginService: signIn = retrofit.create(signIn::class.java)
            loginService.requestSignIn(login).enqueue(object : Callback<result> {
                override fun onResponse(call: Call<result>, response: Response<result>) {
                    result = response.body()
                    Log.d("mainsss_login","성공 ")
                    Log.d("mainsss_login",response.code().toString())
                    Log.d("mainsss",result!!.result!!.toString())
                    startActivity(mainOfMainIntent)
                    //if 달아서 false true인지 판별
                }

                override fun onFailure(call: Call<result>, t: Throwable) {
                    Log.d("mainsss",t.message.toString())
                }
            })
        }

    }
}

interface signIn {
    @POST("/users/signin")
    fun requestSignIn(
        @Body body: login,
    )
            : Call<result>
}

data class login(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
)

data class result(
    @SerializedName("result") val result : String
)

// http://10.0.2.2:8080 에뮬레이터 주소
//172.30.1.58:8080 개인 폰으로 할 때