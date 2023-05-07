package com.example.bookingnow.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.example.bookingnow.R
import com.example.bookingnow.viewmodel.ListFragmentViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso


class ProfileFragment : Fragment() {


    private lateinit var bottomNav: BottomNavigationView
    private lateinit var buttonConfirm: Button
    private lateinit var imageOfUser:ImageView


    private val viewModel:ListFragmentViewModel by lazy {
        ViewModelProvider(requireActivity())[ListFragmentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomNav = requireActivity().findViewById(R.id.BottomNavigation)
        buttonConfirm = view.findViewById(R.id.ButtonConfirmChanges)
        imageOfUser = view.findViewById(R.id.ImageViewProfileFragment)



        bottomNav.visibility = View.GONE

        buttonConfirm.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bottomNav.visibility = View.VISIBLE
    }
}