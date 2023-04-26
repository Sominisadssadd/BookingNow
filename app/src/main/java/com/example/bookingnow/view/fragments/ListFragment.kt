package com.example.bookingnow.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingnow.R
import com.example.bookingnow.model.database.RoomItem
import com.example.bookingnow.model.database.UserItem
import com.example.bookingnow.view.fragments.adapters.listfragment.ListFragmentAdapter
import com.example.bookingnow.viewmodel.ListFragmentViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListFragment : Fragment() {


    lateinit var recAdapter: ListFragmentAdapter
    lateinit var recView: RecyclerView
    lateinit var buttonAdd: FloatingActionButton
    val viewModel: ListFragmentViewModel by lazy {
        ViewModelProvider(this)[ListFragmentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_list, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recAdapter = ListFragmentAdapter()

        viewModel.RoomList.observe(viewLifecycleOwner) {
            recAdapter.submitList(it)
        }

        buttonAdd = view.findViewById(R.id.AddItem)
        buttonAdd.setOnClickListener {
            val item = RoomItem(0, "1", "2", "3", "4", "1", "1", "10")
            viewModel.addRoom(item)
            val user = UserItem(0)
            viewModel.testAddUser(user)
        }

        initRecyclerView(view)

    }

    fun initRecyclerView(view: View) {

        recView = view.findViewById(R.id.RecyclerRoom)
        recView.layoutManager = LinearLayoutManager(activity)
        recView.adapter = recAdapter

    }

}