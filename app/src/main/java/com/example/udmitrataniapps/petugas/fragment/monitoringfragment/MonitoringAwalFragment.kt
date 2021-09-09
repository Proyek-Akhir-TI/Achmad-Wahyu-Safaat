package com.example.udmitrataniapps.petugas.fragment.monitoringfragment

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
import com.example.udmitrataniapps.pelanggan.varietaspadi.DetailVarietasBenih
import com.example.udmitrataniapps.petugas.FormMonitoringAwal
import com.example.udmitrataniapps.petugas.fragment.adapters.monitoringadapters.MonitoringAwalAdapter
import kotlinx.android.synthetic.main.fragment_monitoring_awal.*
import kotlinx.android.synthetic.main.jadwal_fase_berbunga_fragment.*
import kotlinx.android.synthetic.main.jadwal_fase_berbunga_fragment.dialog_empty
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MonitoringAwalFragment : Fragment(), MonitoringAwalAdapter.Callback {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null
    lateinit var sharedPref: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = PreferencesHelper(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_monitoring_awal, container, false)
    }

    override fun onResume() {
        val dataAdapter = MonitoringAwalAdapter(callback = this)
        getJadwalMonitoring(dataAdapter)
        dataAdapter.notifyDataSetChanged()
        super.onResume()
    }

    private fun getJadwalMonitoring(dataAdapter: MonitoringAwalAdapter) {
        ApiConfig.instancRetrofit.getMonitoringAwalToday(
            token = "Bearer ${sharedPref.fetchAuthToken()}"
        ).enqueue(object : Callback<ResponseJadwalMonitoring>{
            override fun onResponse(
                call: Call<ResponseJadwalMonitoring>,
                response: Response<ResponseJadwalMonitoring>
            ) {
                if (response.body()!!.monitoring.isNotEmpty()){
                    rv_monitoring_awal.visibility = View.VISIBLE
                    dialog_empty.visibility = View.GONE
                }

                rv_monitoring_awal.apply {
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

    override fun onClick(data: Pesanan) {
        activity.let {
            val intent = Intent(it, FormMonitoringAwal::class.java)
            startActivity(intent)
        }
    }


}