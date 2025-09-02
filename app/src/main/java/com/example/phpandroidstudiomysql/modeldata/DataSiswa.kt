package com.example.phpandroidstudiomysql.modeldata

import kotlinx.serialization.Serializable

@Serializable
data class DataSiswa {
    val id: Int,
    val nama: String,
    val alamat: String,
    val telepon: String,


}