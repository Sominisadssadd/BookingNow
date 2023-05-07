package com.example.bookingnow.view.fragments.adapters.listfragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.bookingnow.R
import com.example.bookingnow.model.database.RoomItem
import com.squareup.picasso.Picasso

class TopRecyclerAdapter : ListAdapter<RoomItem, TopRecyclerViewHolder>(DiffUtliBallBackItemTop()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRecyclerViewHolder {

        return TopRecyclerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.top_hotel_item, parent, false)

        )
    }

    override fun onBindViewHolder(holder: TopRecyclerViewHolder, position: Int) {
        if (currentList.isNotEmpty()) {
            val currentItem = getItem(position)
            holder.apply {
                Picasso.get().load(currentItem.imageTitle).into(image)
                title.text = currentItem.nameOfRoom
                type.text = currentItem.typeOfRoom
            }
        }
    }
}