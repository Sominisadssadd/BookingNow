package com.example.bookingnow.view.fragments

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.motion.widget.MotionLayout
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
import de.hdodenhof.circleimageview.CircleImageView


class ListFragment : Fragment(), SearchView.OnQueryTextListener, View.OnClickListener {


    private lateinit var recAdapter: ListFragmentAdapter
    private lateinit var recViewMain: RecyclerView
    private lateinit var recViewTop: RecyclerView
    private lateinit var recAdapterTop: TopRecyclerAdapter
    private lateinit var bottomNavigationBar: BottomNavigationView
    private lateinit var searchView: SearchView
    private lateinit var motionLayout: MotionLayout
    private lateinit var circleIamge: CircleImageView

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

        searchView = view.findViewById(R.id.SearcViewListFragment)
        motionLayout = view.findViewById(R.id.MotionLayoutListFragment)
        circleIamge = view.findViewById(R.id.ProfileIconInListFragment)
        recAdapter = ListFragmentAdapter()
        recAdapterTop = TopRecyclerAdapter()
        bottomNavigationBar = requireActivity().findViewById(R.id.BottomNavigation)

        viewModel.RoomList.observe(viewLifecycleOwner) {
            //ИЗМЕНИТЬ RECADAPTEERTOP на максимальное колличество элементов 4< i <6
            recAdapter.submitList(it)
            recAdapterTop.submitList(it)
        }

        initRecyclerView(view)

        searchView.setOnQueryTextListener(this)
        circleIamge.setOnClickListener(this)

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
            bot.show(requireActivity().supportFragmentManager, "justTAG")


        }
    }

    //При нажатии на кнопку найти  срабатывает этот метод
    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText?.length!! > 0) {
            //если длинна введенного текста больше 1 символа, то срабатывает анимация
            motionLayout.transitionToState(R.id.end)
        } else {
            motionLayout.transitionToState(R.id.start)
        }

        return true
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.ProfileIconInListFragment -> {
                findNavController().navigate(R.id.action_listFragment_to_profileFragment)
            }
        }
    }


}