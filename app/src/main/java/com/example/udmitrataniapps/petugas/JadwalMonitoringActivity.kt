package com.example.udmitrataniapps.petugas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.adapter.JadwalMonitoringAdapter
import com.example.udmitrataniapps.model.JadwalMonitoring
import com.example.udmitrataniapps.petugas.fragment.jadwalmonitoring.JadwalFaseAwalFragment
import com.example.udmitrataniapps.petugas.fragment.jadwalmonitoring.JadwalFaseBerbungaFragment
import com.example.udmitrataniapps.petugas.fragment.jadwalmonitoring.JadwalFaseMasakFragment
import com.example.udmitrataniapps.petugas.fragment.jadwalmonitoring.JadwalFaseVegetatifFragment
import com.example.udmitrataniapps.petugas.fragment.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_jadwal.*

class JadwalMonitoringActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jadwal)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var jadwalList = ArrayList<JadwalMonitoring>()
        jadwalList.add(JadwalMonitoring("Achmad Wahyu Safaat", "Songgon, Banyuwangi", "fase awal", "20-03-2021"))
        jadwalList.add(JadwalMonitoring("Zasiya Fahira", "Glagah, Banyuwangi", "fase vegetatif", "20-03-2021"))
        jadwalList.add(JadwalMonitoring("Dika Hermawan", "Giri, Banyuwangi", "fase vegetatif", "20-03-2021"))
        jadwalList.add(JadwalMonitoring("Ridwan Ananta", "Tegaldlimo, Banyuwangi", "fase vegetatif", "20-03-2021"))

        setUpTabs()

        var dataAdapter= JadwalMonitoringAdapter()
//        rv_jadwal_monitoring.apply {
//            var linearLayoutManager = LinearLayoutManager(context)
//            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
//            layoutManager = linearLayoutManager
//            adapter = dataAdapter
//        }

        dataAdapter.setJadwalMonitoringFase(jadwalList)
        dataAdapter.notifyDataSetChanged()

    }

    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(JadwalFaseAwalFragment(), "Pendahuluan")
        adapter.addFragment(JadwalFaseVegetatifFragment(), "vegetatif")
        adapter.addFragment(JadwalFaseBerbungaFragment(), "berbunga")
        adapter.addFragment(JadwalFaseMasakFragment(), "masak")
        vp_fase_monitoring.adapter = adapter
        tab_layout_fase.setupWithViewPager(vp_fase_monitoring)
    }
}