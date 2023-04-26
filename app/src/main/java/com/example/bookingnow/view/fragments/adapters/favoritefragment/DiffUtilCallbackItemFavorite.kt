package com.example.bookingnow.view.fragments.adapters.favoritefragment

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingnow.model.database.RoomItem

class DiffUtilCallbackItemFavorite : DiffUtil.ItemCallback<RoomItem>() {

    override fun areItemsTheSame(oldItem: RoomItem, newItem: RoomItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RoomItem, newItem: RoomItem): Boolean {
        return oldItem == newItem
    }


}