package com.example.bookingnow.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface RoomDao {


    //RoomItem table
    @Update
    fun updateItem(item: RoomItem)

    @Delete
    fun deleteItem(item: RoomItem)

    @Insert(entity = RoomItem::class)
    fun addItem(item: RoomItem)

    @Query("select * from Rooms group by id")
    fun getListOfItems(): LiveData<List<RoomItem>>


    @Insert(entity = FavoriteItem::class)
    fun addToFavorite(item: FavoriteItem)

    @Insert(entity = UserItem::class)
    fun registerUser(user: UserItem)

    //RoomPhotoItem Table
    @Query("select * from RoomImage order by RoomId")
    fun getListOfPhotos(): LiveData<List<RoomPhotoItem>>

    @Insert(entity = RoomPhotoItem::class)
    fun addImage(item: RoomPhotoItem)




    @Query(
        "select Rooms.id,RoomName,RoomImportantInfo,RoomDescription,RoomCount,RoomSpecial,RoomType," +
                "RoomCost from Rooms inner join FavoriteRooms where Rooms.id = FavoriteRooms.RoomId "
    )
    fun getListOfFavorite(): LiveData<List<RoomItem>>




}