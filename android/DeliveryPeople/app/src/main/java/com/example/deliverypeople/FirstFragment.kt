package com.example.deliverypeople


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment

// stop 생명주기에 checkbox 초기화
class FirstFragment : Fragment(),View.OnClickListener {
    var categoryIntent : Intent? = null
    companion object {
        var k = 0
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        var root : View = inflater.inflate(R.layout.firstfragment,container,false)
        var linear1 : LinearLayout  = root.findViewById(R.id.first_tab1)
        var linear2 : LinearLayout  = root.findViewById(R.id.first_tab2)
        var linear3 : LinearLayout  = root.findViewById(R.id.first_tab3)
        var linear4 : LinearLayout  = root.findViewById(R.id.first_tab4)
        var linear5 : LinearLayout  = root.findViewById(R.id.first_tab5)
        var linear6 : LinearLayout  = root.findViewById(R.id.first_tab6)
        var linear7 : LinearLayout  = root.findViewById(R.id.first_tab7)
        var linear8 : LinearLayout  = root.findViewById(R.id.first_tab8)
        var linear9 : LinearLayout  = root.findViewById(R.id.first_tab9)
        var linear10 : LinearLayout  = root.findViewById(R.id.first_tab10)
        var linear11 : LinearLayout  = root.findViewById(R.id.first_tab11)
        var linear12 : LinearLayout  = root.findViewById(R.id.first_tab12)
        var linear13 : LinearLayout  = root.findViewById(R.id.first_tab13)
        var linear14 : LinearLayout  = root.findViewById(R.id.first_tab14)
        var linear15 : LinearLayout  = root.findViewById(R.id.first_tab15)
        var linear16 : LinearLayout  = root.findViewById(R.id.first_tab16)
        var linear17 : LinearLayout  = root.findViewById(R.id.first_tab17)
        var linear18 : LinearLayout  = root.findViewById(R.id.first_tab18)
        var linear19 : LinearLayout  = root.findViewById(R.id.first_tab19)
        var linear20 : LinearLayout  = root.findViewById(R.id.first_tab20)

        linear1!!.setOnClickListener(this)
        linear2!!.setOnClickListener(this)
        linear3!!.setOnClickListener(this)
        linear4!!.setOnClickListener(this)
        linear5!!.setOnClickListener(this)
        linear6!!.setOnClickListener(this)
        linear7!!.setOnClickListener(this)
        linear8!!.setOnClickListener(this)
        linear9!!.setOnClickListener(this)
        linear10!!.setOnClickListener(this)
        linear11!!.setOnClickListener(this)
        linear12!!.setOnClickListener(this)
        linear13!!.setOnClickListener(this)
        linear14!!.setOnClickListener(this)
        linear15!!.setOnClickListener(this)
        linear16!!.setOnClickListener(this)
        linear17!!.setOnClickListener(this)
        linear18!!.setOnClickListener(this)
        linear19!!.setOnClickListener(this)
        linear20!!.setOnClickListener(this)

        return root
    }

    override fun onClick(p0: View?) {
        Log.d("mainsss","click")
        when(p0!!.id){
            R.id.first_tab1 -> k =1
            R.id.first_tab2 -> k =2
            R.id.first_tab3 -> k =3
            R.id.first_tab4 -> k =4
            R.id.first_tab5 -> k =5
            R.id.first_tab6 -> k =6
            R.id.first_tab7 -> k =7
            R.id.first_tab8 -> k =8
            R.id.first_tab9 -> k =9
            R.id.first_tab10 -> k =10
            R.id.first_tab11 -> k =11
            R.id.first_tab12 -> k =12
            R.id.first_tab13 -> k =13
            R.id.first_tab14 -> k =14
            R.id.first_tab15 -> k =15
            R.id.first_tab16 -> k =16
            R.id.first_tab17 -> k =17
            R.id.first_tab18 -> k =18
            R.id.first_tab19 -> k =19
            R.id.first_tab20 -> k =20
        }
        // intent
        categoryIntent = Intent(activity,StoreListActivity::class.java)
        startActivity(categoryIntent)
    }

}





