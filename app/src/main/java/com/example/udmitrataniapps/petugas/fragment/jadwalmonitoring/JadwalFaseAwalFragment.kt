package com.example.udmitrataniapps.petugas.fragment.jadwalmonitoring

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.app.ApiConfig
import com.example.udmitrataniapps.helper.PreferencesHelper
import com.example.udmitrataniapps.model.pegawai.ResponseJadwalMonitoring
import com.example.udmitrataniapps.petugas.fragment.adapters.jadwaladapters.JadwalFaseAwalFragmentAdapter
import com.example.udmitrataniapps.petugas.fragment.jadwalmonitoring.viewmodel.JadwalFaseAwalViewModel
import kotlinx.android.synthetic.main.jadwal_fase_awal_fragment.*
import retrofit2.Call
import retrofit2.Response

class JadwalFaseAwalFragment : Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null
    lateinit var sharedPref: PreferencesHelper

    companion object {
        fun newInstance() = JadwalFaseAwalFragment()
    }

    private lateinit var viewModel: JadwalFaseAwalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.jadwal_fase_awal_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(JadwalFaseAwalViewModel::class.java)
        sharedPref = PreferencesHelper(requireContext())
    }

    override fun onResume() {
        val dataAdapter = JadwalFaseAwalFragmentAdapter()
        getJadwalMonitoring(dataAdapter)
        dataAdapter.notifyDataSetChanged()
        super.onResume()
    }

    private fun getJadwalMonitoring(dataAdapter: JadwalFaseAwalFragmentAdapter) {
        ApiConfig.instancRetrofit.getJadwalMonitoringFaseAwal(
            token = "Bearer ${sharedPref.fetchAuthToken()}"
        ).enqueue(object : retrofit2.Callback<ResponseJadwalMonitoring> {
            override fun onResponse(
                call: Call<ResponseJadwalMonitoring>,
                response: Response<ResponseJadwalMonitoring>
            ) {
                rv_jadwal_fase_awal.apply {
                    val linearLayoutManager = LinearLayoutManager(context)
                    layoutManager = linearLayoutManager
                    adapter = dataAdapter
                }
                dataAdapter.setData(response.body()!!.jadwal_monitoring)
            }

            override fun onFailure(call: Call<ResponseJadwalMonitoring>, t: Throwable) {
                Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()
            }
        }


        )
    }


}