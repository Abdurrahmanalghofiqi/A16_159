package com.umy.pam_api.model

import kotlinx.serialization.Serializable


@Serializable

data class Kategori(
    val id_kategori: String,
    val nama_kategori: String,
)
