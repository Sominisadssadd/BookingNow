package com.example.bookingnow.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.bookingnow.model.database.FavoriteItem
import com.example.bookingnow.model.database.RoomDao
import com.example.bookingnow.model.database.RoomDataBase
import com.example.bookingnow.model.database.RoomItem

class FavoriteFragmentViewModel(context: Application) : AndroidViewModel(context) {

    //изменить модификатор листа, чтоб мы могли получать его только из функции, а не из листа и менять его
    var daoFavorite: RoomDao
    var listOfFavorite: LiveData<List<RoomItem>>

    init {
        val db = RoomDataBase.getDataBase(context)
        daoFavorite = db.DaoRoom()
        listOfFavorite = daoFavorite.getListOfFavorite()
    }

    fun addToFavorite(item: FavoriteItem) {
        daoFavorite.addToFavorite(item)
    }


}