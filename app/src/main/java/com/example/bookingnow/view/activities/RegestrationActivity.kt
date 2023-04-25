package com.example.bookingnow.view.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.DisplayPhoto
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.bookingnow.R
import com.example.bookingnow.databinding.ActivityRegestrationBinding
import com.example.bookingnow.view.activities.MainActivity.Companion.ADMIN_LOGGED
import com.example.bookingnow.view.activities.MainActivity.Companion.GUEST_LOGGED
import com.example.bookingnow.view.activities.MainActivity.Companion.USER_LOGGED
import java.lang.RuntimeException


class RegestrationActivity : AppCompatActivity(), View.OnClickListener {


    lateinit var binding: ActivityRegestrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegestrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val path = "android.resource://com.example.bookingnow/" + R.raw.clouds

        var u = Uri.parse(path)

        binding.ViewBackgound.apply {
            setVideoURI(u)
            start()
            setOnPreparedListener { mp -> mp!!.isLooping = true }
        }
        binding.ButtonLogin.setOnClickListener(this)

        hideUITOOLSElements(true)

    }


//    override fun onResume() {
//        super.onResume()
//        hideUITOOLSElements(true)
//    }


    override fun onClick(view: View) {

        if (fieldsIsCorrect()) {
            var status = "admin"
            when (status) {
                ADMIN_LOGGED -> startActivity(MainActivity.newIntentAdminLogged(this))
                USER_LOGGED -> startActivity(MainActivity.newIntentUserLogged(this))
                GUEST_LOGGED -> startActivity(MainActivity.newIntentGuestLogged(this))
                else -> throw RuntimeException("Unknown user status from DB")
            }
        }
    }


    //функция проверки полей на допустимые значения
    fun fieldsIsCorrect(): Boolean {
        var name = parseName()
        var phone = ""
        var password = parsePassword()



    }

    //Блок приведения значений к нормальному виду

    fun parseName(InputName: String): String {

    }

    fun parsePassword(InputPassword: String): String {

    }

    fun parseRole(InputRole: String): String{

    }







    fun hideUITOOLSElements(hideOrShow: Boolean) {
        val windowInsetsController =
            WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
        supportFragmentManager.beginTransaction()
    }

}




