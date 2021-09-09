package com.example.udmitrataniapps.petugas

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.udmitrataniapps.R
import kotlinx.android.synthetic.main.activity_form_monitoring_awal.*
import kotlinx.android.synthetic.main.activity_form_monitoring_lanjut.*

class FormMonitoringLanjut : AppCompatActivity() {

    private var selectedImage : Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_monitoring_lanjut)

        iv_fase_lanjut.setOnClickListener{
            OpenImageChooser()
        }
    }

    private fun OpenImageChooser() {
        Intent(Intent.ACTION_PICK).also {
            it.type = "image/*"
            val mimeTypes = arrayOf("image/jpeg", "image/png")
            it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            startActivityForResult(it, REQUEST_CODE_IMAGE_PICKER)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            when(requestCode){
                REQUEST_CODE_IMAGE_PICKER -> {
                    selectedImage = data?.data
                    iv_fase_lanjut.setImageURI(selectedImage)
                }
            }
        }
    }

    companion object {
        private const val REQUEST_CODE_IMAGE_PICKER = 100
    }
}