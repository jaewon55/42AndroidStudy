package com.example.week7_2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val items: MutableList<Product>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    lateinit var listener: OnProductItemClickListener

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.imageView)
        private val name: TextView = itemView.findViewById(R.id.name)
        private val price: TextView = itemView.findViewById(R.id.price)
        private val explain: TextView = itemView.findViewById(R.id.explain)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(this, itemView, adapterPosition)
            }
        }

        fun setItem(item: Product) {
            image.setImageResource(item.img)
            name.text = item.name
            price.text = item.price
            explain.text = item.explain
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.itemlayout, parent, false)
        return ViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.setItem(item)
    }

    override fun getItemCount(): Int = items.size
}