package com.example.bookingnow.view.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import com.example.bookingnow.R
import com.example.bookingnow.model.Consts
import com.example.bookingnow.model.Consts.EMPTY_STRING_FIELD
import com.example.bookingnow.model.database.UserItem
import com.example.bookingnow.viewmodel.ListFragmentViewModel
import com.example.bookingnow.viewmodel.RegisterActivityViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ProfileFragment : Fragment(), View.OnClickListener {


    private lateinit var bottomNav: BottomNavigationView
    private lateinit var buttonConfirm: Button
    private lateinit var imageOfUser: ImageView
    private lateinit var editTextChangeName: EditText
    private lateinit var editTextChangeEmail: EditText
    private lateinit var editTextChangePassword: EditText
    private lateinit var editTextChangePhone: EditText
    private lateinit var selectNewImageOfUser: ImageView

    var sharedPref: SharedPreferences? = null
    var imageUri: String = EMPTY_STRING_FIELD
    lateinit var currentUser: UserItem

    private val viewModelReg: RegisterActivityViewModel by lazy {
        ViewModelProvider(this)[RegisterActivityViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        sharedPref = activity?.getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iniViews(view)

        bottomNav.visibility = View.GONE

        buttonConfirm.setOnClickListener {
            activity?.onBackPressed()
        }
        setStartValue()

        viewModelReg.listOfUsers.observe(viewLifecycleOwner) {
            it.forEach { user ->
                if (user.id == sharedPref!!.getInt("user_id", 0)) {
                    Picasso.get().load(user.photo).into(imageOfUser)
                }
            }
        }

        selectNewImageOfUser.setOnClickListener(this)
        buttonConfirm.setOnClickListener(this)


    }

    private fun updateUserInformation() {
        GlobalScope.launch(Dispatchers.IO) {
            viewModelReg.updateUserInfo(
                UserItem(
                    sharedPref!!.getInt("user_id", 0),
                    editTextChangeName.text.toString(),
                    editTextChangePassword.text.toString(),
                    imageUri,
                    true,
                    "user",
                    editTextChangePhone.text.toString(),
                    editTextChangeEmail.text.toString()
                )
            )
        }
        requireActivity().onBackPressed()
    }


    private fun setStartValue() {
        GlobalScope.launch(Dispatchers.IO) {
            currentUser = viewModelReg.getUserInfo(sharedPref!!.getInt("user_id", 0))
            editTextChangeName.setText(currentUser.name)
            editTextChangeEmail.setText(currentUser.email)
            editTextChangePassword.setText(currentUser.password)
            editTextChangePhone.setText(currentUser.phone)
            imageUri = currentUser.photo
        }
    }


    private fun iniViews(view: View) {
        bottomNav = requireActivity().findViewById(R.id.BottomNavigation)
        buttonConfirm = view.findViewById(R.id.ButtonConfirmChanges)
        imageOfUser = view.findViewById(R.id.ImageViewProfileFragment)
        editTextChangeName = view.findViewById(R.id.editeTextChangeName)
        editTextChangeEmail = view.findViewById(R.id.editeTextChangeEmail)
        editTextChangePassword = view.findViewById(R.id.editeTextChangePassword)
        editTextChangePhone = view.findViewById(R.id.editeTextChangePhone)
        selectNewImageOfUser = view.findViewById(R.id.SelectImageButton)


    }


    override fun onDestroy() {
        super.onDestroy()
        bottomNav.visibility = View.VISIBLE
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Consts.REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK && data != null) {
            imageUri = data.data.toString()
        }
    }


    override fun onClick(view: View) {
        when (view.id) {
            R.id.SelectImageButton -> {
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, Consts.REQUEST_CODE)
            }
            R.id.ButtonConfirmChanges ->{
              updateUserInformation()
            }
        }
    }
}