package com.example.bookingnow.view.fragments.adapters.favoritefragment

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingnow.R
import com.google.android.material.card.MaterialCardView

class FavoriteViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val card : MaterialCardView = view.findViewById(R.id.CardContent)
    val imageView: ImageView = view.findViewById(R.id.ImageViewOfHotelItem)
    val title: TextView = view.findViewById(R.id.TextViewTitleOfHotelItem)
    val type: TextView = view.findViewById(R.id.TextViewTypeOfHotelItem)
    val cost: TextView = view.findViewById(R.id.TextViewCostOfHotelItem)

}