package com.example.bookingnow.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bookingnow.model.database.RoomDataBase
import com.example.bookingnow.model.database.RoomDao
import com.example.bookingnow.model.database.RoomItem
import com.example.bookingnow.model.database.UserItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListFragmentViewModel(context: Application) : AndroidViewModel(context) {

    var daoRoom: RoomDao
    var RoomList: LiveData<List<RoomItem>>

    init {
        val db = RoomDataBase.getDataBase(context)
        daoRoom = db.DaoRoom()
        RoomList = daoRoom.getListOfItems()

    }

    fun addRoom(item: RoomItem) {
        viewModelScope.launch(Dispatchers.IO) {
            daoRoom.addItem(item)
        }

    }

    fun testAddUser(user: UserItem) {
        viewModelScope.launch(Dispatchers.IO) {
            daoRoom.registerUser(user)
        }
    }

}