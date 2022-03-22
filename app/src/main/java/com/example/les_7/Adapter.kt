package com.example.les_7

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val responseItem: Array<ResponseItem>) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item, parent, false)
        return ViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return responseItem.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(responseItem.get(position))
    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val textView: TextView = itemView.findViewById((R.id.Id))
    fun bind(get: ResponseItem) {
        itemView.findViewById<TextView>((R.id.Id)).text = get.id.toString()
        itemView.findViewById<TextView>(R.id.userId).text = get.userId.toString()
        itemView.findViewById<TextView>(R.id.title).text = get.title
        itemView.findViewById<TextView>(R.id.body).text = get.body
    }
}