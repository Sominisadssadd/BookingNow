package com.example.bookingnow.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import androidx.room.RoomMasterTable
import com.example.bookingnow.model.database.*
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

    fun getListOfTopRoomItems(): List<RoomItem> {
        return  daoRoom.getListOfTopItems()
    }


    fun getListOfHotelList(): List<RoomItem> {
        return daoRoom.getListOfItemsList()
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

    fun updateRoom(item: RoomItem) {
        viewModelScope.launch(Dispatchers.IO) {
            daoRoom.updateItem(item)
        }
    }

    fun getListOfRoomsWithQuery(query: String): LiveData<List<RoomItem>> {
        return daoRoom.getListOfRoomsWithQuery("$query%")
    }


}