package com.example.bookingnow.view.fragments.adapters.favoritefragment

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingnow.R

class FavoriteViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val TvTitle: TextView = view.findViewById(R.id.TitleFavorite)
    val TvIInfo: TextView = view.findViewById(R.id.ImportantInfoFavorite)
    val TvDescription: TextView = view.findViewById(R.id.DescriptionFavorite)
    val TvCount: TextView = view.findViewById(R.id.RoomCountFavorite)
    val TvCost: TextView = view.findViewById(R.id.CostFavorite)
    val TvID: TextView = view.findViewById(R.id.IDDDFavorite)

}