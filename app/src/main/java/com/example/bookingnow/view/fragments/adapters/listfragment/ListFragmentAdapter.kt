package com.example.bookingnow.view.fragments.adapters.listfragment

import android.os.strictmode.UntaggedSocketViolation
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.bookingnow.R
import com.example.bookingnow.model.database.RoomItem
import com.example.bookingnow.view.fragments.RoomDescriptionFragment

class ListFragmentAdapter() :
    ListAdapter<RoomItem, ListFragmentViewHolder>(DiffUtillCalbackItem()) {

    var onItemClickListener: ((item: RoomItem)->Unit)?=null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListFragmentViewHolder {

        return ListFragmentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.hotel_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListFragmentViewHolder, position: Int) {
        if (currentList.isNotEmpty()) {
            val currentItem = getItem(position)

            holder.card.setOnClickListener{
                onItemClickListener?.invoke(currentItem)
            }

        }


        Log.d("SOM", "bind")

    }


}