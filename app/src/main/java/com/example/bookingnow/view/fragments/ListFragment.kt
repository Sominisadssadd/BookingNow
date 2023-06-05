package com.example.bookingnow.view.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingnow.R
import com.example.bookingnow.model.Consts.ROOM_DESCRIPTION_FRAGMENT
import com.example.bookingnow.view.activities.MainActivity
import com.example.bookingnow.view.fragments.adapters.listfragment.ListFragmentAdapter
import com.example.bookingnow.view.fragments.adapters.listfragment.TopRecyclerAdapter
import com.example.bookingnow.viewmodel.ListFragmentViewModel
import com.example.bookingnow.viewmodel.RegisterActivityViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.*


class ListFragment : Fragment(), SearchView.OnQueryTextListener, View.OnClickListener {


    private lateinit var recAdapter: ListFragmentAdapter
    private lateinit var recViewMain: RecyclerView
    private lateinit var recViewTop: RecyclerView
    private lateinit var recAdapterTop: TopRecyclerAdapter
    private lateinit var bottomNavigationBar: BottomNavigationView
    private lateinit var searchView: SearchView
    private lateinit var motionLayout: MotionLayout
    private lateinit var circleIamge: CircleImageView
    private lateinit var buttonAdd: FloatingActionButton

    var sharedPref: SharedPreferences? = null


    val viewModel: ListFragmentViewModel by lazy {
        ViewModelProvider(this)[ListFragmentViewModel::class.java]
    }
    val regViewModel: RegisterActivityViewModel by lazy {
        ViewModelProvider(this)[RegisterActivityViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        sharedPref = activity?.getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchView = view.findViewById(R.id.SearcViewListFragment)
        motionLayout = view.findViewById(R.id.MotionLayoutListFragment)
        circleIamge = view.findViewById(R.id.ProfileIconInListFragment)
        recAdapter = ListFragmentAdapter(requireContext())
        recAdapterTop = TopRecyclerAdapter()
        bottomNavigationBar = requireActivity().findViewById(R.id.BottomNavigation)
        buttonAdd = view.findViewById(R.id.ButtonAddFromList)
        getList()



        regViewModel.listOfUsers.observe(viewLifecycleOwner) {
            it.forEach { user ->
                if (user.id == sharedPref!!.getInt("user_id", 0)) {
                    Picasso.get().load(user.photo).into(circleIamge)
                }
            }
        }


        initRecyclerView(view)

        searchView.setOnQueryTextListener(this)
        circleIamge.setOnClickListener(this)
        buttonAdd.setOnClickListener(this)

    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getList() {
        GlobalScope.launch(Dispatchers.IO) {
            recAdapter.submitList(viewModel.getListOfHotelList())
            recAdapterTop.submitList(viewModel.getListOfTopRoomItems())
        }
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
            val descriptionFragmentInstance =
                RoomDescriptionFragment.newInstanceDescriptionRoomItemFromList(it)
            descriptionFragmentInstance.show(
                requireActivity().supportFragmentManager,
                ROOM_DESCRIPTION_FRAGMENT
            )
        }
    }

    //При нажатии на кнопку найти  срабатывает этот метод
    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        viewModel.getListOfRoomsWithQuery(newText!!).observe(viewLifecycleOwner, Observer {
            recAdapter.submitList(it)
            if (newText.isNotEmpty()) {
                motionLayout.transitionToState(R.id.end)
            } else {
                motionLayout.transitionToState(R.id.start)
                recViewMain.smoothScrollToPosition(0);
            }

        })
        return true
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.ProfileIconInListFragment -> {
                findNavController().navigate(R.id.action_listFragment_to_profileFragment)
            }
            R.id.ButtonAddFromList -> findNavController().navigate(R.id.action_listFragment_to_addRoomFragment)
        }
    }


}