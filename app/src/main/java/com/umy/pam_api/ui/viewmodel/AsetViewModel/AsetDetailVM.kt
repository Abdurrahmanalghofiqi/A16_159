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

object DestinasiDetailAset: DestinasiNavigasi {
    override val route = "detail aset"
    const val ID_ASET = "id_aset"
    override val titleRes = "Detail Mahasiswa"
    val routeWithArg = "$route/{$ID_ASET}"
}


class AsetDetailVM(
    savedStateHandle: SavedStateHandle,
    private val asetRepository: AsetRepository
) : ViewModel() {
    // Assuming the correct key is `ID_LOKASI`, not `NIM`
    private val id_aset: String = checkNotNull(savedStateHandle[DestinasiDetailAset.ID_ASET])

    var detailUiStateAset: DetailUiStateAset by mutableStateOf(DetailUiStateAset())
        private set

    init {
        getAsetById()
    }

    private fun getAsetById() {
        viewModelScope.launch {
            detailUiStateAset = DetailUiStateAset(isLoading = true)
            try {
                val result = asetRepository.getAsetById(id_aset)
                detailUiStateAset = DetailUiStateAset(
                    detailUiEventAset = result.toAsetDetailUiEvent(),
                    isLoading = false
                )
            } catch (e: Exception) {
                detailUiStateAset = DetailUiStateAset(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown error occurred"
                )
            }
        }
    }
}

// Consistent naming for the UI state
data class DetailUiStateAset(
    val detailUiEventAset : AsetDetailUiEvent = AsetDetailUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    val isAsetEmpty: Boolean
        get() = detailUiEventAset == AsetDetailUiEvent()

    val isAsetNotEmpty: Boolean
        get() = detailUiEventAset != AsetDetailUiEvent()
}

// Correct name for the event class
data class AsetDetailUiEvent(
    val id_aset: String = "",
    val nama_aset: String = "",
)

// Correct function name to map `Lokasi` to the new event model
fun Aset.toAsetDetailUiEvent(): AsetDetailUiEvent {
    return AsetDetailUiEvent(
        id_aset = id_aset,
        nama_aset = nama_aset,
    )
}


fun AsetDetailUiEvent.toAst(): Aset = Aset(
    id_aset = id_aset,
    nama_aset = nama_aset,
)
