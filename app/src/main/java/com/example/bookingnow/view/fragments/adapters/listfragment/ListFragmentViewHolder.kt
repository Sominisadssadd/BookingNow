package com.example.bookingnow.view.fragments.adapters.listfragment

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingnow.R

class ListFragmentViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val TvTitle: TextView = view.findViewById(R.id.Title)
    val TvIInfo: TextView = view.findViewById(R.id.ImportantInfo)
    val TvDescription: TextView = view.findViewById(R.id.Description)
    val TvCount: TextView = view.findViewById(R.id.RoomCount)
    val TvCost: TextView = view.findViewById(R.id.Cost)
    val TvID: TextView = view.findViewById(R.id.IDDD)


}