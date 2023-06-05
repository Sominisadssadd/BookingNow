package com.example.bookingnow.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.bookingnow.model.database.FavoriteItem
import com.example.bookingnow.model.database.RoomDao
import com.example.bookingnow.model.database.RoomDataBase
import com.example.bookingnow.model.database.RoomItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteFragmentViewModel( context: Application) : AndroidViewModel(context) {

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


    fun getListOfFavoriteWithQuery(query: String): LiveData<List<RoomItem>> {
        return daoFavorite.getListOfFavoriteWithQuery("$query%")
    }

    fun removeFromFavorite(userId: Int, roomId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            daoFavorite.deleteFromFavorite(userId, roomId)
        }
    }

    fun deleteAllFavorite(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            daoFavorite.deleteAllFromFavorite(userId)
        }
    }

    fun listOfFavorite(userId: Int): LiveData<List<RoomItem>> {
        var listOfFavorite: LiveData<List<RoomItem>> = daoFavorite.getListOfFavorite(userId);
        return listOfFavorite;
    }


}