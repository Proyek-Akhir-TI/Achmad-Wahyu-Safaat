package com.example.udmitrataniapps.pelanggan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.adapter.VarietasBenihAdapter
import com.example.udmitrataniapps.app.ApiConfig
import com.example.udmitrataniapps.helper.PreferencesHelper
import com.example.udmitrataniapps.model.DataBenih
import com.example.udmitrataniapps.model.ResponseArrayModel
import kotlinx.android.synthetic.main.activity_informasi_benih.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InformasiBenihActivity : AppCompatActivity() {

    lateinit var sharedPref : PreferencesHelper
    lateinit var dataAdapter : VarietasBenihAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informasi_benih)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        sharedPref = PreferencesHelper(this)
        var dataBenihList = ArrayList<DataBenih>()



    }

    override fun onResume() {
        val dataAdapter = VarietasBenihAdapter()
        fetchVarietasBenih(dataAdapter)
        dataAdapter.notifyDataSetChanged()
        super.onResume()
    }

    private fun fetchVarietasBenih(dataAdapter: VarietasBenihAdapter) {
        ApiConfig.instancRetrofit.fetchVariatasPadi(token = "Bearer ${sharedPref.fetchAuthToken()}")
            .enqueue(object : Callback<ResponseArrayModel>{
                override fun onResponse(
                    call: Call<ResponseArrayModel>,
                    response: Response<ResponseArrayModel>
                ) {
                    if (response.isSuccessful){
                        rv_dataBenih.apply {
                            val gridLayoutManager = GridLayoutManager(context, 3)
                            layoutManager = gridLayoutManager
                            adapter = dataAdapter
                        }
                        dataAdapter.setDataBenih(response.body()!!.varietas_padi)
                    }
                }

                override fun onFailure(call: Call<ResponseArrayModel>, t: Throwable) {

                }

            })
    }
}

