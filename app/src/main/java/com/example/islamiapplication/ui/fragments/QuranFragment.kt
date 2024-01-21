package com.example.islamiapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.islamiapplication.adapters.SuraNameAdapter
import com.example.islamiapplication.databinding.FragmentQuranBinding
import com.example.islamiapplication.model.SuraNameIndex
import com.example.islamiapplication.model.suraNameList

class QuranFragment : Fragment() {
    lateinit var binding: FragmentQuranBinding
    lateinit var adapter: SuraNameAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuranBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val suraIndexList = suraNameList.mapIndexed { index, suraName ->
            SuraNameIndex(suraName, index + 1)
        }
        adapter = SuraNameAdapter(suraIndexList)
        binding.rvQuranFragment.adapter = adapter

    }
}