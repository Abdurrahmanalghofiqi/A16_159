package com.umy.pam_api.ui.navigasi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.umy.pam_api.HomeApp
import com.umy.pam_api.ui.view.DestinasiDetail
import com.umy.pam_api.ui.view.DestinasiUpdate
import com.umy.pam_api.ui.view.DetailScreen
import com.umy.pam_api.ui.view.EntryAsetScreen
import com.umy.pam_api.ui.view.HomeScreen
import com.umy.pam_api.ui.view.UpdateScreen
import com.umy.pam_api.ui.view.aset.AsetDetailScreen
import com.umy.pam_api.ui.view.aset.AsetInsertView
import com.umy.pam_api.ui.view.aset.AsetScreen
import com.umy.pam_api.ui.view.aset.DestinasiDetailAset
import com.umy.pam_api.ui.view.aset.DestinasiEntryAset
import com.umy.pam_api.ui.view.aset.DestinasiUpdateAset
import com.umy.pam_api.ui.view.aset.UpdateAsetView

@Composable
fun PengelolaHalaman(
    modifier : Modifier = Modifier,
    navController: NavHostController = rememberNavController()){
    NavHost(
        navController = navController,
        startDestination = DestinasiMain.route,
        modifier = Modifier,
    ){
        composable(route = DestinasiMain.route){
            HomeApp(
                onHalamanHomeKategori = {
                    navController.navigate(DestinasiHome.route)
                },
                onHalamanHomeAset   = {
                    navController.navigate(DestinasiHomeAset.route)
                },
                modifier = modifier
            )
        }

    }

}