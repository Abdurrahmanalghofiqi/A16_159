package com.umy.pam_api

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeApp(
    modifier: Modifier = Modifier,
    onHalamanHomeKategori: () -> Unit,
    onHalamanHomeAset: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFFEDEDED), Color(0xFFFFFFFF))))
            .padding(top = 30.dp),
        verticalArrangement = Arrangement.Top
    ) {
        HeaderSection()
        BodySection(
            onHalamanHomeKategori = onHalamanHomeKategori,
            onHalamanHomeAset = onHalamanHomeAset,
        )
    }
}

@Composable
fun HeaderSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.horizontalGradient(
                    colors = listOf(Color(0xFF4CAF50), Color(0xFF087F23))
                ),
                shape = RoundedCornerShape(bottomEnd = 32.dp, bottomStart = 32.dp)
            )
            .padding(vertical = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Dashboard Management",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
fun BodySection(
    onHalamanHomeKategori: () -> Unit,
    onHalamanHomeAset: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Silahkan pilih menu yang ingin anda kelola",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .wrapContentWidth(Alignment.CenterHorizontally)
        )

        ManageBox(
            title = "Kategori",
            description = "Kelola Kategori",
            gradientColors = listOf(Color(0xFF4CAF50), Color(0xFF087F23)),
            onClick = { onHalamanHomeKategori() }
        )

        ManageBox(
            title = "Aset",
            description = "Kelola Aset",
            gradientColors = listOf(Color(0xFF03A9F4), Color(0xFF0288D1)),
            onClick = { onHalamanHomeAset() }
        )
    }
}

@Composable
fun ManageBox(
    title: String,
    description: String,
    gradientColors: List<Color>,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(colors = gradientColors),
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { onClick() }
            .padding(16.dp)
            .height(100.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                fontSize = 22.sp,
                color = Color.White
            )
        }
    }
}
