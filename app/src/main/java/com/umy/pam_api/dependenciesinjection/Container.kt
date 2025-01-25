package com.umy.pam_api.dependenciesinjection

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.umy.pam_api.repository.AsetRepository
import com.umy.pam_api.repository.KategoriRepository
import com.umy.pam_api.repository.NetworkAsetRepository
import com.umy.pam_api.repository.NetworkKategoriRepository
import com.umy.pam_api.service_api.AsetService
import com.umy.pam_api.service_api.KategoriService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val asetRepository: AsetRepository
    val kategoriRepository : KategoriRepository
}

class TugasContainer : AppContainer {
    private val baseUrl = "http://10.0.2.2:81/umyTI/" //http://10.0.2.2:8080/umyTI/ untuk lokal
    private val json = Json { ignoreUnknownKeys = true}
    private val retrofit: Retrofit = Retrofit.Builder()

        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val kategoriService: KategoriService by lazy {
        retrofit.create(KategoriService::class.java)
    }
    override val kategoriRepository : KategoriRepository by lazy {
        NetworkKategoriRepository (kategoriService)
    }


}