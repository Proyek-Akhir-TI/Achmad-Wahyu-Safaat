package com.example.udmitrataniapps.pelanggan.lahanpelanggan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.adapter.LahanPelangganAdapter
import com.example.udmitrataniapps.app.ApiConfig
import com.example.udmitrataniapps.helper.PreferencesHelper
import com.example.udmitrataniapps.model.ResponseArrayModel
import com.example.udmitrataniapps.model.ResponseModel
import com.example.udmitrataniapps.model.pelanggan.DataLahan
import kotlinx.android.synthetic.main.activity_lahan_pelanggan.*
import kotlinx.android.synthetic.main.item_lahan_pelanggan.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LahanPelangganActivity : AppCompatActivity(), LahanPelangganAdapter.Callback {
    private lateinit var sharedPref:PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lahan_pelanggan)

        floating_action_button.setOnClickListener {
            startActivity(Intent(this, FormLahanActivity::class.java).putExtra("statusButton", true))
        }
    }

    override fun onResume() {
        var adapter = LahanPelangganAdapter(this)
        sharedPref = PreferencesHelper(this)
        pb_lahan.visibility = View.VISIBLE
        fetchDataLahan(adapter)
        adapter.notifyDataSetChanged()
        super.onResume()
    }

    fun fetchDataLahan(adapter: LahanPelangganAdapter){
        ApiConfig.instancRetrofit.dataLahan(token = "Bearer ${sharedPref.fetchAuthToken()}").enqueue(object : Callback<ResponseArrayModel>{
            override fun onResponse(
                call: Call<ResponseArrayModel>,
                response: Response<ResponseArrayModel>
            ) {
                if (response.body()!!.success == 1){
                    pb_lahan.visibility = View.GONE
                    rv_dataLahanPelangan.layoutManager = LinearLayoutManager(this@LahanPelangganActivity)
                    rv_dataLahanPelangan.adapter = adapter
                    adapter.setData(response.body()!!.data_lahan)

                }
            }

            override fun onFailure(call: Call<ResponseArrayModel>, t: Throwable) {
                Toast.makeText(this@LahanPelangganActivity, "Error : ${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onClick(data: DataLahan) {
        startActivity(Intent(this, FormLahanActivity::class.java)
            .putExtra("statusButton", false)
            .putExtra("dataLahan", data))
    }

}