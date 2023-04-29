package com.example.bookingnow.view.fragments

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingnow.R
import com.example.bookingnow.model.database.RoomItem
import com.example.bookingnow.model.database.UserItem
import com.example.bookingnow.view.fragments.adapters.listfragment.ListFragmentAdapter
import com.example.bookingnow.view.fragments.adapters.listfragment.TopRecyclerAdapter
import com.example.bookingnow.viewmodel.ListFragmentViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListFragment : Fragment() {


    lateinit var recAdapter: ListFragmentAdapter
    lateinit var recViewMain: RecyclerView
    lateinit var recViewTop: RecyclerView
    lateinit var recAdapterTop: TopRecyclerAdapter
    lateinit var bottomNavigationBar: BottomNavigationView

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
        bottomNavigationBar = requireActivity().findViewById(R.id.BottomNavigation)
        initRecyclerView(view)
    }

    private fun initRecyclerView(view: View) {
        recViewMain = view.findViewById(R.id.RecyclerViewMain)

        recViewMain.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = recAdapter
        }

        recViewTop = view.findViewById(R.id.TopOfRecyclerView)
        recViewTop.apply {
            adapter = recAdapterTop
        }

        onItemClickListenerInMain()

    }

    private fun onItemClickListenerInMain() {
        recAdapter.onItemClickListener = {
//            findNavController().navigate(R.id.action_listFragment_to_roomDescriptionFragment)
//            bottomNavigationBar.visibility = View.INVISIBLE

            val bot = RoomDescriptionFragment()
            bot.show(requireActivity().supportFragmentManager,"justTAG")


        }
    }


}