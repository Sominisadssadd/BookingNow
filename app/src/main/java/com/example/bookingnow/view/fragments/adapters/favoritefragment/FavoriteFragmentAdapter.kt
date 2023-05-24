package com.example.bookingnow.view.fragments.adapters.favoritefragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.bookingnow.R
import com.example.bookingnow.model.database.RoomItem
import com.squareup.picasso.Picasso

class FavoriteFragmentAdapter :
    ListAdapter<RoomItem, FavoriteViewHolder>(DiffUtilCallbackItemFavorite()) {

    var onItemClickListener: ((item: RoomItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.hotel_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        if (currentList.isNotEmpty()) {
            val currentItem = getItem(position)

            holder.apply {
                card.setOnClickListener {
                    onItemClickListener?.invoke(currentItem)
                }
                title.text = currentItem.nameOfRoom
                type.text = currentItem.typeOfRoom
                cost.text = currentItem.cost


                //Ошибка свзяанна с отказом доступа к внешнему хранилищу
                Picasso.get().load(currentItem.imageTitle).into(imageView)


            }


        }
    }
}