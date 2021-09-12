package com.example.udmitrataniapps.petugas

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.app.ApiConfig
import com.example.udmitrataniapps.helper.PreferencesHelper
import com.example.udmitrataniapps.model.ResponseModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_form_monitoring_awal.*
import kotlinx.android.synthetic.main.activity_invoice_pesanan.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class FormMonitoringAwal : AppCompatActivity() {
    lateinit var fusedLocationProviderClient : FusedLocationProviderClient
    lateinit var address: Address
    lateinit var sharedPref : PreferencesHelper
    private var pesanan_id : Int? = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_monitoring_awal)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        sharedPref = PreferencesHelper(this)
        getLocation()
        getDetailPesanan()


        btn_lokasi.setOnClickListener {
            getLocation()
        }

        btn_submit_awal.setOnClickListener{
            sendMonitoringAwal()
        }

    }

    private fun sendMonitoringAwal() {
        ApiConfig.instancRetrofit.sendMonitoringAwal(
            token = "Bearer ${sharedPref.fetchAuthToken()}",
            pesanan_id!!,
            getCheckBoxValue(cb_letak_areal.isChecked),
            getCheckBoxValue(cb_luas_areal.isChecked),
            getCheckBoxValue(cb_isolasi.isChecked),
            getCheckBoxValue(cb_sejarah.isChecked),
            getCheckBoxValue(cb_asal_luas.isChecked),
            edt_catatan_monitoring.text.toString(),
            edt_kesimpulan_monitoring.text.toString()
            ).enqueue(object : Callback<ResponseModel>{
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                if (response.isSuccessful){
                    Toast.makeText(this@FormMonitoringAwal, response.body()!!.message, Toast.LENGTH_SHORT).show()
                    finish()
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Toast.makeText(this@FormMonitoringAwal, "error : ${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun getCheckBoxValue(checked: Boolean): String {
        val value : String
        value = if (checked){
            "benar"
        } else "salah"

        return value
    }


    override fun onResume() {
        super.onResume()

    }

    private fun getLocation() {
        val task:Task<Location> = fusedLocationProviderClient.lastLocation
        if (
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 101)
            return
        }
        task.addOnSuccessListener {
            if (it != null) {
                var geocoder = Geocoder(this)
                val addresses : List<Address>
                geocoder = Geocoder(applicationContext, Locale.getDefault())
                addresses = geocoder.getFromLocation(it.latitude, it.longitude, 1)
                val address : String = addresses[0].getAddressLine(0)
                tv_lokasi_sekarang.text = address
                Toast.makeText(applicationContext, "Lokasi berhasil diperbarui ", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getDetailPesanan() {
        ApiConfig.instancRetrofit.getDetailLahan(
            token = "Bearer ${sharedPref.fetchAuthToken()}",
            intent.getIntExtra("pesanan_id", 0)
        ).enqueue(object : Callback<ResponseModel> {
            @SuppressLint("ResourceAsColor", "SetTextI18n")
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                if (response.isSuccessful){
                    val respon = response.body()!!
                    tv_nama_pemilik.text = respon.pesanan.lahan_pelanggan.pelanggan.nama_lengkap
                    tv_nomor_induk.text = respon.pesanan.nomor_induk
                    tv_lokasi.text = "Alamat Lahan : " + respon.pesanan.lahan_pelanggan.alamat
                    tv_luas_lahan.text = "Luas Lahan : ${respon.pesanan.lahan_pelanggan.luas_lahan} hektar"
                    tv_rencana_benih.text = "Benih Rencana : ${respon.pesanan.stok_padi.varietas_padi.nama_varietas}"
                    tv_tgl_sebar_monitoring.text = "Rencana Tanggal Sebar Benih : "+ newDate(respon.pesanan.tgl_sebar)
                    tv_tgl_tanam_monitoring.text = "Rencana Tanggal Sebar Benih : "+ newDate(respon.pesanan.tgl_tanam)
                    pesanan_id = respon.pesanan.id

                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Toast.makeText(this@FormMonitoringAwal, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    @SuppressLint("SimpleDateFormat")
    private fun newDate(data : String): Any? {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val convert = dateFormat.parse(data)!!
        dateFormat.applyPattern("dd/MM/yyyy")
        val newDateFormat = dateFormat.format(convert)

        return newDateFormat
    }


}


