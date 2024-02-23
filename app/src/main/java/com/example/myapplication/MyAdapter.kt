package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class MyAdapter(private var originalData: List<Item>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private var filteredData: List<Item> = originalData
    private var currentQuery: String? = null

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textName: TextView = itemView.findViewById(R.id.textName)
        val textDesc: TextView = itemView.findViewById(R.id.textDesc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(itemView)
    }


override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    if (position < filteredData.size) {
        val currentItem = filteredData[position]
        holder.imageView.setImageResource(currentItem.img)
        holder.textName.text = currentItem.name
        holder.textDesc.text = currentItem.desc
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(currentItem)
        }
    }
}

    override fun getItemCount(): Int {
        return filteredData.size
    }

    fun filter(query: String?) {
        currentQuery = query

        if (query.isNullOrBlank()) {
            filteredData = originalData
            return
        }

        filteredData = originalData.filter {
            it?.name?.lowercase()?.contains(query.lowercase()) == true
        }
        notifyDataSetChanged()
    }



    interface OnItemClickListener {
        fun onItemClick(item: Item)
    }

    private var onItemClickListener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

}

