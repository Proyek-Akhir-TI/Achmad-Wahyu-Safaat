package com.example.udmitrataniapps.pelanggan.pesananpelanggan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.udmitrataniapps.R
import kotlinx.android.synthetic.main.activity_pesanan_pelanggan.*

class PesananPelangganActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesanan_pelanggan)

        div_pesanan1.setOnClickListener {
            startActivity(Intent(this, DetailPesananActivity::class.java))
        }
    }
}