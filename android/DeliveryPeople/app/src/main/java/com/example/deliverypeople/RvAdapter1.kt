package com.example.deliverypeople

//import kotlinx.android.synthetic.main.recycler_item.*
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.system.Os.bind
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deliverypeople.RvAdapte.ViewHolder

class RvAdapte1(private val items: ArrayList<recycleMenuList>) :

    RecyclerView.Adapter<RvAdapte1.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view : View =v

        var tv_name: TextView
//        var layout : ConstraintLayout
        var tv_price: TextView
        var iv_image: ImageView
//        var tv_minPrice: TextView

        fun bind(listener:View.OnClickListener,item: recycleMenuList) {
            tv_name.text = item.name
            tv_price.text = item.price.toString()

            view.setOnClickListener(listener)
            //imageview binding?

//            tv_name.text= item.name
//            tv_minPrice.text= item.minPrice.toString()
//            tv_deliveryTip.text= item.deliveryTip.toString()
//            tv_deliveryTime.text= item.deliverTime.toString()
//            view.setOnClickListener(listener)
            //view..setImageDrawable(item.image)
            //tv_name.text = item.name.setOnClickListener(listener)
        }

        init
        {
//            layout = view.findViewById(R.id.rc_layout)
            tv_name = view.findViewById(R.id.rv_menu_name_tv)
            tv_price = view.findViewById(R.id.rv_menu_price_tv)
//            tv_deliveryTip = view.findViewById(R.id.rc_tv_deliveytip)
            iv_image = view.findViewById(R.id.rv_menu_iv)
        }

    }

    override fun getItemCount() = items.size



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):

            RvAdapte1.ViewHolder {

        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item1, parent, false)

        return RvAdapte1.ViewHolder(inflatedView)
    }


    override fun onBindViewHolder(holder: RvAdapte1.ViewHolder, position: Int) {
        val item = items[position]
        // listener -> 클릭시 이벤트를 넣고 싶을 때

        val listener = View.OnClickListener { it->
            Log.d("mainsss_item_id",items[position].id.toString())
            StoreMenuClick.menuname=item.name
            StoreMenuClick.imageurl=item.imageUrl
            StoreMenuClick.menuprice=item.price
            val intent = Intent(it.context,StoreMenuClick::class.java)
            it.context.startActivity(intent)
        }

        holder.apply {
            bind(listener,item)
            itemView.tag= item

        }
        Glide.with(holder.itemView.context).load(item.imageUrl).into(holder.iv_image)


    }


}