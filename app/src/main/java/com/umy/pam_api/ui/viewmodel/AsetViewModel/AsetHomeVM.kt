package com.umy.pam_api.ui.viewmodel.AsetViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.umy.pam_api.model.Aset
import com.umy.pam_api.repository.AsetRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class AsetUiState {
    data class Success(val aset: List<Aset>) : AsetUiState()
    object Error : AsetUiState()
    object Loading : AsetUiState()
}

class AsetViewModel(ast1: SavedStateHandle, private val ast: AsetRepository): ViewModel(){
    var astUiState : AsetUiState by mutableStateOf(AsetUiState.Loading)
        private set

    init {
        getAst()
    }

    fun getAst(){
        viewModelScope.launch {
            astUiState = AsetUiState.Loading
            astUiState = try {
                AsetUiState.Success(ast.getAset())
            }catch (e:Exception) {
                AsetUiState.Error
            }catch (e:Exception) {
                AsetUiState.Error
            }
        }
    }

    fun deleteAset(id_aset:String) {
        viewModelScope.launch {
            try {
                ast.deleteAset(id_aset)
            }catch(e:IOException){
                AsetUiState.Error
            }catch (e:HttpException) {
                AsetUiState.Error
            }
        }
    }
}