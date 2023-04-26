package com.example.bookingnow.view.fragments.adapters.favoritefragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.bookingnow.R
import com.example.bookingnow.model.database.RoomItem

class FavoriteFragmentAdapter :
    ListAdapter<RoomItem, FavoriteViewHolder>(DiffUtilCallbackItemFavorite()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.favorite_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val item = getItem(position)

        with(holder){
            TvTitle.text = item.nameOfRoom
            TvIInfo.text = item.ImportantInfo
            TvDescription.text = item.description
            TvCount.text = item.countOfRooms
            TvCost.text = item.cost
            TvID.text = item.id.toString()
        }

    }
}