package com.example.bookingnow.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bookingnow.model.database.RoomDao
import com.example.bookingnow.model.database.RoomDataBase
import com.example.bookingnow.model.database.RoomPhotoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomDescriptionViewModel(context: Application) : AndroidViewModel(context) {

    val dao: RoomDao

    init {
        val db = RoomDataBase.getDataBase(context)
        dao = db.DaoRoom()


    }

    fun addPhoto(item: RoomPhotoItem) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.addImage(item)
        }
    }

    fun getListOfImage(RoomItemId: Int): LiveData<List<RoomPhotoItem>> {
        val RoomImageList = dao.getListOfPhotos(RoomItemId)
        return RoomImageList as LiveData<List<RoomPhotoItem>>
    }


}