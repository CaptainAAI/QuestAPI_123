package com.example.phpandroidstudiomysql.modeldata

import kotlinx.serialization.Serializable

@Serializable
data class DataSiswa(
    val id: String,
    val nama: String,
    val alamat: String,
    val telepon: String
)