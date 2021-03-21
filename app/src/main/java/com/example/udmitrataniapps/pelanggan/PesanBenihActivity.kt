package com.example.udmitrataniapps.pelanggan

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter
import com.example.udmitrataniapps.R
import com.example.udmitrataniapps.model.LahanPelanggan
import kotlinx.android.synthetic.main.activity_pesan_benih.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class PesanBenihActivity : AppCompatActivity() {

    private lateinit var lahanSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesan_benih)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val formatDate = SimpleDateFormat("dd MMMM YYYY", Locale.US)
        lahanSpinner = findViewById(R.id.sp_lahan)

        btn_tgl_sebar.setOnClickListener {
            val getDate : Calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth, DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->

                val selectDate : Calendar = Calendar.getInstance()
                selectDate.set(Calendar.YEAR,year)
                selectDate.set(Calendar.MONTH,month)
                selectDate.set(Calendar.DAY_OF_MONTH,day)
                val date : String = formatDate.format(selectDate.time)
                btn_tgl_sebar.text=date

            }, getDate.get(Calendar.YEAR), getDate.get(Calendar.MONTH), getDate.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }

        btn_tgl_tanam.setOnClickListener {
            val getDate : Calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth, DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->

                val selectDate : Calendar = Calendar.getInstance()
                selectDate.set(Calendar.YEAR,year)
                selectDate.set(Calendar.MONTH,month)
                selectDate.set(Calendar.DAY_OF_MONTH,day)
                val date : String = formatDate.format(selectDate.time)
                btn_tgl_tanam.text=date

            }, getDate.get(Calendar.YEAR), getDate.get(Calendar.MONTH), getDate.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }
    }

}