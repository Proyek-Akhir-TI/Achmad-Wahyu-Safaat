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
import com.example.udmitrataniapps.petugas.fragment.adapters.JadwalFaseBerbungaFragmentAdapter
import com.example.udmitrataniapps.petugas.fragment.adapters.JadwalFaseVegetatifFragmentAdapter
import com.example.udmitrataniapps.petugas.fragment.jadwalmonitoring.viewmodel.JadwalFaseBerbungaViewModel
import kotlinx.android.synthetic.main.jadwal_fase_berbunga_fragment.*
import kotlinx.android.synthetic.main.jadwal_fase_vegetatif_fragment.*
import kotlinx.android.synthetic.main.jadwal_fase_vegetatif_fragment.dialog_empty
import retrofit2.Call
import retrofit2.Response

class JadwalFaseBerbungaFragment : Fragment() {

    private var layoutManager : RecyclerView.LayoutManager? = null
    private var adapter : RecyclerView.Adapter<RecyclerView.ViewHolder>? = null
    lateinit var sharedPref : PreferencesHelper

    companion object {
        fun newInstance() = JadwalFaseBerbungaFragment()
    }

    private lateinit var viewModel: JadwalFaseBerbungaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.jadwal_fase_berbunga_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(JadwalFaseBerbungaViewModel::class.java)
        sharedPref = PreferencesHelper(requireContext())
    }

    override fun onResume() {
        val dataAdapter = JadwalFaseBerbungaFragmentAdapter()
        getJadwalMonitoring(dataAdapter)
        dataAdapter.notifyDataSetChanged()
        super.onResume()
    }

    private fun getJadwalMonitoring(dataAdapter: JadwalFaseBerbungaFragmentAdapter) {
        ApiConfig.instancRetrofit.getJadwalMonitoringFaseBerbunga(
            token = "Bearer ${sharedPref.fetchAuthToken()}"
        ).enqueue(object : retrofit2.Callback<ResponseJadwalMonitoring>{
            override fun onResponse(
                call: Call<ResponseJadwalMonitoring>,
                response: Response<ResponseJadwalMonitoring>
            ) {
                if (response.body()!!.jadwal_monitoring.isNotEmpty()){
                    rv_jadwal_fase_berbunga.visibility = View.VISIBLE
                    dialog_empty.visibility = View.GONE
                }

                rv_jadwal_fase_berbunga.apply {
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