package com.example.apis

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso


class MyAdapter(val context : Activity, val productArrayList: List<Product>) :
RecyclerView.Adapter<MyAdapter.MyViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.eachitem, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
     return  productArrayList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    val curentItem = productArrayList[position]
        holder.title.text = curentItem.title
        // image viwe, how to show in imageview if the image is in form of url,3rd party library
        //picasso
        Picasso.get().load(curentItem.thumbnail).into(holder.image);

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var title : TextView
        var image : ShapeableImageView

        init{
            title = itemView.findViewById(R.id.productTitle)
            image = itemView.findViewById(R.id.productImage)
        }
    }

}
