package com.example.capstoneduasatu

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.capstoneduasatu.databinding.ActivityMainBinding
import com.example.capstoneduasatu.fragments.HomeFragment
import com.example.capstoneduasatu.fragments.ProfileFragment
import com.example.capstoneduasatu.fragments.SearchFragment
import com.example.capstoneduasatu.fragments.UploadFragment
import com.example.capstoneduasatu.helper.SettingPreferences
import com.example.capstoneduasatu.viewmodel.MainViewModel
import com.example.capstoneduasatu.viewmodel.ProfileThemeViewModel
import com.example.capstoneduasatu.viewmodel.SettingViewModelFactory
import com.example.capstoneduasatu.viewmodel.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModelFactory: ViewModelFactory
    private var token = ""
    private val mainViewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        //tema
        val pref = SettingPreferences.getInstance(dataStore)

        val themeSettingView = ViewModelProvider(this, SettingViewModelFactory(pref))[ProfileThemeViewModel::class.java]

        themeSettingView.getThemeSettings().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        viewModelFactory=ViewModelFactory.getInstance(this)

        mainViewModel.checkToken().observe(this) {
            token = it.token
            if (!it.isLogin) {
                val intent = Intent(this, OnBoardingActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
        }

        val homeFragment = HomeFragment()
        val searchFragment = SearchFragment()
        val uploadFragment = UploadFragment()
        val profileFragment = ProfileFragment()


        binding.fab.setOnClickListener {
            startActivity(Intent(this, DeteksiActivity::class.java))
        }

        makeCurrentFragment(homeFragment)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.home -> makeCurrentFragment(homeFragment)
                R.id.Search -> makeCurrentFragment(searchFragment)
                R.id.Article -> makeCurrentFragment(uploadFragment)
                R.id.profile -> makeCurrentFragment(profileFragment)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment:Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flwarper,fragment)
            commit()
        }
}