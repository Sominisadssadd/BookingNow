package com.example.bookingnow.view.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingnow.R
import com.example.bookingnow.model.Consts
import com.example.bookingnow.model.database.FavoriteItem
import com.example.bookingnow.view.fragments.adapters.favoritefragment.FavoriteFragmentAdapter
import com.example.bookingnow.viewmodel.FavoriteFragmentViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class FavoriteFragment : Fragment() {

    var sharedPref: SharedPreferences? = null
    private lateinit var recView: RecyclerView
    private lateinit var recViewAdapter: FavoriteFragmentAdapter
    private lateinit var buttonAdd: FloatingActionButton
    private val viewModel: FavoriteFragmentViewModel by lazy {
        ViewModelProvider(this)[FavoriteFragmentViewModel::class.java]
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

        recViewAdapter = FavoriteFragmentAdapter()

        viewModel.listOfFavorite(sharedPref!!.getInt("user_id", 0)).observe(viewLifecycleOwner) {
            recViewAdapter.submitList(it)
        }

        initRecyclerView(view)
    }

    fun initRecyclerView(view: View) {
        recView = view.findViewById(R.id.RecyclerFavorite)
        recView.layoutManager = LinearLayoutManager(activity)
        recView.adapter = recViewAdapter
        onItemClickListenerInMain()
    }

    private fun onItemClickListenerInMain() {
        recViewAdapter.onItemClickListener = {
            val descriptionFragmentInstance = RoomDescriptionFragment.newInstanceAddRoomItem(it)
            descriptionFragmentInstance.show(
                requireActivity().supportFragmentManager,
                Consts.ROOM_DESCRIPTION_FRAGMENT
            )
        }
    }
}