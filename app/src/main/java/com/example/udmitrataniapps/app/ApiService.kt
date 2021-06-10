package com.example.udmitrataniapps.app

import com.example.udmitrataniapps.model.auth.ResponseAuthPelangganModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("register-pelanggan")
    fun register_pelanggan(
        @Field("nama_lengkap") nama_lengkap:String,
        @Field("name") name:String,
        @Field("password") password:String,
        @Field("nik") nik:String,
        @Field("alamat") alamat:String,
        @Field("telepon") telepon:String,
        ):Call<ResponseAuthPelangganModel>

    @FormUrlEncoded
    @POST("login-pelanggan")
    fun login_pelanggan(
        @Field("name") name: String,
        @Field("password") password: String
    ):Call<ResponseAuthPelangganModel>
}