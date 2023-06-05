package com.example.bookingnow.view.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingnow.R
import com.example.bookingnow.model.Consts
import com.example.bookingnow.model.database.FavoriteItem
import com.example.bookingnow.model.database.RoomItem
import com.example.bookingnow.view.fragments.adapters.favoritefragment.FavoriteFragmentAdapter
import com.example.bookingnow.viewmodel.FavoriteFragmentViewModel
import com.example.bookingnow.viewmodel.ListFragmentViewModel
import com.example.bookingnow.viewmodel.RegisterActivityViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.flow.combine


class FavoriteFragment : Fragment() {

    var sharedPref: SharedPreferences? = null
    private lateinit var recView: RecyclerView
    private lateinit var recViewAdapter: FavoriteFragmentAdapter
    private lateinit var deleteAllFavoriteButton: ImageView
    private lateinit var emptyImage: ImageView
    private lateinit var searchView: SearchView
    private lateinit var profileImageView: CircleImageView
    private var userId: Int = 0


    private val regViewModel: RegisterActivityViewModel by lazy {
        ViewModelProvider(this)[RegisterActivityViewModel::class.java]
    }

    private val viewModel: FavoriteFragmentViewModel by lazy {
        ViewModelProvider(this)[FavoriteFragmentViewModel::class.java]
    }

    private val viewModelRooms: ListFragmentViewModel by lazy {
        ViewModelProvider(this)[ListFragmentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        sharedPref = activity?.getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPref = activity?.getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
        userId = sharedPref!!.getInt("user_id", 0)

        recViewAdapter = FavoriteFragmentAdapter()



        viewModel.listOfFavorite(sharedPref!!.getInt("user_id", 0)).observe(viewLifecycleOwner) {
            recViewAdapter.submitList(it)
            if (it.isNotEmpty()) {
                emptyImage.visibility = View.INVISIBLE
            } else {
                emptyImage.visibility = View.VISIBLE
            }
        }
        initRecyclerView(view)
        initialView(view)
        swipeToDelete()
        searchViewFunction()
        getUserImage()

        profileImageView.setOnClickListener{
            findNavController().navigate(R.id.action_favoriteFragment_to_profileFragment)
        }

        deleteAllFavoriteButton.setOnClickListener {
            viewModel.deleteAllFavorite(userId)
        }

    }

    private fun getUserImage(){
        regViewModel.listOfUsers.observe(viewLifecycleOwner) {
            it.forEach { user ->
                if (user.id == sharedPref!!.getInt("user_id", 0)) {
                    Picasso.get().load(user.photo).into(profileImageView)
                }
            }
        }
    }



    private fun searchViewFunction() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.getListOfFavoriteWithQuery(newText!!)
                    .observe(viewLifecycleOwner, Observer {
                        recViewAdapter.submitList(it)

                    })
                return true
            }

        })
    }


    private fun swipeToDelete() {
        val swipeToDelete = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val currentRoom = recViewAdapter.currentList[viewHolder.adapterPosition]
                viewModel.removeFromFavorite(
                    userId,
                    currentRoom.id
                )

                viewModelRooms.updateRoom(
                    RoomItem(
                        currentRoom.id,
                        currentRoom.nameOfRoom,
                        currentRoom.ImportantInfo,
                        currentRoom.description,
                        currentRoom.countOfRooms,
                        currentRoom.specialOfBooking,
                        currentRoom.imageTitle,
                        currentRoom.typeOfRoom,
                        currentRoom.cost,
                        isFavorite = false
                    )
                )
            }

        }

        val itemTouchHelper = ItemTouchHelper(swipeToDelete)
        itemTouchHelper.attachToRecyclerView(recView)
    }


    private fun initialView(view: View) {
        deleteAllFavoriteButton = view.findViewById(R.id.buttonDeleteAllFavorite)
        emptyImage = view.findViewById(R.id.imageViewHereIsEmpty)
        searchView = view.findViewById(R.id.SearcViewFavoriteFragment)
        profileImageView = view.findViewById(R.id.ProfileImage)
    }

    fun initRecyclerView(view: View) {
        recView = view.findViewById(R.id.RecyclerFavorite)
        recView.layoutManager = LinearLayoutManager(activity)
        recView.adapter = recViewAdapter
        onItemClickListenerInMain()
    }

    private fun onItemClickListenerInMain() {
        recViewAdapter.onItemClickListener = {
            val descriptionFragmentInstance =
                RoomDescriptionFragment.newInstanceDescriptionRoomItemFromFavorite(it)
            descriptionFragmentInstance.show(
                requireActivity().supportFragmentManager,
                Consts.ROOM_DESCRIPTION_FRAGMENT
            )
        }
    }
}