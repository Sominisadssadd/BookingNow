package com.example.bookingnow.view.fragments.adapters.listfragment

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingnow.R

class TopRecyclerViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val image: ImageView = view.findViewById(R.id.ImageViewOfTopHotelItem)
    val title: TextView = view.findViewById(R.id.TextViewTitleOfTopHotelItem)
    val type: TextView = view.findViewById(R.id.TextViewTitleOfTopHotelItem)

}