package com.example.udmitrataniapps.petugas

import android.annotation.SuppressLint
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.app.ApiConfig
import com.example.udmitrataniapps.helper.PreferencesHelper
import com.example.udmitrataniapps.model.ResponseModel
import com.github.drjacky.imagepicker.ImagePicker
import com.inyongtisto.myhelper.extension.toMultipartBody
import kotlinx.android.synthetic.main.activity_form_monitoring_awal.*
import kotlinx.android.synthetic.main.activity_form_monitoring_lanjut.*
import kotlinx.android.synthetic.main.activity_form_monitoring_lanjut.isolasi_barat
import kotlinx.android.synthetic.main.activity_form_monitoring_lanjut.isolasi_selatan
import kotlinx.android.synthetic.main.activity_form_monitoring_lanjut.isolasi_timur
import kotlinx.android.synthetic.main.activity_form_monitoring_lanjut.isolasi_utara
import kotlinx.android.synthetic.main.item_riwayat_monitoring.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class FormMonitoringLanjut : AppCompatActivity() {
    lateinit var sharedPref: PreferencesHelper
    private var fileUri: File? = null
    private var pesananId= 0
    private var pemeriksaanAwalID = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_monitoring_lanjut)
        sharedPref = PreferencesHelper(this)

        getDetailPesanan()
        tv_ttl_form.text = intent.getStringExtra("judul")

//        Log.d("pesanan_id", intent.putExtra("pesanan_id", 0).toString())
//        Log.d("pesanan_id", intent.putExtra("pemeriksaan_awal_id", 0).toString())

        iv_fase_lanjut.setOnClickListener {
            ImagePicker.with(this)
                //...
                .cropSquare().maxResultSize(512, 512)
                .createIntentFromDialog { launcher.launch(it) }

        }

        btn_monitoring_lanjut.setOnClickListener {
            if (fileUri == null) {
                Toast.makeText(this, "Masukkan Foto Terlebih Dahulu", Toast.LENGTH_SHORT).show()
            } else {
                sendMonitoringLanjut(fileUri!!)
//                Log.d("tag :", waktu.hour.toString())
            }
        }
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val uri = it.data?.data!!
                // Use the uri to load the image
                iv_fase_lanjut.setImageURI(uri)
                fileUri = File(uri.path!!)
            }
        }

    private fun sendMonitoringLanjut(file: File) {
        val fileImage = file.toMultipartBody()
        val waktu = "${pick_waktu.hour}:${pick_waktu.minute}"
        ApiConfig.instancRetrofit.sendMonitoringLanjut(
            token = "Bearer ${sharedPref.fetchAuthToken()}",
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),pesananId.toString()
            ),
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                pemeriksaanAwalID.toString()
            ),
            RequestBody.create("text/plain".toMediaTypeOrNull(), intent.getStringExtra("fase")!!),
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                getCheckBoxIsolasi(isolasi_timur.isChecked)
            ),
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                getCheckBoxIsolasi(isolasi_barat.isChecked)
            ),
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                getCheckBoxIsolasi(isolasi_selatan.isChecked)
            ),
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                getCheckBoxIsolasi(isolasi_utara.isChecked)
            ),
            RequestBody.create("text/plain".toMediaTypeOrNull(), edt_barier.text.toString()),
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                getCheckBoxPenanaman(sifat_penanaman.isChecked)
            ),
            RequestBody.create("text/plain".toMediaTypeOrNull(), getCheckBoxHama(hama.isChecked)),
            RequestBody.create("text/plain".toMediaTypeOrNull(), edt_perkiraan_produksi.text.toString()),
            RequestBody.create("text/plain".toMediaTypeOrNull(), edt_contoh_pemeriksaan.text.toString()),
            RequestBody.create("text/plain".toMediaTypeOrNull(), waktu),
            RequestBody.create("text/plain".toMediaTypeOrNull(), edt_tgl_panen.text.toString()),
            RequestBody.create("text/plain".toMediaTypeOrNull(), edt_cvl1.text.toString()),
            RequestBody.create("text/plain".toMediaTypeOrNull(), edt_cvl2.text.toString()),
            RequestBody.create("text/plain".toMediaTypeOrNull(), edt_cvl3.text.toString()),
            RequestBody.create("text/plain".toMediaTypeOrNull(), edt_cvl4.text.toString()),
            fileImage!!
        ).enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                val respon = response.body()!!.success
                if (respon == 1) {
                    Toast.makeText(
                        this@FormMonitoringLanjut,
                        response.body()!!.message,
                        Toast.LENGTH_SHORT
                    ).show()
                    onBackPressed()
                } else if (respon == 0) {
                    Toast.makeText(
                        this@FormMonitoringLanjut,
                        response.body()!!.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Toast.makeText(
                    this@FormMonitoringLanjut,
                    "error : ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

    private fun getDetailPesanan() {
        ApiConfig.instancRetrofit.getDetailLahan(
            token = "Bearer ${sharedPref.fetchAuthToken()}",
            intent.getIntExtra("pesanan_id", 0)
        ).enqueue(object : Callback<ResponseModel> {
            @SuppressLint("ResourceAsColor", "SetTextI18n")
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                val respon= response.body()!!
                if (response.isSuccessful){
                    pesananId = respon.pesanan.id
                    pemeriksaanAwalID = respon.pesanan.pemeriksaan_awal.id
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Toast.makeText(this@FormMonitoringLanjut, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun getCheckBoxHama(checked: Boolean): String {
        val value: String
        value = if (checked) {
            "ada terkendali"
        } else "ada tidak terkendali"

        return value
    }

    private fun getCheckBoxPenanaman(checked: Boolean): String {
        val value: String
        value = if (checked) {
            "sesuai"
        } else "tidak sesuai"

        return value
    }


    private fun getCheckBoxIsolasi(checked: Boolean): String {
        val value: String
        value = if (checked) {
            "cukup"
        } else "kurang"

        return value
    }
}

//    private fun openCamera() {
//        var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        startActivityForResult(i, 123)
//    }

//    private fun openImageChooser() {
//        Intent(Intent.ACTION_PICK).also {
//            it.type = "image/*"
//            val mimeTypes = arrayOf("image/png", "image/jpeg")
//            it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
//            startActivityForResult(it, REQUEST_CODE_IMAGE_PICKER)
//        }
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode == Activity.RESULT_OK){
//            when(requestCode){
//                REQUEST_CODE_IMAGE_PICKER -> {
//                    selectedImage = data?.data
//                    iv_fase_lanjut.setImageURI(selectedImage)
//                    Log.d("tag", selectedImage.toString())
//                }
//            }
//        }
//    }

//    companion object {
//        private const val REQUEST_CODE_IMAGE_PICKER = 100
//    }
