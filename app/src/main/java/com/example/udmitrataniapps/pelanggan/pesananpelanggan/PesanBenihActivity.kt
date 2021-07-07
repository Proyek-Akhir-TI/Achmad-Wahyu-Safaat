package com.example.udmitrataniapps.pelanggan.pesananpelanggan

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.app.ApiConfig
import com.example.udmitrataniapps.helper.PreferencesHelper
import com.example.udmitrataniapps.model.ResponseArrayModel
import kotlinx.android.synthetic.main.activity_form_lahan.*
import kotlinx.android.synthetic.main.activity_lahan_pelanggan.*
import kotlinx.android.synthetic.main.activity_pesan_benih.*
import kotlinx.android.synthetic.main.item_lahan_pelanggan.*
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class PesanBenihActivity : AppCompatActivity() {

    lateinit var sharedPref : PreferencesHelper

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesan_benih)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        sharedPref = PreferencesHelper(this)

        initButton()
        fecthLahan()



        btn_pesan_benih.setOnClickListener {
            val text = sp_lahan.selectedItem.toString().split(" ")
            val txtTrim = text[3]
            Log.d("id spinner", txtTrim)
            Log.d("id spinner", btn_tgl_sebar.text.toString())
        }
    }

    private fun fecthLahan() {
        pb_alamat.visibility = View.VISIBLE
        ApiConfig.instancRetrofit.dataLahan(
            token = "Bearer ${sharedPref.fetchAuthToken()}"
        ).enqueue(object : retrofit2.Callback<ResponseArrayModel>{
            override fun onResponse(
                call: Call<ResponseArrayModel>,
                response: Response<ResponseArrayModel>
            ) {
                if (response.isSuccessful){
                    pb_alamat.visibility = View.GONE
                    div_alamat.visibility = View.VISIBLE
                    val respon = response.body()!!
                    val arrayString = ArrayList<String>()
                    for (lahan in respon.data_lahan){
                        arrayString.add("kode Lahan : "+lahan.id+" , "+lahan.nama_lahan)
                    }

                    val adapter = ArrayAdapter<Any>(this@PesanBenihActivity, R.layout.support_simple_spinner_dropdown_item, arrayString.toTypedArray())
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    sp_lahan.adapter = adapter
                }
            }

            override fun onFailure(call: Call<ResponseArrayModel>, t: Throwable) {
                Toast.makeText(this@PesanBenihActivity, "error : ${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun initButton() {
        btn_tgl_sebar.setOnClickListener {
            openCalenderSebar()
        }

        btn_tgl_tanam.setOnClickListener {
            openCalenderTanam()
        }

        val formatDate = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        btn_tgl_sebar.text = formatDate.format(Calendar.getInstance().time)
        btn_tgl_tanam.text = formatDate.format(Calendar.getInstance().time)

    }

    private fun openCalenderTanam() {
        val formatDate = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val getDate : Calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, year, month, day ->

            val selectDate : Calendar = Calendar.getInstance()
            selectDate.set(Calendar.YEAR,year)
            selectDate.set(Calendar.MONTH,month)
            selectDate.set(Calendar.DAY_OF_MONTH,day)
            val date : String = formatDate.format(selectDate.time)
            btn_tgl_tanam.text=date

        }, getDate.get(Calendar.YEAR), getDate.get(Calendar.MONTH), getDate.get(Calendar.DAY_OF_MONTH))
        datePicker.show()
    }

    private fun openCalenderSebar() {
        val formatDate = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val getDate : Calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, year, month, day ->

            val selectDate : Calendar = Calendar.getInstance()
            selectDate.set(Calendar.YEAR,year)
            selectDate.set(Calendar.MONTH,month)
            selectDate.set(Calendar.DAY_OF_MONTH,day)
            val date : String = formatDate.format(selectDate.time)
            btn_tgl_sebar.text=date

        }, getDate.get(Calendar.YEAR), getDate.get(Calendar.MONTH), getDate.get(Calendar.DAY_OF_MONTH))
        datePicker.show()
    }

}