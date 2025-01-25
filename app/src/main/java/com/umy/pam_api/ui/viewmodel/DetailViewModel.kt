package com.umy.pam_api.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umy.pam_api.model.Kategori
import com.umy.pam_api.repository.KategoriRepository
import com.umy.pam_api.ui.view.DestinasiDetail
import kotlinx.coroutines.launch

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val kategoriRepository: KategoriRepository
) : ViewModel() {
    private val id_kategori: String = checkNotNull(savedStateHandle[DestinasiDetail.ID_KATEGORI])

    var detailUiState: DetailUiState by mutableStateOf(DetailUiState())
        private set

    init {
        getKategoriById()
    }

    private fun getKategoriById() {
        viewModelScope.launch {
            detailUiState = DetailUiState(isLoading = true)
            try {
                val result = kategoriRepository.getKategoriById(id_kategori)
                detailUiState = DetailUiState(
                    detailUiEvent = result.toDetailUiEvent(),
                    isLoading = false
                )
            } catch (e: Exception) {
                detailUiState = DetailUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown error occurred"
                )
            }
        }
    }
}

data class DetailUiState(
    val detailUiEvent: InsertUiEvent = InsertUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
){
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == InsertUiEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != InsertUiEvent()
}

fun Kategori.toDetailUiEvent(): InsertUiEvent{
    return InsertUiEvent(
        id_kategori = id_kategori,
        nama_kategori = nama_kategori
    )
}