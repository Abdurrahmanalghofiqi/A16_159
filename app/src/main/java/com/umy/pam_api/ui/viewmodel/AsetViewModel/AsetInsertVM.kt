package com.umy.pam_api.ui.viewmodel.AsetViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umy.pam_api.model.Aset
import com.umy.pam_api.repository.AsetRepository
import kotlinx.coroutines.launch

class AsetInsertVM(
    asetRepository: SavedStateHandle,
    private val ast: AsetRepository
) : ViewModel() {
    // Define the UI state variable for Insert operation
    var uiState by mutableStateOf(AsetUiState1())
        private set

    fun updateInsertAstState(asetUiEvent: AsetUiEvent) {
        uiState = AsetUiState1(asetUiEvent = asetUiEvent)
    }

    fun insertAst() {
        viewModelScope.launch {
            try {
                ast.insertAset(uiState.asetUiEvent.toAst())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class AsetUiState1(
    val asetUiEvent: AsetUiEvent = AsetUiEvent(),
    val error: String? = null
)

data class AsetUiEvent(
    val id_aset: String = "",
    val nama_aset: String = "",
)

fun AsetUiEvent.toAst(): Aset = Aset(
    id_aset = id_aset,
    nama_aset = nama_aset,
)

fun Aset.toUiStateAst(): AsetUiEvent = AsetUiEvent(
    id_aset = id_aset,
    nama_aset = nama_aset,
)