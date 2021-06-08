package com.example.udmitrataniapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.udmitrataniapps.pelanggan.DashboardPelangganActivity
import com.example.udmitrataniapps.petugas.DashboardPetugasActivity
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        toDaftarView()

//        {
//            if (switch_pelanggan.isChecked){
//                var intent = Intent(this, DashboardPelangganActivity::class.java)
//                startActivity(intent)
//            }else{
//                var intent = Intent(this, DashboardPetugasActivity::class.java)
//                startActivity(intent)
//            }
//        }
//
//        tvDaftar.setOnClickListener {
//            val intent = Intent(this, SignUpActivity::class.java)
//            startActivity(intent)
//        }
    }

    private fun toDaftarView() {
        tv_to_register.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}


