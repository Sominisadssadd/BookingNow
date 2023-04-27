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
import com.example.bookingnow.view.fragments.adapters.listfragment.TopRecyclerAdapter
import com.example.bookingnow.viewmodel.ListFragmentViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListFragment : Fragment() {


    lateinit var recAdapter: ListFragmentAdapter
    lateinit var recViewMain: RecyclerView
    lateinit var recViewTop: RecyclerView
    lateinit var recAdapterTop: TopRecyclerAdapter

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
        recAdapterTop = TopRecyclerAdapter()

        viewModel.RoomList.observe(viewLifecycleOwner) {
            recAdapter.submitList(it)
            recAdapterTop.submitList(it)
        }




        initRecyclerView(view)
    }

    fun initRecyclerView(view: View) {
        recViewMain = view.findViewById(R.id.RecyclerViewMain)

        recViewMain.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = recAdapter
        }

        recViewTop = view.findViewById(R.id.TopOfRecyclerView)
        recViewTop.apply {
            adapter = recAdapterTop
        }

    }

}