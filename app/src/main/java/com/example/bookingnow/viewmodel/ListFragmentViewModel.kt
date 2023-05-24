package com.example.bookingnow.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bookingnow.model.database.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListFragmentViewModel(context: Application) : AndroidViewModel(context) {

    var daoRoom: RoomDao
    var RoomList: LiveData<List<RoomItem>>
    var TopRoomList: LiveData<List<RoomItem>>

    init {
        val db = RoomDataBase.getDataBase(context)
        daoRoom = db.DaoRoom()
        RoomList = daoRoom.getListOfItems()
        TopRoomList = daoRoom.getListOfTopItems()

    }

    fun addRoom(item: RoomItem) {
        viewModelScope.launch(Dispatchers.IO) {
            daoRoom.addItem(item)
        }
    }

    fun addImagesOfRoom(item: RoomPhotoItem) {
        viewModelScope.launch(Dispatchers.IO) {
            daoRoom.addImage(item)
        }
    }


}