package com.example.bookingnow.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface RoomDao {

    //entity определяет вставку в нужную таблицу
    @Insert(entity = RoomItem::class)
    fun addItem(item: RoomItem)

    @Insert(entity = FavoriteItem::class)
    fun addToFavorite(item: FavoriteItem)


    @Query("select * from Rooms group by id")
    fun getListOfItems(): LiveData<List<RoomItem>>

    @Query("select id,RoomName,RoomImportantInfo,RoomDescription,RoomCount,RoomSpecial,RoomType," +
            "RoomCost from Rooms inner join FavoriteRooms where Rooms.id = FavoriteRooms.RoomId ")
    fun getListOfFavorite(): LiveData<List<RoomItem>>

    @Update
    fun updateItem(item: RoomItem)

    @Delete
    fun deleteItem(item: RoomItem)


}