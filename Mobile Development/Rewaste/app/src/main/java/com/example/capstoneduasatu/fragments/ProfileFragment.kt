package com.example.capstoneduasatu.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.capstoneduasatu.OnBoardingActivity
import com.example.capstoneduasatu.ProfileAbout
import com.example.capstoneduasatu.ProfileTheme
import com.example.capstoneduasatu.R
import com.example.capstoneduasatu.databinding.FragmentProfileBinding
import com.example.capstoneduasatu.response.DataProfile
import com.example.capstoneduasatu.viewmodel.ProfileViewModel
import com.example.capstoneduasatu.viewmodel.ViewModelFactory

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private var token = ""
    private lateinit var data: DataProfile
    private val profileViewModel: ProfileViewModel by viewModels { ViewModelFactory.getInstance(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel.checkToken().observe(this) {
            token = it.token
            if (!it.isLogin) {
                val intent = Intent(requireActivity(), OnBoardingActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                requireActivity().finish()
            }
        }

        profileViewModel.getUser().observe(viewLifecycleOwner){
            token = it.token

            profileViewModel.getDataUser(token)
            profileViewModel.dataProfile.observe(viewLifecycleOwner){
                data = it
            }
            setupDataUser()
        }

        binding.buttonLogout.setOnClickListener {
            profileViewModel.logout()
        }
        binding.rTema.setOnClickListener{
            movetotheme()
        }
        binding.rLanguage.setOnClickListener{
            movetosetting()
        }
        binding.rAbout.setOnClickListener {
            movetoabout()
        }

    }
    private fun movetotheme(){
        val intent = Intent(requireContext(), ProfileTheme::class.java)
        startActivity(intent)
    }

    private fun movetosetting(){
        startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
    }

    private fun movetoabout(){
        val intent = Intent(requireContext(), ProfileAbout::class.java)
        startActivity(intent)
    }

    private fun setupDataUser() {
        profileViewModel.dataProfile.observe(viewLifecycleOwner) {
            if (it != null) {
                data = it
                showProfile(data)
            }
        }
    }
    private fun showProfile(data : DataProfile) {
        Glide.with(this)
            .load(data.imageUrl)
            .circleCrop()
            .placeholder(R.drawable.ic_baseline_account_circle_24)
            .error(R.drawable.ic_baseline_account_circle_24)
            .into(binding.imgProfile)

        binding.nameUser.text = data.name
        binding.emailUser.text = data.email
    }
}