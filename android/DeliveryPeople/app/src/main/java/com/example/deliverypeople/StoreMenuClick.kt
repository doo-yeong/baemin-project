package com.example.deliverypeople

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class StoreMenuClick :AppCompatActivity() {

    companion object{
        var menuname : String? = null
        var imageurl : String? = null
        var menuprice : Int? = null
        var minprice : Int? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menuclick_activity)
        var radio1 : RadioButton = findViewById(R.id.radio1_menuclick)
        var radio2 : RadioButton = findViewById(R.id.radio2_menuclick)
        var radio3 : RadioButton = findViewById(R.id.radio3_menuclick)
        var image : ImageView = findViewById(R.id.iv_menuclick)
        var menunameText : TextView = findViewById(R.id.tv_storename_menuclick)
        var menuprice1Text : TextView = findViewById(R.id.tv_price1_menuclick)
        var menuprice2Text : TextView = findViewById(R.id.tv_price2_menuclick)
        var menuprice3Text : TextView = findViewById(R.id.tv_price3_menuclick)
        var getmenuBt : Button = findViewById(R.id.bt_getmenu_menuclick)

        menunameText.text= menuname
        menuprice1Text.text = menuprice.toString()
        menuprice2Text.text = (menuprice!!*2).toInt().toString()
        menuprice3Text.text = (menuprice!!*3).toString()
        Glide.with(this).load(imageurl).into(image)
        radio1.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked==true){
                radio2.isChecked=false
                radio3.isChecked=false
            }
        }
        radio2.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked==true){
                radio1.isChecked=false
                radio3.isChecked=false
            }
        }
        radio3.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked==true){
                radio1.isChecked=false
                radio2.isChecked=false
            }
        }
        getmenuBt.setOnClickListener {
            var intent = Intent(this,StoreMenuListActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.finish()
    }
}