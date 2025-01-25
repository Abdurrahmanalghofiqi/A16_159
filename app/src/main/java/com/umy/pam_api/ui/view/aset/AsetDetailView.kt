package com.umy.pam_api.ui.view.aset

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.umy.pam_api.model.Aset
import com.umy.pam_api.ui.navigasi.CostumeTopAppBar
import com.umy.pam_api.ui.navigasi.DestinasiNavigasi
import com.umy.pam_api.ui.view.PenyediaViewModel
import com.umy.pam_api.ui.viewmodel.AsetViewModel.AsetDetailVM
import com.umy.pam_api.ui.viewmodel.AsetViewModel.AsetUiState
import com.umy.pam_api.ui.viewmodel.AsetViewModel.DetailUiStateAset
import com.umy.pam_api.ui.viewmodel.AsetViewModel.toAst

object DestinasiDetailAset: DestinasiNavigasi {
    override val route = "detail aset"
    const val ID_ASET = "id_aset"
    override val titleRes = "Detail Mahasiswa"
    val routeWithArg = "$route/{$ID_ASET}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AsetDetailScreen(
    navigateBack: () -> Unit,
    navigateToEdit: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AsetDetailVM = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetailAset.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToEdit,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Delete Mahasiswa"
                )
            }
        }
    ) { innerPadding ->
        BodyDetailAst(
            detailUiStateAset = viewModel.detailUiStateAset,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyDetailAst(
    detailUiStateAset: DetailUiStateAset,
    modifier: Modifier = Modifier
) {
    when {
        detailUiStateAset.isLoading -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        detailUiStateAset.isError -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text =  detailUiStateAset.errorMessage,
                    color = Color.Red
                )
            }
        }
        detailUiStateAset.isAsetNotEmpty -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ItemDetailAst(
                    aset =  detailUiStateAset.detailUiEventAset.toAst(),
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
fun ItemDetailAst(
    modifier: Modifier = Modifier,
    aset: Aset
){
    Card(
        modifier = modifier.fillMaxWidth().padding(top = 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ){
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            ComponentDetailAst(judul = "ID Aset", isinya = aset.id_aset)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailAst(judul = "Nama", isinya = aset.nama_aset)
            Spacer(modifier = Modifier.padding(4.dp))
        }
    }
}

@Composable
fun ComponentDetailAst(
    modifier: Modifier = Modifier,
    judul:String,
    isinya:String
){
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul : ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(
            text = isinya,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}