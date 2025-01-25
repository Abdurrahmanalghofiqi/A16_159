package com.umy.pam_api.ui.view

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.umy.pam_api.application.Applications
import com.umy.pam_api.ui.viewmodel.AsetViewModel.AsetDetailVM
import com.umy.pam_api.ui.viewmodel.AsetViewModel.AsetInsertVM
import com.umy.pam_api.ui.viewmodel.AsetViewModel.AsetUpdateVM
import com.umy.pam_api.ui.viewmodel.AsetViewModel.AsetViewModel
import com.umy.pam_api.ui.viewmodel.DetailViewModel
import com.umy.pam_api.ui.viewmodel.HomeViewModel
import com.umy.pam_api.ui.viewmodel.InsertViewModel
import com.umy.pam_api.ui.viewmodel.UpdateViewModel

object PenyediaViewModel{
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(
                MahasiswaApplication().container.kategoriRepository)
        }


    }
}

fun CreationExtras.MahasiswaApplication(): Applications =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Applications)