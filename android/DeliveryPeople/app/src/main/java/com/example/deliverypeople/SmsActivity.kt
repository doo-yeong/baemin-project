package com.example.deliverypeople

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*


import androidx.core.content.ContextCompat
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import kotlin.collections.ArrayList

// stop 생명주기에 checkbox 초기화
class SmsActivity : AppCompatActivity() {
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
            startActivity(toNext)
        }

    }

}




