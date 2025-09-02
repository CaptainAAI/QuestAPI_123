package com.example.phpandroidstudiomysql.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.phpandroidstudiomysql.repositori.DefaultContainerApp

fun CreationExtras.aplikasiSiswa(): DefaultContainerApp.AplikasiDataSiswa =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as DefaultContainerApp.AplikasiDataSiswa)
object PenyediaViewModel{
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(aplikasiDataSiswa().container.repositoriSiswa)
        }
    }
}