package com.example.bookingnow.view.fragments.adapters.listfragment

import androidx.recyclerview.widget.DiffUtil
import com.example.bookingnow.model.database.RoomItem

class DiffUtliBallBackItemTop : DiffUtil.ItemCallback<RoomItem>() {

    override fun areItemsTheSame(oldItem: RoomItem, newItem: RoomItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RoomItem, newItem: RoomItem): Boolean {
        return oldItem == newItem
    }
}