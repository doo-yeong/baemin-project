package com.example.deliverypeople

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import java.util.concurrent.TimeUnit


// stop 생명주기에 checkbox 초기화

class UserRegisterLastActivity : AppCompatActivity(),TextWatcher {
    var result : register? = null
    var email : EditText? =null
    var nickname : EditText? = null
    var password : EditText? = null
    var birth : EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_info_register)
         email  = findViewById(R.id.email_et)
       // var phoneNum : EditText = findViewById(R.id.phoneNum_et)
         nickname = findViewById(R.id.nickname_et)
        password  = findViewById(R.id.password_et)
        birth  = findViewById(R.id.birth_et)

        var registerText : TextView = findViewById(R.id.register_finish) // 완료버튼

        var toLoginActivity = Intent(this, MainActivity::class.java)

        val httpClient = OkHttpClient.Builder()
            .callTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)


        var builder = Retrofit.Builder()
            .baseUrl(MainActivity.url) // 성공
            .addConverterFactory(GsonConverterFactory.create())

        builder.client(httpClient.build())

        var retrofit : Retrofit = builder.build()

//
        var registerService: signUp = retrofit.create(signUp::class.java)

        registerText.setOnClickListener {
            Log.d("mainsss","")
            var email_text : String = email!!.text.toString()
            var username_text = "kim"
            var phoneNum_text : String = SmsActivity.phoneNu.toString()//phoneNum.text.toString()
            var nickname_text : String = nickname!!.text.toString()
            var password_text : String = password!!.text.toString()
            var birth_text : String = birth!!.text.toString()

            val userInfo = register1(username_text,password_text,email_text,nickname_text,birth_text,phoneNum_text)
            registerService.requestSignUp(userInfo)
                .enqueue(object : Callback<register> {
                    override fun onResponse(call: Call<register>, response: Response<register>) {
                        result = response.body()
                        Log.d("mainsss_register",response.code().toString()+" 다음"+response.errorBody()+"다음"+response.message())
                        Log.d("mainsss",result!!.result.toString())
                        if(result!!.result.equals("true")){
                            Log.d("mainsss","회원가입성공")
                        }
                        else{
                            Log.d("mainsss","중복아이디")
                        }
                        // 루트 액티비티 종료
                        Toast.makeText(this@UserRegisterLastActivity,"회원가입 완료!",Toast.LENGTH_SHORT).show()
                        startActivity(toLoginActivity)
                    }

                    override fun onFailure(call: Call<register>, t: Throwable) {
                        Log.d("mainsss_register", t.message.toString())
                        Toast.makeText(this@UserRegisterLastActivity,"서버에러 다시 시도해 주세요",Toast.LENGTH_SHORT).show()
                    }

                })
        }

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if(nickname!!.length()>0 && password!!.length()>0 && email!!.length()>0 && birth!!.length()>0){
            Log.d("mainsss","글자색깔 변경")
        }
    }

    override fun afterTextChanged(p0: Editable?) {

    }
}

interface signUp {
    @Headers("Content-Type:application/json")
    @POST("/users/signup")
    fun requestSignUp(
        @Body body : register1
    )
            : Call<register>
}


data class register(
    @SerializedName("result") val result : String
)
data class register1(
    @SerializedName("username") val username : String,
    @SerializedName("password") val password : String,
    @SerializedName("email") val email : String,
    @SerializedName("nickname") val nickname : String,
    @SerializedName("birth") val birth : String,
    @SerializedName("tel") val tel : String
)
