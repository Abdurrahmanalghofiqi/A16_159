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
import com.umy.pam_api.ui.view.EntryKategoriScreen
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
        composable(route = DestinasiHomeAset.route) {
            AsetScreen(
                navController = navController,
                navigateToItemEntry = { navController.navigate(DestinasiEntryAset.route) },
                onDetailClick = { id_aset ->
                    navController.navigate("${DestinasiDetailAset.route}/$id_aset")
                }
            )
        }
        composable(route = DestinasiEntryAset.route){
            AsetInsertView(navigateBack = {
                navController.navigate(DestinasiHomeAset.route){
                    popUpTo(DestinasiHomeAset.route){
                        inclusive = true
                    }
                }
            })
        }
        composable(
            DestinasiDetailAset.routeWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailAset.ID_ASET){
                    type = NavType.StringType
                }
            )
        ) {
            val id_aset = it.arguments?.getString(DestinasiDetailAset.ID_ASET)
            id_aset?.let {
                AsetDetailScreen(
                    navigateBack = {
                        navController.navigate(DestinasiHomeAset.route) {
                            popUpTo(DestinasiHomeAset.route) {
                                inclusive = true
                            }
                        }
                    },
                    navigateToEdit =  {
                        navController.navigate("${DestinasiUpdateAset.route}/$it")
                    }
                )
            }
        }
        composable(DestinasiUpdateAset.routesWithArg, arguments = listOf(navArgument(DestinasiDetailAset.ID_ASET){
            type = NavType.StringType
        }
        )
        ){
            val id_aset = it.arguments?.getString(DestinasiUpdateAset.ID_ASET)
            id_aset?.let { nim ->
                UpdateAsetView(
                    onBack = { navController.popBackStack() },
                    onNavigate = { navController.popBackStack() }
                )
            }
        }
        composable(route = DestinasiHome.route){
            HomeScreen(
                navController = navController,
                navigateToItemEntry = {navController.navigate(DestinasiEntry.route)},
                onDetailClick = { nim ->
                    navController.navigate("${DestinasiDetail.route}/$nim")
                }
            )
        }
        composable(route = DestinasiEntry.route){
            EntryKategoriScreen(navigateBack = {
                navController.navigate(DestinasiHome.route){
                    popUpTo(DestinasiHome.route){
                        inclusive = true
                    }
                }
            })
        }
        composable(
            DestinasiDetail.routeWithArg,
            arguments = listOf(
                navArgument(DestinasiDetail.ID_KATEGORI){
                    type = NavType.StringType
                }
            )
        ) {
            val id_kategori = it.arguments?.getString(DestinasiDetail.ID_KATEGORI)
            id_kategori?.let {
                DetailScreen(
                    navigateBack = {
                        navController.navigate(DestinasiHome.route) {
                            popUpTo(DestinasiHome.route) {
                                inclusive = true
                            }
                        }
                    },
                    navigateToEdit =  {
                        navController.navigate("${DestinasiUpdate.route}/$it")
                    }
                )
            }
        }
        composable(DestinasiUpdate.routesWithArg, arguments = listOf(navArgument(DestinasiDetail.ID_KATEGORI){
            type = NavType.StringType
        }
        )
        ){
            val id_kategori = it.arguments?.getString(DestinasiUpdate.ID_KATEGORI)
            id_kategori?.let { id_kategori ->
                UpdateScreen(
                    onBack = { navController.popBackStack() },
                    onNavigate = { navController.popBackStack() }
                )
            }
        }
    }

}