package com.umy.pam_api.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umy.pam_api.model.Kategori
import com.umy.pam_api.repository.KategoriRepository
import kotlinx.coroutines.launch

class InsertViewModel(private val kgr: KategoriRepository) : ViewModel() {
    var uiState by mutableStateOf(InsertUiState())
    private set

    fun updateInsertKgrState(insertUiEvent: InsertUiEvent) {
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertKgr() {
        viewModelScope.launch {
            try {
                kgr.insertKategori(uiState.insertUiEvent.toKgr())
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}

data class InsertUiState(
    val insertUiEvent : InsertUiEvent = InsertUiEvent(),
    val error: String? = null

)

data class InsertUiEvent(
    val id_kategori:String = "",
    val nama_kategori:String = ""
)

fun InsertUiEvent.toKgr(): Kategori = Kategori(
    id_kategori = id_kategori,
    nama_kategori = nama_kategori,
)

fun Kategori.toUiStateKgr(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Kategori.toInsertUiEvent():InsertUiEvent = InsertUiEvent(
    id_kategori = id_kategori,
    nama_kategori = nama_kategori
)