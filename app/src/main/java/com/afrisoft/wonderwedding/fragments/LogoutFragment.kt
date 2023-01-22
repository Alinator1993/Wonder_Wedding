package com.afrisoft.wonderwedding.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.afrisoft.wonderwedding.databinding.FragmentLogoutBinding


class LogoutFragment: Fragment() {
    private var _binding:  FragmentLogoutBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogoutBinding.inflate(inflater, container, false)
        val userId = activity?.intent?.getStringExtra("user_id")
        val emailId = activity?.intent?.getStringExtra("email_id")
        "User ID :: $userId".also { binding.tvUserId.text = it }
        "Email ID :: $emailId".also { binding.tvEmailId.text = it }
//        val userId = savedInstanceState.binding.getString("user_id",String.Empty)
//        val emailId = savedInstanceState.getString("email_id",String.Empty)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //This call the parent constructor
        //Step 11: Get the user id and email through intent and set them in UI.
        // START
        // END

        // Assign the logout button click event and add the feature to logout fom the application.
        // START
        binding.btnLogout.setOnClickListener {
            // Logout from app.
            FirebaseAuth.getInstance().signOut()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    }