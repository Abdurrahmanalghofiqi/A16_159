package com.umy.pam_api.ui.view.aset

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.umy.pam_api.ui.navigasi.CostumeTopAppBar
import com.umy.pam_api.ui.navigasi.DestinasiNavigasi
import com.umy.pam_api.ui.view.PenyediaViewModel
import com.umy.pam_api.ui.viewmodel.AsetViewModel.AsetInsertVM
import com.umy.pam_api.ui.viewmodel.AsetViewModel.AsetUiEvent
import com.umy.pam_api.ui.viewmodel.AsetViewModel.AsetUiState1
import com.umy.pam_api.ui.viewmodel.InsertUiEvent
import kotlinx.coroutines.launch

object DestinasiEntryAset : DestinasiNavigasi {
    override val route = "item ast"
    override val titleRes = "Entry Ast"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AsetInsertView(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AsetInsertVM = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiEntryAset.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        EntryBody(
            asetUiState = viewModel.uiState,
            onSiswaValueChange = viewModel::updateInsertAstState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertAst()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun EntryBody(
    asetUiState: AsetUiState1,
    onSiswaValueChange: (AsetUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInput(
            asetUiEvent = asetUiState.asetUiEvent,
            onValueChange = onSiswaValueChange,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Simpan")
        }
    }
}

@Composable
fun FormInput(
    asetUiEvent: AsetUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (AsetUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = asetUiEvent.id_aset,
            onValueChange = { onValueChange(asetUiEvent.copy(id_aset = it)) },
            label = { Text("ID Aset") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = asetUiEvent.nama_aset,
            onValueChange = { onValueChange(asetUiEvent.copy(nama_aset = it)) },
            label = { Text("Nama Aset") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        Divider(
            thickness = 8.dp,
            modifier = Modifier.padding(12.dp)
        )
    }
}
