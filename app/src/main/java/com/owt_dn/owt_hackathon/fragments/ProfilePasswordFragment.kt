package com.owt_dn.owt_hackathon.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.owt_dn.owt_hackathon.R
import kotlinx.android.synthetic.main.fragment_profile_password.*

class ProfilePasswordFragment : Fragment() {
    var onBack: (() -> Unit)? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
    }

    private fun initialize() {
        btnBack.setOnClickListener {
            onBack?.invoke()
        }
    }
}