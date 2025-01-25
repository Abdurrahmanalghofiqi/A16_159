package com.umy.pam_api.model

import kotlinx.serialization.Serializable


@Serializable
data class Aset(
    val id_aset: String,
    val nama_aset: String,
)