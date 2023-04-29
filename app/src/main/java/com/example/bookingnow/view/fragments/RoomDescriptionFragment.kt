package com.example.bookingnow.view.fragments

import android.app.DatePickerDialog
import android.app.Dialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingnow.R
import com.example.bookingnow.model.database.RoomPhotoItem
import com.example.bookingnow.view.fragments.adapters.roomdescriptionfragment.RoomDescriptionFragmentAdapter
import com.example.bookingnow.viewmodel.RoomDescriptionViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import java.lang.RuntimeException


class RoomDescriptionFragment : BottomSheetDialogFragment(), View.OnClickListener {

    private lateinit var checkBoxAddFavorite: CheckBox
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var editTextDatePciker: EditText
    private lateinit var recAdapter: RoomDescriptionFragmentAdapter
    private lateinit var buttonBooking: Button

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
        editTextDatePciker = view.findViewById(R.id.DatePickerEditText)
        buttonBooking = view.findViewById(R.id.ButtonBooking)

        checkBoxAddFavorite.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                snackBarRoomDescription(getString(R.string.added_to_favorite_text))
            } else {
                snackBarRoomDescription(getString(R.string.remove_from_favorite_text))
            }
        }

        editTextDatePciker.setOnClickListener(this)

        recAdapter = RoomDescriptionFragmentAdapter()

        viewModel.RoomImageList.observe(viewLifecycleOwner) {
            //если ошибка со считыванием данных, то значит в таблице фоток тестовые данные
            recAdapter.list = it
            initRecyclerView(view)
        }

        buttonBooking.setOnClickListener {

            val item = RoomPhotoItem(0, 2, "affas")
            viewModel.addPhoto(item)
        }
    }


    fun initRecyclerView(view: View) {

        val recyclerView = view.findViewById<RecyclerView>(R.id.PhotoOfRoomReecyclerView)
        recyclerView.adapter = recAdapter

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

    private fun showDatePcikerDialog() {

        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog(
            context?.applicationContext ?: throw RuntimeException("DatePickerDialog error"),
            { _, year, moth, day ->
                editTextDatePciker.setText(String.format("%d-%02d-%02d", year, moth, day))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()

    }


    override fun onClick(view: View) {
        when (view.id) {
            R.id.DatePickerEditText -> {
                showDatePcikerDialog()
            }
        }
    }
}