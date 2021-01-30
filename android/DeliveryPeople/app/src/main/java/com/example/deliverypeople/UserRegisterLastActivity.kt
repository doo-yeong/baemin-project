package com.example.deliverypeople

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.concurrent.TimeUnit


// stop 생명주기에 checkbox 초기화

class UserRegisterLastActivity : AppCompatActivity() {
    var result : register? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_info_register)
        var email : EditText = findViewById(R.id.email_et)
       // var phoneNum : EditText = findViewById(R.id.phoneNum_et)
        var nickname : EditText = findViewById(R.id.nickname_et)
        var password : EditText = findViewById(R.id.password_et)

        var birth : EditText = findViewById(R.id.birth_et)

        var registerText : TextView = findViewById(R.id.register_finish)

        var toLoginActivity = Intent(this, MainActivity::class.java)

        val httpClient = OkHttpClient.Builder()
            .callTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)


        var builder = Retrofit.Builder()
            .baseUrl("http://172.30.1.43:8080")
            .addConverterFactory(GsonConverterFactory.create())

        builder.client(httpClient.build())

        var retrofit : Retrofit = builder.build()

//
        var registerService: signUp = retrofit.create(signUp::class.java)

        registerText.setOnClickListener {
            Log.d("mainsss","")
            var email_text : String = email.text.toString()
            var username_text = "kim"
            var phoneNum_text : String = "01044051733"//phoneNum.text.toString()
            var nickname_text : String = nickname.text.toString()
            var password_text : String = password.text.toString()
            var birth_text : String = birth.text.toString()

            registerService.requestSignUp(username_text,
                email_text,
                nickname_text,
                password_text,
                birth_text,
                phoneNum_text)
                .enqueue(object : Callback<register> {
                    override fun onResponse(call: Call<register>, response: Response<register>) {
                        TODO("Not yet implemented")
                        Log.d("mainsss_register", result!!.result.toString())
                        // 루트 액티비티 종료
                        startActivity(toLoginActivity)
                    }

                    override fun onFailure(call: Call<register>, t: Throwable) {
                        Log.d("mainsss_register", t.message.toString())
                        TODO("Not yet implemented")
                    }

                })
        }

    }
}


//interface signIn {
//    @FormUrlEncoded
//    @POST("/users/signin")
//    fun requestSignIn(
//        @Field("userid") userid: String,
//        @Field("userpw") userpw: String)
//    : Call<Login>
//
//}

interface signUp {
    @FormUrlEncoded
    @POST("/users/signup")
    fun requestSignUp(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("email") email: String,
        @Field("nickname") nickname: String,
        @Field("birth") birth: String,
        @Field("tel") tel: String,
    )
            : Call<register>
}

data class register(val result: String)