package com.umy.pam_api.repository

import com.umy.pam_api.model.Aset
import com.umy.pam_api.model.Kategori
import com.umy.pam_api.service_api.AsetService
import com.umy.pam_api.service_api.KategoriService

interface KategoriRepository {
    suspend fun getKategori(): List<Kategori>
    suspend fun insertKategori(kategori: Kategori)
    suspend fun updateKategori(id_kategori: String, kategori: Kategori)
    suspend fun deleteKategori(id_kategori: String)
    suspend fun getKategoriById(id_kategori: String): Kategori
}

class NetworkKategoriRepository(private val kategoriService: KategoriService)
    : KategoriRepository {
    override suspend fun getKategori(): List<Kategori> = kategoriService.getKategori()


    override suspend fun insertKategori(kategori: Kategori) {
        kategoriService.insertKategori(kategori)
    }

    override suspend fun updateKategori(id_kategori: String, kategori: Kategori) {
        kategoriService.updateKategori(id_kategori, kategori)
    }

    override suspend fun deleteKategori(id_kategori: String) {
        try {
            val response = kategoriService.deleteKategori(id_kategori)
            if (!response.isSuccessful) {
                throw Exception("Failed to delete kategori. HTTP Status Code: ${response.code()}")
            }
            else{
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getKategoriById(id_kategori: String): Kategori {
        return kategoriService.getKategoriById(id_kategori)
    }
}