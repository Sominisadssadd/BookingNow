package com.example.bookingnow.view.fragments

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bookingnow.R
import com.example.bookingnow.databinding.ActivityMainBinding
import com.example.bookingnow.databinding.FragmentListBinding
import com.example.bookingnow.view.activities.MainActivity
import com.example.bookingnow.view.activities.MainActivity.Companion.ADMIN_LOGGED
import com.example.bookingnow.view.activities.MainActivity.Companion.GUEST_LOGGED
import com.example.bookingnow.view.activities.MainActivity.Companion.USER_LOGGED
import com.example.bookingnow.view.activities.MainActivity.Companion.USER_STATUS
import com.google.android.material.navigation.NavigationBarMenu
import com.google.android.material.navigation.NavigationBarView


class ListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(USER_STATUS)


    }

    companion object {
        fun newFragmentAdmin(): ListFragment {
            return ListFragment().apply {
                arguments =
                    Bundle().apply {
                        putString(USER_STATUS, ADMIN_LOGGED)
                    }
            }
        }

        fun newFragmentUser(): ListFragment {
            return ListFragment().apply {
                arguments =
                    Bundle().apply {
                        putString(USER_STATUS, USER_LOGGED)
                    }
            }
        }

        fun newFragmentGuest(): ListFragment {
            return ListFragment().apply {
                arguments =
                    Bundle().apply {
                        putString(USER_STATUS, GUEST_LOGGED)
                    }
            }
        }
    }


}