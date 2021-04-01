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
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.deliverypeople.RvAdapte.ViewHolder

class RvAdapte(private val items: ArrayList<recycleList>) :

    RecyclerView.Adapter<RvAdapte.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view : View =v

        var tv_name: TextView
        var layout : ConstraintLayout
        var tv_deliveryTime: TextView
        var tv_deliveryTip: TextView
        var tv_minPrice: TextView

        fun bind(listener:View.OnClickListener,item: recycleList) {

            tv_name.text= item.name
            tv_minPrice.text= item.minPrice.toString()
            tv_deliveryTip.text= item.deliveryTip.toString()
            tv_deliveryTime.text= item.deliverTime.toString()
            view.setOnClickListener(listener)
            //view..setImageDrawable(item.image)
            //tv_name.text = item.name.setOnClickListener(listener)
        }

        init
        {
            layout = view.findViewById(R.id.rc_layout)
            tv_name = view.findViewById(R.id.rc_tv_name)
            tv_deliveryTime = view.findViewById(R.id.rc_tv_deliverytime)
            tv_deliveryTip = view.findViewById(R.id.rc_tv_deliveytip)
            tv_minPrice = view.findViewById(R.id.rc_tv_minprice)
        }

    }

    override fun getItemCount() = items.size

//    interface OnItemClickListener {
//        fun onClick(v: View, position: Int)
//    }
//
//    private lateinit var itemClickListener : OnItemClickListener
//
//    fun setItemClickListener(itemClickListener: OnItemClickListener) {
//        this.itemClickListener = itemClickListener
//    }
//


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):

            RvAdapte.ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)
        return RvAdapte.ViewHolder(inflatedView)
    }


    override fun onBindViewHolder(holder: RvAdapte.ViewHolder, position: Int) {
        val item = items[position]
        // listener -> 클릭시 이벤트를 넣고 싶을 때

        val listener = View.OnClickListener { it->
            StoreMenuListActivity.storeId=item.id
            val intent = Intent(it.context,StoreMenuListActivity::class.java)
            it.context.startActivity(intent)
            StoreMenuListActivity.storeId = item.id
        }

        holder.apply {
            bind(listener,item)
            itemView.tag= item
        }

    }


}