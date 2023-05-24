package com.example.bookingnow.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bookingnow.model.database.FavoriteItem
import com.example.bookingnow.model.database.RoomDao
import com.example.bookingnow.model.database.RoomDataBase
import com.example.bookingnow.model.database.RoomItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteFragmentViewModel(context: Application) : AndroidViewModel(context) {

    //изменить модификатор листа, чтоб мы могли получать его только из функции, а не из листа и менять его
    var daoFavorite: RoomDao


    init {
        val db = RoomDataBase.getDataBase(context)
        daoFavorite = db.DaoRoom()

    }

    //операции с базой данных только в фоновом потоке
    fun addToFavorite(item: FavoriteItem) {
        viewModelScope.launch(Dispatchers.IO) {
            daoFavorite.addToFavorite(item)
        }

    }

    fun removeFromFavorite(item: FavoriteItem){
        viewModelScope.launch(Dispatchers.IO){
            daoFavorite.deleteFromFavorite(item)
        }
    }

    fun listOfFavorite(userId: Int): LiveData<List<RoomItem>> {
        var listOfFavorite: LiveData<List<RoomItem>> = daoFavorite.getListOfFavorite(userId);
        return listOfFavorite;
    }



}