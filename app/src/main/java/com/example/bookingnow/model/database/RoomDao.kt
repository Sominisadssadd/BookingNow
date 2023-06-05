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


    @Query("select * from Rooms group by id")
    fun getListOfItemsList(): List<RoomItem>

    @Query("select * from Rooms where id > 3 limit 5")
    fun getListOfTopItems(): List<RoomItem>

    @Query("select * from rooms where RoomName like :query ")
    fun getListOfRoomsWithQuery(query: String): LiveData<List<RoomItem>>

    //favorite table
    @Insert(entity = FavoriteItem::class)
    fun addToFavorite(item: FavoriteItem)

    @Query("delete from FavoriteRooms where UserId = :userID and RoomId  = :roomItemId")
    fun deleteFromFavorite(userID: Int, roomItemId: Int)

    @Query("select * from rooms inner join FavoriteRooms on rooms.id = FavoriteRooms.RoomId where RoomName like :query ")
    fun getListOfFavoriteWithQuery(query: String): LiveData<List<RoomItem>>


    //удалить все заметки текущего юзера
    @Query("delete from FavoriteRooms where UserId = :userID")
    fun deleteAllFromFavorite(userID: Int);

    @Query(
        "select Rooms.id,RoomName,RoomImportantInfo,RoomDescription,RoomCount,RoomSpecial,RoomType,ImageTitle, IsFavorite," +
                "RoomCost from Rooms inner join FavoriteRooms where Rooms.id = FavoriteRooms.RoomId and  FavoriteRooms.UserId = :userID"
    )
    fun getListOfFavorite(userID: Int): LiveData<List<RoomItem>>


    //users
    @Insert(entity = UserItem::class)
    fun registerUser(user: UserItem)

    @Update(entity = UserItem::class)
    fun updateUserInformation(user: UserItem)

    @Query("select * from Users")
    fun getListOfUsers(): LiveData<List<UserItem>>

    @Query("select * from Users  where id = :userId")
    fun getUserInformation(userId: Int): UserItem


    //RoomPhotoItem Table
    @Query("select * from RoomImage where RoomId = :roomItemId")
    fun getListOfPhotos(roomItemId: Int): LiveData<List<RoomPhotoItem>>

    @Insert(entity = RoomPhotoItem::class)
    fun addImage(item: RoomPhotoItem)


}