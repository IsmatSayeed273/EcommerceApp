package com.example.apis

import android.app.Activity
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class MyAdapter(
    private val context: Activity,
    private val productArrayList: List<Product>
) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.eachitem, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int = productArrayList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = productArrayList[position]

        // Title & Brand
        holder.title.text = item.title
        holder.brand.text = item.brand

        // Rating (Double -> Float), clamp 0..5 just in case
        val rating = item.rating.toFloat().coerceIn(0f, 5f)
        holder.ratingBar.rating = rating
        holder.ratingText.text = String.format("(%.1f)", rating)

        // Pricing
        val discount = item.discountPercentage
        val originalPrice = item.price
        val finalPrice = (originalPrice * (1 - (discount / 100.0))).coerceAtLeast(0.0)

        holder.price.text = "₹ %.2f".format(finalPrice)

        // Original price + strike-through if discount available
        if (discount > 0) {
            holder.originalPrice.visibility = View.VISIBLE
            holder.originalPrice.text = "₹ %.2f".format(originalPrice)
            holder.originalPrice.paintFlags = holder.originalPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

            holder.discountBadge.visibility = View.VISIBLE
            holder.discountBadge.text = "%.0f%% OFF".format(discount)
        } else {
            holder.originalPrice.visibility = View.GONE
            holder.discountBadge.visibility = View.GONE
        }

        // Stock
        holder.stock.text = "In stock: ${item.stock}"

        // Image (Picasso)
        Picasso.get()
            .load(item.thumbnail)
            .fit()
            .centerCrop()
            .into(holder.image)

        // (Optional) Click action – you can navigate or toast
        // holder.itemView.setOnClickListener { ... }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ShapeableImageView = itemView.findViewById(R.id.productImage)
        val title: TextView = itemView.findViewById(R.id.productTitle)
        val brand: TextView = itemView.findViewById(R.id.productBrand)
        val ratingBar: RatingBar = itemView.findViewById(R.id.productRating)
        val ratingText: TextView = itemView.findViewById(R.id.ratingText)
        val price: TextView = itemView.findViewById(R.id.productPrice)
        val originalPrice: TextView = itemView.findViewById(R.id.productOriginalPrice)
        val discountBadge: TextView = itemView.findViewById(R.id.discountBadge)
        val stock: TextView = itemView.findViewById(R.id.productStock)
    }
}

