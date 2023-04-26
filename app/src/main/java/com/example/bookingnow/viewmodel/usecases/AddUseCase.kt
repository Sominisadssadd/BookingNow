package com.example.bookingnow.viewmodel.usecases

import com.example.bookingnow.model.database.RoomItem
import com.example.bookingnow.viewmodel.FragmentListRepository

class AddUseCase(private val fragmentList: FragmentListRepository) {

    fun addUseCase(item: RoomItem) {
        fragmentList.addItem(item)
    }

}