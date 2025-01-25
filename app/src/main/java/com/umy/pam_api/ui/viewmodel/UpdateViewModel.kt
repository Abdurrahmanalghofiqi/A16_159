package com.umy.pam_api.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umy.pam_api.model.Kategori
import com.umy.pam_api.repository.KategoriRepository
import com.umy.pam_api.ui.view.DestinasiUpdate
import kotlinx.coroutines.launch

class UpdateViewModel(
    savedStateHandle: SavedStateHandle,
    private val kgr: KategoriRepository
) : ViewModel() {
    var updateUiState by mutableStateOf(InsertUiState())
        private set

    private val _id_kategori: String = checkNotNull(savedStateHandle[DestinasiUpdate.ID_KATEGORI])

    init {
        viewModelScope.launch {
            try {
                updateUiState = kgr.getKategoriById(_id_kategori)
                    .toUIStateKgr()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateInsertKgrState(insertUiEvent: InsertUiEvent) {
        updateUiState = updateUiState.copy(insertUiEvent = insertUiEvent)
    }

    suspend fun updateKgr() {
        viewModelScope.launch {
            try {
                kgr.updateKategori(_id_kategori, updateUiState.insertUiEvent.toKgr())
                println("Update berhasil!")
            } catch (e: Exception) {
                e.printStackTrace()
                println("Update gagal: ${e.message}")
            }
        }
    }
}

fun Kategori.toUIStateKgr(): InsertUiState = InsertUiState(
    insertUiEvent = this.toDetailUiEvent()
)
