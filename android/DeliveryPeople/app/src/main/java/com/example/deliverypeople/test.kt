package com.example.deliverypeople

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

// stop 생명주기에 checkbox 초기화
class test : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {
    var checkBoxAll: CheckBox? = null
    var checkBox1: CheckBox? = null
    var checkBox2: CheckBox? = null
    var checkBox3: CheckBox? = null
    var checkBox4: CheckBox? = null
    var checkBox5: CheckBox? = null
    var shape: GradientDrawable? = null
    var registerBt: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.user_register)

        shape = ContextCompat.getDrawable(this, R.drawable.register_btn) as GradientDrawable

        registerBt = findViewById(R.id.register_bt)
        checkBoxAll = findViewById(R.id.register_check_all)
        checkBox1 = findViewById(R.id.register_check_1)
        checkBox2 = findViewById(R.id.register_check_2)
        checkBox3 = findViewById(R.id.register_check_3)
        checkBox4 = findViewById(R.id.register_check_4)
        checkBox5 = findViewById(R.id.register_check_5)
        checkBoxAll!!.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                checkBox1!!.isChecked = true
                checkBox2!!.isChecked = true
                checkBox3!!.isChecked = true
                checkBox4!!.isChecked = true
                checkBox5!!.isChecked = true
            } else {
                checkBox1!!.isChecked = false
                checkBox2!!.isChecked = false
                checkBox3!!.isChecked = false
                checkBox4!!.isChecked = false
                checkBox5!!.isChecked = false
            }
        }
//        checkBox1!!.setOnCheckedChangeListener { compoundButton, b ->
//            if(checkBox1!!.isChecked && b && checkBoxAll!!.isChecked){
//                checkBoxAll!!.isChecked = false
//            }
//        }
        checkBox1!!.setOnCheckedChangeListener(this)
        checkBox2!!.setOnCheckedChangeListener(this)
        checkBox3!!.setOnCheckedChangeListener(this)
        checkBox4!!.setOnCheckedChangeListener(this)
        checkBox5!!.setOnCheckedChangeListener(this)

        registerBt!!.setOnClickListener {
            val toSmsIntent = Intent(this,SmsActivity::class.java)
            startActivity(toSmsIntent)
        }
    }

    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {

        if (checkBox1!!.isChecked && checkBox2!!.isChecked && checkBox3!!.isChecked) {
            Log.d("mainsss", "123")
            shape!!.setColor(Color.parseColor("#5EBEBB"))
            registerBt!!.isClickable = true

        } else {
            Log.d("mainsss", "11")
            shape!!.setColor(Color.parseColor("#BEBEBE"))
            registerBt!!.isClickable = false
        }
        registerBt!!.background = shape
    }
}