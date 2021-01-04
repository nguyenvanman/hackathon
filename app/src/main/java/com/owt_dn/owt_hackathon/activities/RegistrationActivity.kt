package com.owt_dn.owt_hackathon.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.owt_dn.owt_hackathon.R
import com.owt_dn.owt_hackathon.adapters.ProfilePagerAdapter
import com.owt_dn.owt_hackathon.fragments.ProfileInfoFragment
import com.owt_dn.owt_hackathon.fragments.ProfilePasswordFragment
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {
    private val fragments = listOf(
        ProfileInfoFragment(),
        ProfilePasswordFragment().also {
            it.onBack = {
                viewPager.previousPage()
            }
        }
    )

    private var isOnLastPage = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        initialize()
    }

    private fun initialize() {
        viewPager.adapter = ProfilePagerAdapter(supportFragmentManager, fragments)

        viewPager.onPageChanged { position ->
            isOnLastPage = position == fragments.size - 1
            setButtonState()
        }

        btnNext.setOnClickListener {
            if (isOnLastPage) {
                submit()
            } else {
                viewPager.nextPage()
            }
        }
    }

    private fun setButtonState() {
        btnNext.text = getString(if (isOnLastPage) R.string.button_text_submit else R.string.button_text_next)
        btnNext.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, if (isOnLastPage) R.drawable.ic_submit else R.drawable.ic_next, 0)
    }

    private fun submit() {
    }
}