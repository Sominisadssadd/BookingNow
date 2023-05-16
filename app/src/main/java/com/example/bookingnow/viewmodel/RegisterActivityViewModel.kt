package com.example.bookingnow.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookingnow.model.database.RoomDao
import com.example.bookingnow.model.database.RoomDataBase
import com.example.bookingnow.model.database.UserItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterActivityViewModel(application: Application) : AndroidViewModel(application) {

    var listOfUsers: LiveData<List<UserItem>>
    var daoDB: RoomDao

    init {
        var roomDataBase = RoomDataBase.getDataBase(application)
        daoDB = roomDataBase.DaoRoom()
        listOfUsers = daoDB.getListOfUsers()
    }

    fun userRegister(userItem: UserItem) {
        viewModelScope.launch(Dispatchers.IO) {
            daoDB.registerUser(userItem)
        }
    }
}