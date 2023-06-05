package com.example.bookingnow.view.fragments

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingnow.R
import com.example.bookingnow.model.Consts.ROOM_ITEM_ARGUMENT
import com.example.bookingnow.model.database.FavoriteItem
import com.example.bookingnow.model.database.RoomItem
import com.example.bookingnow.view.fragments.adapters.roomdescriptionfragment.RoomDescriptionFragmentAdapter
import com.example.bookingnow.viewmodel.FavoriteFragmentViewModel
import com.example.bookingnow.viewmodel.ListFragmentViewModel
import com.example.bookingnow.viewmodel.RoomDescriptionViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso


class RoomDescriptionFragment : BottomSheetDialogFragment() {

    private lateinit var checkBoxAddFavorite: CheckBox
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var buttonBooking: Button
    private lateinit var roomDescriptionAdapter: RoomDescriptionFragmentAdapter
    private var requireFragment = "nothing"
    private lateinit var headerImageView: ImageView
    private lateinit var costTextView: TextView
    private lateinit var titleTextView: TextView
    private lateinit var importantInfoTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var typeOfRoomTextView: TextView
    private lateinit var countOfRoomTextView: TextView
    private lateinit var specialOfRoomTextView: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var currentFavorite: FavoriteItem /////////////////////////////////////
    private var userId: Int = 0
    lateinit var currentRoom: RoomItem
    var sharedPref: SharedPreferences? = null

    private val viewModel: RoomDescriptionViewModel by lazy {
        ViewModelProvider(this)[RoomDescriptionViewModel::class.java]
    }

    private val viewModelRooms: ListFragmentViewModel by lazy {
        ViewModelProvider(this)[ListFragmentViewModel::class.java]
    }

    private val viewModelFavorite: FavoriteFragmentViewModel by lazy {
        ViewModelProvider(this)[FavoriteFragmentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.room_fragment_description, container, false)
        sharedPref = activity?.getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
        userId = sharedPref!!.getInt("user_id", 0)
        // Показываем BottomSheetDialog весь экран
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )

        return view
    }

    private fun getArgsAndInitViews() {
        currentRoom = arguments?.getSerializable(ROOM_ITEM_ARGUMENT) as RoomItem
        requireFragment = arguments?.getString(GET_FRAGMENT).toString()

        Picasso.get().load(currentRoom.imageTitle).into(headerImageView)
        costTextView.text = currentRoom.cost
        titleTextView.text = currentRoom.nameOfRoom
        importantInfoTextView.text = currentRoom.ImportantInfo
        descriptionTextView.text = currentRoom.description
        typeOfRoomTextView.text = currentRoom.typeOfRoom
        countOfRoomTextView.text = currentRoom.countOfRooms
        specialOfRoomTextView.text = currentRoom.specialOfBooking

        if (currentRoom.isFavorite) {
            checkBoxAddFavorite.isChecked = true
        }

        viewModel.getListOfImage(currentRoom.id).observe(viewLifecycleOwner) {
            roomDescriptionAdapter = RoomDescriptionFragmentAdapter(it)
            recyclerView.adapter = roomDescriptionAdapter
        }
    }

    //dialog на весь экран
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener {

            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { it ->
                val behaviour = BottomSheetBehavior.from(it)
                setupFullHeight(it)
                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

        return dialog
    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkBoxAddFavorite = view.findViewById(R.id.checkBoxAddToFavorite)
        buttonBooking = view.findViewById(R.id.ButtonBooking)
        headerImageView = view.findViewById(R.id.HeaderImageView)
        costTextView = view.findViewById(R.id.TextViewCostOfRoomDescription)
        titleTextView = view.findViewById(R.id.TextViewHeaderOfRoomDescription)
        importantInfoTextView = view.findViewById(R.id.textViewImportantInfoOFRoomDescription)
        descriptionTextView = view.findViewById(R.id.TextViewRoomDescription)
        typeOfRoomTextView = view.findViewById(R.id.TextViewTypeOfRoom)
        countOfRoomTextView = view.findViewById(R.id.TextViewCountOfRoom)

        specialOfRoomTextView = view.findViewById(R.id.TextViewSpecialOfRoomDescription)
        recyclerView = view.findViewById(R.id.PhotoOfRoomReecyclerView)

        getArgsAndInitViews()

        checkBoxAddFavorite.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                Toast.makeText(requireContext(), "Добавленно в избранное", Toast.LENGTH_LONG)
                    .show()
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
                        isFavorite = true
                    )
                )
                viewModelFavorite.addToFavorite(FavoriteItem(0, userId, currentRoom.id))
            } else {
                Toast.makeText(requireContext(), "Удаленно из избранного", Toast.LENGTH_LONG)
                    .show()
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
                viewModelFavorite.listOfFavorite(userId).observe(viewLifecycleOwner) {
                    viewModelFavorite.removeFromFavorite(userId, currentRoom.id)
                }
            }
          }

        buttonBooking.setOnClickListener {

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        bottomNavigationView = requireActivity().findViewById(R.id.BottomNavigation)
        bottomNavigationView.visibility = View.VISIBLE

    }

    private fun snackBarRoomDescription(text: String) {

        var snackbar = Snackbar.make(
            requireView(),
            text,
            Snackbar.LENGTH_LONG
        )

        snackbar.show()

    }

    companion object {

        fun newInstanceDescriptionRoomItemFromList(
            item: RoomItem,
        ): RoomDescriptionFragment {
            return RoomDescriptionFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ROOM_ITEM_ARGUMENT, item)
                    putString(GET_FRAGMENT, "list")
                }
            }
        }

        fun newInstanceDescriptionRoomItemFromFavorite(
            item: RoomItem,
        ): RoomDescriptionFragment {
            return RoomDescriptionFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ROOM_ITEM_ARGUMENT, item)
                    putString(GET_FRAGMENT, "favorite")
                }
            }
        }


        const val GET_FRAGMENT = "fragment"

    }
}