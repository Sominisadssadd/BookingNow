package com.example.bookingnow.view.fragments.adapters.roomdescriptionfragment

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingnow.R

class RoomDescriptionViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    var imageViewForRecyclerDescription: ImageView =
        view.findViewById(R.id.ImageViewItemOfDescriptionFragment)
}