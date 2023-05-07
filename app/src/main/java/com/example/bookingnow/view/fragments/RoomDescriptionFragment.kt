package com.example.bookingnow.view.fragments

import android.app.DatePickerDialog
import android.app.Dialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingnow.R
import com.example.bookingnow.model.Consts.ROOM_ITEM_ARGUMENT
import com.example.bookingnow.model.database.RoomItem
import com.example.bookingnow.model.database.RoomPhotoItem
import com.example.bookingnow.view.fragments.adapters.roomdescriptionfragment.RoomDescriptionFragmentAdapter
import com.example.bookingnow.viewmodel.RoomDescriptionViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import org.w3c.dom.Text
import java.lang.RuntimeException


class RoomDescriptionFragment : BottomSheetDialogFragment(), View.OnClickListener {

    private lateinit var checkBoxAddFavorite: CheckBox
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var buttonBooking: Button
    private lateinit var roomDescriptionAdapter: RoomDescriptionFragmentAdapter
    
    private lateinit var headerImageView: ImageView
    private lateinit var costTextView: TextView
    private lateinit var titleTextView: TextView
    private lateinit var importantInfoTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var typeOfRoomTextView: TextView
    private lateinit var countOfRoomTextView: TextView
    private lateinit var specialOfRoomTextView: TextView

    private lateinit var recyclerView: RecyclerView

    private val viewModel: RoomDescriptionViewModel by lazy {
        ViewModelProvider(this)[RoomDescriptionViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.room_fragment_description, container, false)

        // Показываем BottomSheetDialog весь экран
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )

        return view
    }

    private fun getArgsAndInitViews() {

        var roomItem = arguments?.getSerializable(ROOM_ITEM_ARGUMENT) as RoomItem


        Picasso.get().load(roomItem.imageTitle).into(headerImageView)
        costTextView.text = roomItem.cost
        titleTextView.text = roomItem.nameOfRoom
        importantInfoTextView.text = roomItem.ImportantInfo
        descriptionTextView.text = roomItem.description
        typeOfRoomTextView.text = roomItem.typeOfRoom
        countOfRoomTextView.text = roomItem.countOfRooms
        specialOfRoomTextView.text = roomItem.specialOfBooking

        viewModel.getListOfImage(roomItem.id).observe(viewLifecycleOwner) {
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
        recyclerView = view.findViewById<RecyclerView>(R.id.PhotoOfRoomReecyclerView)






        checkBoxAddFavorite.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                snackBarRoomDescription(getString(R.string.added_to_favorite_text))
            } else {
                snackBarRoomDescription(getString(R.string.remove_from_favorite_text))
            }
        }


        getArgsAndInitViews()

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

//    private fun showDatePcikerDialog() {
//
//        val calendar = Calendar.getInstance()
//        val datePicker = DatePickerDialog(
//            context?.applicationContext ?: throw RuntimeException("DatePickerDialog error"),
//            { _, year, moth, day ->
//                editTextDatePciker.setText(String.format("%d-%02d-%02d", year, moth, day))
//            },
//            calendar.get(Calendar.YEAR),
//            calendar.get(Calendar.MONTH),
//            calendar.get(Calendar.DAY_OF_MONTH)
//        )
//        datePicker.show()
//
//    }

    override fun onClick(view: View) {
        when (view.id) {
//            R.id.DatePickerEditText -> {
//                showDatePcikerDialog()
//            }

        }
    }


    companion object {

        fun newInstanceAddRoomItem(item: RoomItem): RoomDescriptionFragment {
            return RoomDescriptionFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ROOM_ITEM_ARGUMENT, item)
                }
            }
        }
    }
}