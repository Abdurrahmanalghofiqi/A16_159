package com.umy.pam_api.repository

import com.umy.pam_api.service_api.AsetService
import com.umy.pam_api.model.Aset

interface AsetRepository {
    suspend fun getAset(): List<Aset>
    suspend fun insertAset(aset: Aset)
    suspend fun updateAset(id_aset: String, aset: Aset)
    suspend fun deleteAset(id_aset: String)
    suspend fun getAsetById(id_aset: String): Aset
}

class NetworkAsetRepository(private val asetService: AsetService)
    : AsetRepository
{
    override suspend fun getAset(): List<Aset> = asetService.getAset()
    override suspend fun insertAset(aset: Aset) {
        asetService.insertAset(aset)
    }

    override suspend fun updateAset(id_aset: String, aset: Aset) {
        asetService.insertAset(aset)
    }

    override suspend fun deleteAset(id_aset: String) {
        try {
            val response = asetService.deleteAset(id_aset)
            if (!response.isSuccessful) {
                throw Exception("Failed to delete lokasi. HTTP Status Code: ${response.code()}")
            }
            else{
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getAsetById(id_aset: String): Aset {
        return asetService.getAsetById(id_aset)
    }
}