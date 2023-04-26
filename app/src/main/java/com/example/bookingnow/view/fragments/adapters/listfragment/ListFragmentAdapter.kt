package com.example.bookingnow.view.fragments.adapters.listfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.bookingnow.R
import com.example.bookingnow.model.database.RoomItem

class ListFragmentAdapter() :
    ListAdapter<RoomItem, ListFragmentViewHolder>(DiffUtillCalbackItem()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListFragmentViewHolder {
        return ListFragmentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.hotel_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListFragmentViewHolder, position: Int) {
        if (currentList.isNotEmpty()) {
            val currentItem = getItem(position)

            with(holder) {
                TvTitle.text = currentItem.nameOfRoom
                TvIInfo.text = currentItem.ImportantInfo
                TvDescription.text = currentItem.description
                TvCount.text = currentItem.countOfRooms
                TvCost.text = currentItem.cost
                TvID.text = currentItem.id.toString()
            }
        }

    }


}