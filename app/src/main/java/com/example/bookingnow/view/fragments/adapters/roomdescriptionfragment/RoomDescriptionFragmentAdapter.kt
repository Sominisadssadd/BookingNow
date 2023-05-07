package com.example.bookingnow.view.fragments.adapters.roomdescriptionfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingnow.R
import com.example.bookingnow.model.database.RoomPhotoItem
import com.squareup.picasso.Picasso

class RoomDescriptionFragmentAdapter(var list: List<RoomPhotoItem>) :
    RecyclerView.Adapter<RoomDescriptionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomDescriptionViewHolder {
        return RoomDescriptionViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_of_photo_description, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RoomDescriptionViewHolder, position: Int) {
        Picasso.get().load(list[position].photos).into(holder.imageViewForRecyclerDescription)
    }

    override fun getItemCount() = list.size
}