package com.umy.pam_api.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.umy.pam_api.model.Kategori
import com.umy.pam_api.repository.KategoriRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomeUiState {
    data class Success(val kategori: List<Kategori>) : HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}

class HomeViewModel (private val kgr: KategoriRepository): ViewModel(){
    var kgrUiState : HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getKgr()
    }

    fun getKgr(){
        viewModelScope.launch {
            kgrUiState = HomeUiState.Loading
            kgrUiState = try {
                HomeUiState.Success(kgr.getKategori())
            }catch (e:Exception) {
                HomeUiState.Error
            }catch (e:Exception) {
                HomeUiState.Error
            }
        }
    }

    fun deleteKgr(id_kategori : String) {
        viewModelScope.launch {
            try {
                kgr.deleteKategori(id_kategori)
            }catch(e:IOException){
                HomeUiState.Error
            }catch (e:HttpException) {
                HomeUiState.Error
            }
        }
    }
}