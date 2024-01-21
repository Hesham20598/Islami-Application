package com.example.islamiapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.islamiapplication.databinding.FragmentQuranBinding
import com.example.islamiapplication.databinding.FragmentRadioBinding

class RadioFragment : Fragment() {
    lateinit var bindig: FragmentRadioBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindig = FragmentRadioBinding.inflate(inflater,container,false)
        return bindig.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}