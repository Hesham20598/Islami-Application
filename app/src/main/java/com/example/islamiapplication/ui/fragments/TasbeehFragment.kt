package com.example.islamiapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.islamiapplication.databinding.FragmentTasbeehBinding

class TasbeehFragment : Fragment() {

    private var currentRotation = 0f
    private val rotationIncrement = 12f
    private val tasbeehat = listOf("سبحان الله", "الحمد لله", "الله اكبر")
    private var currentIndex = 0
    private var counter = 0


    lateinit var binding: FragmentTasbeehBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTasbeehBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (counter <= 30) {
            binding.btnTasbeeh.setOnClickListener {
                counter++
                rotateMisbaha()
                binding.tvTasbeehCounter.text = "$counter"
                if (counter == 30) {
                    currentIndex++
                    changeTasbeeh()

                }
            }
        }

    }

    private fun changeTasbeeh() {
        if (currentIndex <= 2) {
            counter = 0
            currentRotation = 0f
            changeButtonName(currentIndex)
        } else {
            counter = 0
            currentIndex = 0
            changeButtonName(currentIndex)
        }
    }


    private fun rotateMisbaha() {
        currentRotation += rotationIncrement
        binding.ivBodeyOfSebha.rotation = currentRotation
    }

    private fun changeButtonName(index: Int) {
        binding.btnTasbeeh.text = tasbeehat[index]
    }
}



