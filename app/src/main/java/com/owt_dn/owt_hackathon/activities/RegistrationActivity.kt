package com.owt_dn.owt_hackathon.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.owt_dn.owt_hackathon.R
import com.owt_dn.owt_hackathon.adapters.ProfilePagerAdapter
import com.owt_dn.owt_hackathon.fragments.ProfileInfoFragment
import com.owt_dn.owt_hackathon.fragments.ProfilePasswordFragment
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        initialize()
    }

    private fun initialize() {
        viewPager.adapter = ProfilePagerAdapter(supportFragmentManager, listOf(
                ProfileInfoFragment(),
                ProfilePasswordFragment()
        ))

        btnNext.setOnClickListener {
            viewPager.currentItem = viewPager.currentItem + 1
        }
    }
}