package com.example.bookingnow.model

import androidx.lifecycle.LiveData
import com.example.bookingnow.model.database.FavoriteItem
import com.example.bookingnow.model.database.RoomDao
import com.example.bookingnow.model.database.RoomItem
import com.example.bookingnow.viewmodel.FragmentListRepository
import kotlinx.coroutines.CoroutineScope

class DataBaseReposiotry(val roomDao: RoomDao) : FragmentListRepository {

    val listOfRoom = roomDao.getListOfItems()
    override fun swipeToDelete(itemId: Int) {
        TODO("Not yet implemented")
    }

    override fun getItem(itemId: Int): RoomItem {
        TODO("Not yet implemented")
    }

    override fun addItem(item: RoomItem) {
        roomDao.addItem(item)
    }

    override fun addToFavorite(item: FavoriteItem) {
        roomDao.addToFavorite(item)
    }


    override fun addToHistory(itemId: Int) {
        TODO("Not yet implemented")
    }

    override fun editItem(itemId: Int) {
        TODO("Not yet implemented")
    }

    override fun deleteItem(item: RoomItem) {
        TODO("Not yet implemented")
    }

    override fun deleteFromFavoriteItem(itemId: Int) {
        TODO("Not yet implemented")
    }

    override fun deleteFromHistoryItem(itemId: Int) {
        TODO("Not yet implemented")
    }

    override fun bookingItem(itemId: Int) {
        TODO("Not yet implemented")
    }

    override fun blockUser(userId: Int) {
        TODO("Not yet implemented")
    }

    override fun deleteUserComments(commentId: Int) {
        TODO("Not yet implemented")
    }


}