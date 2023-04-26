package com.example.bookingnow.view.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.bookingnow.R
import com.example.bookingnow.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideUITOOLSElements()

    }

    override fun onResume() {
        super.onResume()
        hideUITOOLSElements()
    }

    override fun onStart() {
        super.onStart()

        val controller = findNavController(R.id.fragmentContainer)
        NavigationUI.setupWithNavController(binding.BottomNavigation, controller)
    }

    fun hideUITOOLSElements() {
        val windowInsetsController =
            WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
        supportFragmentManager.beginTransaction()
    }

    companion object {
        const val USER_STATUS = "status"
        const val ADMIN_LOGGED = "admin"
        const val USER_LOGGED = "user"
        const val GUEST_LOGGED = "guest"
        const val UNKNOWN_USER = ""

        fun newIntentAdminLogged(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(USER_STATUS, ADMIN_LOGGED)
            return intent
        }

        fun newIntentUserLogged(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(USER_STATUS, USER_LOGGED)
            return intent
        }

        fun newIntentGuestLogged(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(USER_STATUS, GUEST_LOGGED)
            return intent
        }
    }
}