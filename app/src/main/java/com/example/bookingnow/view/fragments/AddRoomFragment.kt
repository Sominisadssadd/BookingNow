package com.example.bookingnow.view.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.bookingnow.R
import com.example.bookingnow.model.Consts
import com.example.bookingnow.model.Consts.EMPTY_STRING_FIELD
import com.example.bookingnow.model.Consts.REQUEST_CODE
import com.example.bookingnow.model.Consts.ZERO_INT_FIELD
import com.example.bookingnow.model.database.RoomItem
import com.example.bookingnow.model.database.RoomPhotoItem
import com.example.bookingnow.viewmodel.ListFragmentViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView


class AddRoomFragment : Fragment(), View.OnClickListener {


    lateinit var buttonADD: Button
    lateinit var buttonSelectImage: Button
    var roomItemId = ZERO_INT_FIELD
    private var imageUri: MutableList<String> = mutableListOf()

    private lateinit var costTextView: TextView
    private lateinit var titleTextView: TextView
    private lateinit var importantInfoTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var typeOfRoomTextView: TextView
    private lateinit var countOfRoomTextView: TextView
    private lateinit var specialOfRoomTextView: TextView


    private val viewModel: ListFragmentViewModel by lazy {
        ViewModelProvider(this)[ListFragmentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_add_room, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonADD = view.findViewById(R.id.ButtonAddFragment)
        buttonSelectImage = view.findViewById(R.id.ButtonSelectImageAddFragment)
        costTextView = view.findViewById(R.id.CostOfRoomTextView)
        titleTextView = view.findViewById(R.id.NameOfRoomTextView)
        importantInfoTextView = view.findViewById(R.id.ImportantOfRoomTextView)
        descriptionTextView = view.findViewById(R.id.DescriptionOfRoomTextView)
        typeOfRoomTextView = view.findViewById(R.id.TypeOfRoomTextView)
        countOfRoomTextView = view.findViewById(R.id.CountOfRoomTextView)
        specialOfRoomTextView = view.findViewById(R.id.SpecialOfRoomTextView)

        //когда мы добавляем элемент в БД, то мы получаем id элемента, чтобы добавить фотографии
        //данной комнаты в БД
        viewModel.RoomList.observe(viewLifecycleOwner) {
            if (it.isNotEmpty() && roomItemId > 0) {
                var idOfLastItem = it[it.size - 1].id
                imageUri.forEach {
                    viewModel.addImagesOfRoom(RoomPhotoItem(0, idOfLastItem, it))
                }
                activity?.onBackPressed()
            }
            //При запуске фрагмента сразу срабатывает код в observer, поэтому мы не даем вносить в
            //БД изображение сразу при запуске, путёем использования условия
            roomItemId++
        }

        buttonADD.setOnClickListener(this)
        buttonSelectImage.setOnClickListener(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            imageUri.add(data.data.toString())
        }
    }

    //ДОБАВЛЯЕМ ФОТО В БД, ПОСЛЕ ТОГО КАК ДОБАВИЛИ В RoomItem

    override fun onClick(view: View) {
        when (view.id) {
            R.id.ButtonAddFragment -> {


                val cost = costTextView.text.toString()
                val name = titleTextView.text.toString()
                val important = importantInfoTextView.text.toString()
                val description = descriptionTextView.text.toString()
                val typeOfRoom = typeOfRoomTextView.text.toString()
                val count = countOfRoomTextView.text.toString()
                val special = specialOfRoomTextView.text.toString()

                var RoomItem = RoomItem(
                    0,
                    nameOfRoom = name,
                    ImportantInfo = important,
                    description = description,
                    typeOfRoom = typeOfRoom,
                    countOfRooms = count,
                    specialOfBooking = special,
                    imageTitle = imageUri[0],
                    cost = cost,
                    isFavorite = false
                )
                viewModel.addRoom(RoomItem)
            }
            R.id.ButtonSelectImageAddFragment -> {
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, REQUEST_CODE)
            }
        }
    }
}