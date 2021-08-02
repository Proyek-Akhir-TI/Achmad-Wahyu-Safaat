package com.example.udmitrataniapps.model.pelanggan

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
class VarietasPadi (
    var id : Int = 0,
    var nama_varietas : String = "",
    var deskripsi_varietas : String = "",
    var foto_varietas : String = "",
    var stok_padi: @RawValue StokPadiModel
): Parcelable