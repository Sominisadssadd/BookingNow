package com.example.bookingnow.view.activities

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.appsearch.SetSchemaRequest.READ_EXTERNAL_STORAGE
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.bookingnow.R
import com.example.bookingnow.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }



    override fun onStart() {
        super.onStart()
        val controller = findNavController(R.id.fragmentContainer)
        NavigationUI.setupWithNavController(binding.BottomNavigation, controller)

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