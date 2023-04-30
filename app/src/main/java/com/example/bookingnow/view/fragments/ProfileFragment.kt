package com.example.bookingnow.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.bookingnow.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class ProfileFragment : Fragment() {


    private lateinit var bottomNav: BottomNavigationView
    private lateinit var buttonConfirm: Button

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