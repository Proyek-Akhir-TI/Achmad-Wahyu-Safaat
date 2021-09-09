package com.example.udmitrataniapps.petugas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.petugas.fragment.adapters.ViewPagerAdapter
import com.example.udmitrataniapps.petugas.fragment.monitoringfragment.MonitoringAwalFragment
import com.example.udmitrataniapps.petugas.fragment.monitoringfragment.MonitoringBerbungaFragment
import com.example.udmitrataniapps.petugas.fragment.monitoringfragment.MonitoringMasakFragment
import com.example.udmitrataniapps.petugas.fragment.monitoringfragment.MonitoringVegetatifFragment
import kotlinx.android.synthetic.main.activity_monitoring.*

class MonitoringFaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monitoring)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        val monitoringList = ArrayList<MonitoringFase>()
//        monitoringList.add(MonitoringFase("Dika Hermawan",
//            "Sawah Grogol, Dusun pelinggihan, Desa Grogol, Kec. Giri, Kabupaten Banyuwangi",
//            "psn/001/93", "Fase Masak" ))
//        monitoringList.add(MonitoringFase("Ridwan Ananta",
//            "Sawah Tegaldlimo, Desa Kalipait, kec. Tegaldlimo, Kabupaten Banyuwangi",
//            "psn/001/123", "Fase Generatif" ))
//        monitoringList.add(MonitoringFase("Achmad Wahyu Safaat",
//            "Sawah Songgon, Dusun Krajan RT.01/RW.01 Desa Songgon, kec. Songgon, Kabupaten Banyuwangi",
//            "psn/001/153", "Fase Vegetatif" ))
//        monitoringList.add(MonitoringFase("Zasiya Fahira",
//            "Sawah Kalipuro, Dusun klatak RT.01/RW.01 Desa Klatak, kec. Kalipuro, Kabupaten Banyuwangi",
//            "psn/001/170", "Fase Vegetatif" ))
//        monitoringList.add(MonitoringFase("Achmad Wahyu Safaat",
//            "Sawah Tamansuruh, Dusun Krajan RT.01/RW.01 Desa Tamansuruh, kec. Glagah, Kabupaten Banyuwangi",
//            "psn/001/211", "Fase Awal" ))


//        val dataAdapter = MonitoringFaseAdapter(this)
//            rv_dataMonitoring.apply {
//                val linearLayoutManager = LinearLayoutManager(context)
//                linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
//                layoutManager = linearLayoutManager
//                adapter = dataAdapter
//            }
//
//        dataAdapter.setMonitoringFasePertumbuhan(monitoringList)
//        dataAdapter.notifyDataSetChanged()

        setUpTabs()
    }

    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(MonitoringAwalFragment(), "Pendahuluan")
        adapter.addFragment(MonitoringVegetatifFragment(), "vegetatif")
        adapter.addFragment(MonitoringBerbungaFragment(), "berbunga")
        adapter.addFragment(MonitoringMasakFragment(), "masak")
        vp_monitoring.adapter = adapter
        tab_layput_monitoring.setupWithViewPager(vp_monitoring)
    }


}