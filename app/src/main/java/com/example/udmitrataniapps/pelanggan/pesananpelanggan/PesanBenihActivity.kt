package com.example.udmitrataniapps.pelanggan.pesananpelanggan

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.Intent
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
import com.example.udmitrataniapps.model.ResponseModel
import com.inyongtisto.myhelper.base.BaseActivity
import com.inyongtisto.myhelper.extension.showErrorDialog
import com.inyongtisto.myhelper.extension.showSuccessDialog
import kotlinx.android.synthetic.main.activity_form_lahan.*
import kotlinx.android.synthetic.main.activity_lahan_pelanggan.*
import kotlinx.android.synthetic.main.activity_pesan_benih.*
import kotlinx.android.synthetic.main.item_lahan_pelanggan.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class PesanBenihActivity : BaseActivity() {

    lateinit var sharedPref : PreferencesHelper

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesan_benih)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        sharedPref = PreferencesHelper(this)

        initButton()
        fetchLahan()
        fetchVarietas()



        btn_pesan_benih.setOnClickListener {

            Log.d("id spinner", btn_tgl_sebar.text.toString())
            pesanBenih()
            progress.show()
        }
    }

    private fun pesanBenih() {
        val textLahan = sp_lahan.selectedItem.toString().split(" ")
        var idLahan = ""
        if (textLahan[3] == "Terlebih"){
            idLahan = "0"
        } else {
            idLahan = textLahan[3]
        }
        val textVarietas = sp_benih.selectedItem.toString().split( " ")
        val idVarietas = textVarietas[0]

        ApiConfig.instancRetrofit.pesanBenih(
            token = "Bearer ${sharedPref.fetchAuthToken()}",
            lahan_pelanggan_id = idLahan.toInt(),
            btn_tgl_sebar.text.toString(),
            btn_tgl_tanam.text.toString(),
            idVarietas
        ).enqueue(object : Callback<ResponseModel>{
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                val resp = response.body()!!
                progress.dismiss()
                if (resp.success == 1){
                    startActivity(Intent(this@PesanBenihActivity, InvoicePesananActivity::class.java)
                        .putExtra("id_pesanan", response.body()!!.pesanan.id)
                        .putExtra("nama_varietas", textVarietas[1]))
                    finish()
                } else if (resp.success == 0){
                    showErrorDialog(resp.message)
//                    Toast.makeText(this@PesanBenihActivity, resp.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                progress.dismiss()
                Toast.makeText(this@PesanBenihActivity, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun fetchVarietas() {
        ApiConfig.instancRetrofit.fetchVariatasPadi(
            token = "Bearer ${sharedPref.fetchAuthToken()}"
        ).enqueue(object : Callback<ResponseArrayModel>{
            override fun onResponse(
                call: Call<ResponseArrayModel>,
                response: Response<ResponseArrayModel>
            ) {
                if (response.isSuccessful){
                    val respon = response.body()!!
                    val arrayVarietas = ArrayList<String>()
                    for (varietas in respon.varietas_padi){
                        arrayVarietas.add("${varietas.id} ${varietas.nama_varietas}")
                    }

                    val adapter = ArrayAdapter<Any>(this@PesanBenihActivity, R.layout.support_simple_spinner_dropdown_item, arrayVarietas.toTypedArray())
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    sp_benih.adapter = adapter
                }
            }

            override fun onFailure(call: Call<ResponseArrayModel>, t: Throwable) {
                Toast.makeText(this@PesanBenihActivity, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun fetchLahan() {
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
                    if (respon.data_lahan.isNotEmpty()){
                        for (lahan in respon.data_lahan){
                            arrayString.add("kode Lahan : "+lahan.id+" , "+lahan.nama_lahan)
                        }
                    }else {
                        arrayString.add("Lengkapi Data Lahan Terlebih Dahulu")
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