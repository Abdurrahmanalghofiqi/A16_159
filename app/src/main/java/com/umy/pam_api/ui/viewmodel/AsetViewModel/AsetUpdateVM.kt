package com.umy.pam_api.ui.viewmodel.AsetViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umy.pam_api.model.Aset
import com.umy.pam_api.repository.AsetRepository
import com.umy.pam_api.ui.navigasi.DestinasiNavigasi
import kotlinx.coroutines.launch

object DestinasiUpdateAset : DestinasiNavigasi {
    override val route = "update aset"
    override val titleRes = "Update Aset"
    const val ID_ASET = "id_aset"
    val routesWithArg = "$route/{$ID_ASET}"
}

class AsetUpdateVM(
    savedStateHandle: SavedStateHandle,
    private val asetRepository: AsetRepository
) : ViewModel() {
    var updateUiState by mutableStateOf(AsetUiState1())
        private set

    // Ambil ID aset dari argument yang diteruskan
    private val _id_aset: String = checkNotNull(savedStateHandle[DestinasiUpdateAset.ID_ASET])

    init {
        // Inisialisasi UI state dengan data dari repository
        viewModelScope.launch {
            try {
                val aset = asetRepository.getAsetById(_id_aset)
                updateUiState = aset.toUIStateAst()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Memperbarui UI state berdasarkan perubahan dari pengguna
    fun updateInsertAstState(asetUiEvent: AsetUiEvent) {
        updateUiState = updateUiState.copy(asetUiEvent = asetUiEvent)
    }

    // Fungsi untuk memperbarui data aset ke repository
    fun updateAset() {
        viewModelScope.launch {
            try {
                val updatedAset = updateUiState.asetUiEvent.toAst()
                asetRepository.updateAset(_id_aset, updatedAset)
                println("Data aset berhasil diperbarui!")
            } catch (e: Exception) {
                e.printStackTrace()
                println("Gagal memperbarui data aset: ${e.message}")
            }
        }
    }
}

// Fungsi ekstensi untuk mengonversi objek Aset menjadi UI state
fun Aset.toUIStateAst(): AsetUiState1 = AsetUiState1(
    asetUiEvent = this.toUiStateAst()
)
