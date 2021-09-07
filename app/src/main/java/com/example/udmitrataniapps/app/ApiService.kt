package com.example.udmitrataniapps.app

import com.example.udmitrataniapps.model.ResponseArrayModel
import com.example.udmitrataniapps.model.ResponseModel
import com.example.udmitrataniapps.model.pegawai.ResponseJadwalMonitoring
import com.example.udmitrataniapps.model.pelanggan.ResponseDetailVarietas
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("register-pelanggan")
    fun registerPelanggan(
        @Field("nama_lengkap") nama_lengkap:String,
        @Field("name") name:String,
        @Field("password") password:String,
        @Field("nik") nik:String,
        @Field("alamat") alamat:String,
        @Field("telepon") telepon:String,
        ):Call<ResponseModel>

    @FormUrlEncoded
    @POST("login-pelanggan")
    fun loginPelanggan(
        @Field("name") name: String,
        @Field("password") password: String
    ):Call<ResponseModel>

    @FormUrlEncoded
    @POST("login-pegawai")
    fun loginPegawai(
        @Field("name") name: String,
        @Field("password") password: String
    ):Call<ResponseModel>

    @FormUrlEncoded
    @POST("pelanggan/tambah-lahan")
    fun tambahLahan(
        @Header("Authorization") token : String,
        @Field("nama_lahan") nama_lahan : String,
        @Field("alamat") alamat: String,
        @Field("luas_lahan") luas_lahan: String,
        @Field("sejarah_lahan") sejarah_lahan: String
    ):Call<ResponseModel>

    @GET("pelanggan/data-lahan")
    fun dataLahan(@Header("Authorization") token : String):Call<ResponseArrayModel>

    @FormUrlEncoded
    @PUT("pelanggan/update-lahan/{id}")
    fun updateLahan(
        @Header("Authorization") token: String,
        @Path("id") idLahan : Int,
        @Field("nama_lahan") nama_lahan : String?,
        @Field("alamat") alamat: String?,
        @Field("luas_lahan") luas_lahan: String?,
        @Field("sejarah_lahan") sejarah_lahan: String?
    ):Call<ResponseModel>

    @FormUrlEncoded
    @POST("pelanggan/delete-lahan")
    fun deleteLahan(
        @Header("Authorization") token : String,
        @Field("id") idlahan : Int
    ):Call<ResponseModel>

    @GET("pelanggan/varietas")
    fun fetchVariatasPadi(@Header("Authorization") token : String):Call<ResponseArrayModel>

    @GET("pelanggan/varietas/{id}")
    fun fetchVarietasById(
        @Header("Authorization") token: String,
        @Path("id") idVarietas: String?
    ):Call<ResponseDetailVarietas>

    @FormUrlEncoded
    @POST("pelanggan/pesan-benih")
    fun pesanBenih(
        @Header("Authorization") token : String,
        @Field("lahan_pelanggan_id") lahan_pelanggan_id : Int,
        @Field("tgl_sebar") tgl_sebar : String,
        @Field("tgl_tanam") tgl_tanam : String,
        @Field("varietas_padi") varietas_padi : String,
        @Field("total_benih") total_benih : String
        ):Call<ResponseModel>

    @GET("pelanggan/detail-pesanan/{id}")
    fun getDetailPesanan(
        @Header("Authorization") token: String,
        @Path("id") idPesanan : Int
    ):Call<ResponseModel>

    @GET("pelanggan/pesanans")
    fun getPesanansPelanggan(
        @Header("Authorization") token: String,
    ):Call<ResponseArrayModel>

    @GET("petugas/jadwal-monitoring-fase-awal")
    fun getJadwalMonitoringFaseAwal(
        @Header("Authorization") token: String,
    ):Call<ResponseJadwalMonitoring>

    @GET("petugas/jadwal-monitoring-fase-vegetatif")
    fun getJadwalMonitoringFaseVegetatif(
        @Header("Authorization") token: String,
    ):Call<ResponseJadwalMonitoring>

    @GET("petugas/jadwal-monitoring-fase-berbunga")
    fun getJadwalMonitoringFaseBerbunga(
        @Header("Authorization") token: String,
    ):Call<ResponseJadwalMonitoring>

    @GET("petugas/jadwal-monitoring-fase-masak")
    fun getJadwalMonitoringFaseMasak(
        @Header("Authorization") token: String,
    ):Call<ResponseJadwalMonitoring>
}