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
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deliverypeople.RvAdapte.ViewHolder

class RvAdapte2(private val items: ArrayList<usermenu>) :

    RecyclerView.Adapter<RvAdapte2.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View = v
        var iv_cancle : ImageButton
        var tv_name: TextView
        var tv_price: TextView
        var tv_mount : TextView

        fun bind(listener: View.OnClickListener, item: usermenu) {
            tv_price.text = item.price.toString()
            tv_name.text = item.menuname
            tv_mount.text = item.base+"인분"
            iv_cancle.setOnClickListener(listener)
        }

        init {
            tv_mount = view.findViewById(R.id.rc2_mount_tv)
            iv_cancle = view.findViewById(R.id.rc2_cancle_ib)
            tv_name = view.findViewById(R.id.rc2_name_tv)
            tv_price = view.findViewById(R.id.rc2_price_tv)
        }

    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):

            RvAdapte2.ViewHolder {

        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item2, parent, false)

        return RvAdapte2.ViewHolder(inflatedView)
    }


    override fun onBindViewHolder(holder: RvAdapte2.ViewHolder, position: Int) {
        val item = items[position]
        // listener -> 클릭시 이벤트를 넣고 싶을 때

        val listener = View.OnClickListener { it ->

        }

        holder.apply {
            bind(listener, item)
            itemView.tag = item

        }


    }


}