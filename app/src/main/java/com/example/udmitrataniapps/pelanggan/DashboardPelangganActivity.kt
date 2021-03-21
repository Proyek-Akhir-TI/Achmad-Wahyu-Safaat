package com.example.udmitrataniapps.pelanggan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.udmitrataniapps.R
import kotlinx.android.synthetic.main.activity_dashboard_pelanggan.*

class DashboardPelangganActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_pelanggan)

        menu_informasi_benih.setOnClickListener(){
            var intent = Intent(this, InformasiBenihActivity::class.java)
            startActivity(intent)
        }

        menu_pesan_benih.setOnClickListener(){
            var intent = Intent(this, PesanBenihActivity::class.java)
            startActivity(intent)
        }

        menu_data_lahan.setOnClickListener(){
            var intent = Intent(this, LahanPelangganActivity::class.java)
            startActivity(intent)
        }
    }
}

