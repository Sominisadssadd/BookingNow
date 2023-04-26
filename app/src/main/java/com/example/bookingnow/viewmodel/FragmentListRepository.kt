package com.example.bookingnow.viewmodel

import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.bookingnow.model.database.FavoriteItem
import com.example.bookingnow.model.database.RoomItem

interface FragmentListRepository {

    fun swipeToDelete(itemId: Int)

    fun getItem(itemId: Int): RoomItem

    fun addItem(item: RoomItem)

    fun addToFavorite(item: FavoriteItem)

    fun addToHistory(itemId: Int)

    fun editItem(itemId: Int)

    fun deleteItem(item: RoomItem)

    fun deleteFromFavoriteItem(itemId: Int)

    fun deleteFromHistoryItem(itemId: Int)

    fun bookingItem(itemId: Int)

    fun blockUser(userId: Int)

    fun deleteUserComments(commentId: Int)

}