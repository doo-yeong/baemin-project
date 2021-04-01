package com.example.deliverypeople


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

// stop 생명주기에 checkbox 초기화
class SmsActivity : AppCompatActivity() {
    companion object{
        var phoneNu : String? = null
    }
    var sms_et : EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sms_register)
        var phonNum_et: EditText= findViewById(R.id.phoneNum_et)

        sms_et = findViewById(R.id.smsRegister_et)

        var smsBt :Button = findViewById(R.id.sms_register_bt)
        var smsLayout : LinearLayout = findViewById(R.id.sms_register_layout)
        var smsToNext : TextView = findViewById(R.id.sms_to_next_bt)
        smsBt.setOnClickListener {

            if(phonNum_et.text.length==11){
                smsBt.visibility=Button.GONE
                smsLayout.visibility=Button.VISIBLE
                smsToNext.setTextColor(Color.parseColor("#5EBEBB"))
                smsToNext.isClickable=true
//                startSMSRetriever()
            }

            else{
                Toast.makeText(this,"전화번호를 확인해 주세요",Toast.LENGTH_SHORT).show()
            }

        }

        smsToNext.setOnClickListener {
            val toNext = Intent(this, UserRegisterLastActivity::class.java)
            phoneNu = phonNum_et.toString()
            startActivity(toNext)

        }

    }

}




