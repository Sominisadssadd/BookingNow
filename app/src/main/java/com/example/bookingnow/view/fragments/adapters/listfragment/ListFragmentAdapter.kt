package com.example.bookingnow.view.fragments.adapters.listfragment

import android.util.Log
import android.view.LayoutInflater
import android.view.View
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


        }

        Log.d("SOM","bind")

    }


}