package com.example.udmitrataniapps.model.pelanggan

import android.os.Parcelable
import com.example.udmitrataniapps.model.auth.PelangganModel
import kotlinx.android.parcel.Parcelize

@Parcelize
class DataLahan (
    var id: Int = 0,
    var nama_lahan: String,
    var alamat: String,
    var luas_lahan: Int = 0,
    var sejarah_lahan: String
) : Parcelable