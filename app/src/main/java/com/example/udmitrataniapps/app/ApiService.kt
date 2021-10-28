package com.example.udmitrataniapps.app

import com.example.udmitrataniapps.model.ResponseArrayModel
import com.example.udmitrataniapps.model.ResponseModel
import com.example.udmitrataniapps.model.pegawai.ResponseJadwalMonitoring
import com.example.udmitrataniapps.model.pelanggan.PemeriksaanAwal
import com.example.udmitrataniapps.model.pelanggan.ResponseDetailVarietas
import okhttp3.MultipartBody
import okhttp3.RequestBody
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
        @Field("jenis_kelamin") jenis_kelamin: String,
        @Field("alamat") alamat:String,
        @Field("telepon") telepon:String,
        ):Call<ResponseModel>

    @FormUrlEncoded
    @POST("login-pelanggan")
    fun loginPelanggan(
        @Field("name") name: String,
        @Field("password") password: String,
        @Field("fcm") fcm: String
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

    @GET("pelanggan/monitoring")
    fun getMonitoringPelanggan(
        @Header("Authorization") token: String,
    ):Call<ResponseArrayModel>

    @GET("pelanggan/pemeriksaan-awal/{id}")
    fun getRiwayatMonitoring(
        @Header("Authorization") token: String,
        @Path("id") idPesanan : Int
    ):Call<PemeriksaanAwal>

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

    @GET("petugas/get-monitoring-awal-today")
    fun getMonitoringAwalToday(
        @Header("Authorization") token: String,
    ):Call<ResponseJadwalMonitoring>

    @GET("petugas/get-monitoring-vegetatif-today")
    fun getMonitoringVegetatifToday(
        @Header("Authorization") token: String,
    ):Call<ResponseJadwalMonitoring>

    @GET("petugas/get-monitoring-berbunga-today")
    fun getMonitoringBerbungaToday(
        @Header("Authorization") token: String,
    ):Call<ResponseJadwalMonitoring>

    @GET("petugas/get-monitoring-masak-today")
    fun getMonitoringMasakToday(
        @Header("Authorization") token: String,
    ):Call<ResponseJadwalMonitoring>

    @GET("petugas/detail-pesanan/{id}")
    fun getDetailLahan(
        @Header("Authorization") token: String,
        @Path("id") idPesanan : Int
    ):Call<ResponseModel>

    @FormUrlEncoded
    @POST("petugas/monitoring-awal")
    fun sendMonitoringAwal(
        @Header("Authorization") token : String,
        @Field("pesanan_id") pesanan_id : Int,
        @Field("letak_areal") letak_areal : String,
        @Field("luas_areal") luas_areal : String,
        @Field("isolasi") isolasi : String,
        @Field("sejarah_lapang") sejarah_lapang : String,
        @Field("asal_jumlah_benih") asal_jumlah_benih : String,
        @Field("catatan") catatan : String,
        @Field("kesimpulan") kesimpulan : String,
    ):Call<ResponseModel>

    @Multipart
    @POST("petugas/monitoring-lanjut")
    fun sendMonitoringLanjut(
        @Header("Authorization") token: String,
        @Part("pesanan_id") pesanan_id: RequestBody,
        @Part("pemeriksaan_awal_id") pemeriksaan_awal: RequestBody,
        @Part("jenis_pemeriksaan") jenis_pemeriksaan: RequestBody,
        @Part("isolasi_timur") isolasi_timur: RequestBody,
        @Part("isolasi_barat") isolasi_barat: RequestBody,
        @Part("isolasi_selatan") isolasi_selatan: RequestBody,
        @Part("isolasi_utara") isolasi_utara: RequestBody,
        @Part("barier") barier: RequestBody,
        @Part("sifat_penanaman") sifat_penanaman: RequestBody,
        @Part("serangan_hama") serangan_hama: RequestBody,
        @Part("perkiraan_produksi") perkiraan_produksi: RequestBody,
        @Part("jumlah_contoh_pemeriksaan") jumlah_contoh_pemeriksaan: RequestBody,
        @Part("waktu") waktu: RequestBody,
        @Part("perkiraan_tanggal_panen") perkiraan_tanggal_panen: RequestBody,
        @Part("inbrida_cvl1") inbrida_cvl1: RequestBody,
        @Part("inbrida_cvl2") inbrida_cvl2: RequestBody,
        @Part("inbrida_cvl3") inbrida_cvl3: RequestBody,
        @Part("inbrida_cvl4") inbrida_cvl4: RequestBody,
        @Part image: MultipartBody.Part
    ):Call<ResponseModel>

//    @Multipart
//    @FormUrlEncoded
//    @POST("petugas/monitoring-lanjut")
//    open fun editUser(
//        @Header("Authorization") authorization: String?,
//        @Part("file\"; filename=\"pp.png\" ") file: RequestBody?,
//        @Part("FirstName") fname: RequestBody?,
//        @Part("Id") id: RequestBody?
//    ): Call<User?>?
}