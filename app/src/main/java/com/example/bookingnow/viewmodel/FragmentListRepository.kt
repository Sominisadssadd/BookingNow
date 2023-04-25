package com.example.bookingnow.viewmodel

import androidx.lifecycle.LiveData
import com.example.bookingnow.model.RoomItem

interface FragmentListRepository {

    fun swipeToDelete(itemId: Int)

    fun getItem(): RoomItem

    fun getListItem(): LiveData<List<RoomItem>>

    fun addItem()

    fun addToFavorite(itemId: Int)

    fun addToHistory(itemId: Int)

    fun editItem(itemId: Int)

    fun deleteItem(itemId: Int)

    fun deleteFromFavoriteItem(itemId: Int)

    fun deleteFromHistoryItem(itemId: Int)

    fun bookingItem(itemId: Int)

    fun blockUser(userId: Int)

    fun deleteUserComments(commentId: Int)

}