package com.example.bookingnow.view.fragments.adapters.listfragment

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.strictmode.UntaggedSocketViolation
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.recyclerview.widget.ListAdapter
import com.example.bookingnow.R
import com.example.bookingnow.model.database.RoomItem
import com.example.bookingnow.view.fragments.RoomDescriptionFragment
import com.squareup.picasso.Picasso

class ListFragmentAdapter(var context: Context) :
    ListAdapter<RoomItem, ListFragmentViewHolder>(DiffUtillCalbackItem()) {

    var onItemClickListener: ((item: RoomItem) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListFragmentViewHolder {

        return ListFragmentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.hotel_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListFragmentViewHolder, position: Int) {
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