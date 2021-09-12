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
import com.example.udmitrataniapps.petugas.FormMonitoringLanjut
import com.example.udmitrataniapps.petugas.fragment.adapters.monitoringadapters.MonitoringBerbungaAdapter
import com.example.udmitrataniapps.petugas.fragment.adapters.monitoringadapters.MonitoringVegetatifAdapter
import kotlinx.android.synthetic.main.fragment_monitoring_awal.*
import kotlinx.android.synthetic.main.fragment_monitoring_berbunga.*
import kotlinx.android.synthetic.main.fragment_monitoring_vegetatif.*
import kotlinx.android.synthetic.main.fragment_monitoring_vegetatif.dialog_empty
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MonitoringBerbungaFragment : Fragment(), MonitoringBerbungaAdapter.Callback {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null
    lateinit var sharedPref: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = PreferencesHelper(requireContext())
    }

    override fun onResume() {
        super.onResume()
        val dataAdapter = MonitoringBerbungaAdapter(this)
        getJadwalMonitoring(dataAdapter)
        dataAdapter.notifyDataSetChanged()
    }

    private fun getJadwalMonitoring(dataAdapter: MonitoringBerbungaAdapter) {
        ApiConfig.instancRetrofit.getMonitoringBerbungaToday(
            token = "Bearer ${sharedPref.fetchAuthToken()}"
        ).enqueue(object : Callback<ResponseJadwalMonitoring> {
            override fun onResponse(
                call: Call<ResponseJadwalMonitoring>,
                response: Response<ResponseJadwalMonitoring>
            ) {
                if (response.body()!!.monitoring.isNotEmpty()){
                    rv_monitoring_berbunga.visibility = View.VISIBLE
                    dialog_monitoring_empty.visibility = View.GONE
                }

                rv_monitoring_berbunga.apply {
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
        return inflater.inflate(R.layout.fragment_monitoring_berbunga, container, false)
    }

    companion object {

    }

    override fun onClick(data: Pesanan) {
//        Toast.makeText(activity, "Belum Tersedia", Toast.LENGTH_SHORT).show()
        activity.let {
            val intent = Intent(it, FormMonitoringLanjut::class.java).putExtra("pesanan_id", data.id)
            startActivity(intent
                .putExtra("judul", "Formulir Monitoring Fase Berbunga")
                .putExtra("fase", "Berbunga"))
        }
    }
}