package com.example.bookingnow.view.fragments.adapters.roomdescriptionfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingnow.R
import com.example.bookingnow.model.database.RoomPhotoItem

class RoomDescriptionFragmentAdapter : RecyclerView.Adapter<RoomDescriptionViewHolder>() {

    var list: List<RoomPhotoItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomDescriptionViewHolder {
        return RoomDescriptionViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_of_photo_description, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RoomDescriptionViewHolder, position: Int) {

    }

    override fun getItemCount() = list.size
}