package com.example.islamiapplication.ui.fragments

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.islamiapplication.PlayRadioService
import com.example.islamiapplication.adapters.OnItemClickListener
import com.example.islamiapplication.adapters.RadiosAdapter
import com.example.islamiapplication.api.ApiManager
import com.example.islamiapplication.databinding.FragmentRadioBinding
import com.example.islamiapplication.model.RadioResponse
import com.example.islamiapplication.model.RadiosItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RadioFragment : Fragment() {
    lateinit var binding: FragmentRadioBinding
    private val adapter = RadiosAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRadioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        getRadiosFromApi()
        adapter.onPlayPauseClickListener = OnItemClickListener { position, radio ->

            radioService?.let {

                adapter.changeRadio(position, !it.getIsPlaying())
                if (!it.getIsPlaying()) {
                    startServiceMediaPlayer(radio)
                }
            }
        }
    }

    private fun startServiceMediaPlayer(radio: RadiosItem) {

        if (radio.id != null && radio.name != null && radio.url != null) {
            radioService?.startMediaPlayer(radio.url, radio.name, radio.id)
        } else {
            pauseServiceMediaPlayer()
        }
    }

    private fun pauseServiceMediaPlayer() {
        radioService?.PlayPauseMediaPlayer()
    }

    private fun getRadiosFromApi() {
        ApiManager.radioService.getRadios().enqueue(object : Callback<RadioResponse> {
            override fun onResponse(call: Call<RadioResponse>, response: Response<RadioResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(requireContext(), "successful call", Toast.LENGTH_SHORT).show()
                    val list = response.body()?.radios
                    if (list.isNullOrEmpty()) {
                        Toast.makeText(requireContext(), "this list is empty", Toast.LENGTH_SHORT)
                            .show()
                    } else adapter.changeRadiosList(list)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "this cal is not successful",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }

            override fun onFailure(call: Call<RadioResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "failure happened", Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun initRecyclerView() {
        binding.rvRadio.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        startService()
        bindService()
    }

    var radioService: PlayRadioService? = null
    var bound: Boolean = false
    private fun bindService() {
        val intent = Intent(requireContext(), PlayRadioService::class.java)
        requireActivity().bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as PlayRadioService.MyBinder
            radioService = binder.getService()
            bound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            bound = false
        }

    }

    private fun startService() {
        val intent = Intent(requireContext(), PlayRadioService::class.java)
        requireActivity().startService(intent)
    }

    override fun onStop() {
        super.onStop()
        radioService?.unbindService(connection)
    }
}
