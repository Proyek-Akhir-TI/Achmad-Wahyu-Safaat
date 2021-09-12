package com.example.udmitrataniapps.petugas.fragment.monitoringfragment

import android.app.Activity
import android.content.Intent
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
import com.example.udmitrataniapps.model.pegawai.Pesanan
import com.example.udmitrataniapps.model.pegawai.ResponseJadwalMonitoring
import com.example.udmitrataniapps.petugas.FormMonitoringAwal
import com.example.udmitrataniapps.petugas.FormMonitoringLanjut
import com.example.udmitrataniapps.petugas.fragment.adapters.monitoringadapters.MonitoringVegetatifAdapter
import kotlinx.android.synthetic.main.fragment_monitoring_awal.*
import kotlinx.android.synthetic.main.fragment_monitoring_awal.dialog_empty
import kotlinx.android.synthetic.main.fragment_monitoring_vegetatif.*
import kotlinx.android.synthetic.main.jadwal_fase_berbunga_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MonitoringVegetatifFragment : Fragment(), MonitoringVegetatifAdapter.Callback {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null
    lateinit var sharedPref: PreferencesHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = PreferencesHelper(requireContext())

    }

    override fun onResume() {
        super.onResume()
        val dataAdapter = MonitoringVegetatifAdapter(this)
        getJadwalMonitoring(dataAdapter)
        dataAdapter.notifyDataSetChanged()
    }

    private fun getJadwalMonitoring(dataAdapter: MonitoringVegetatifAdapter) {
        ApiConfig.instancRetrofit.getMonitoringVegetatifToday(
            token = "Bearer ${sharedPref.fetchAuthToken()}"
        ).enqueue(object : Callback<ResponseJadwalMonitoring> {
            override fun onResponse(
                call: Call<ResponseJadwalMonitoring>,
                response: Response<ResponseJadwalMonitoring>
            ) {
                if (response.body()!!.monitoring.isNotEmpty()){
                    rv_monitoring_vegetatif.visibility = View.VISIBLE
                    dialog_empty.visibility = View.GONE
                }

                rv_monitoring_vegetatif.apply {
                    val linearLayoutManager = LinearLayoutManager(context)
                    layoutManager = linearLayoutManager
                    adapter = dataAdapter
                }
                dataAdapter.setData(response.body()!!.monitoring)
            }

            override fun onFailure(call: Call<ResponseJadwalMonitoring>, t: Throwable) {
                Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_monitoring_vegetatif, container, false)
    }



    override fun onClick(data: Pesanan) {
//        Toast.makeText(activity, "Belum Tersedia", Toast.LENGTH_SHORT).show()
        activity.let {
            val intent = Intent(it, FormMonitoringLanjut::class.java).putExtra("pesanan_id", data.id)
            startActivity(intent
                .putExtra("judul", "Formulir Monitoring Fase Vegetatif")
                .putExtra("fase", "Vegetatif")
                .putExtra("pemeriksaan_awal_id", data.pemeriksaan_awal.id))
        }
    }
}