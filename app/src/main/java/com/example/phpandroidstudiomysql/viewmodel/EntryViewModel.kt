package com.example.phpandroidstudiomysql.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phpandroidstudiomysql.modeldata.DataSiswa
import com.example.phpandroidstudiomysql.modeldata.DetailSiswa
import com.example.phpandroidstudiomysql.modeldata.UIStateSiswa
import com.example.phpandroidstudiomysql.modeldata.toDetailSiswa
import com.example.phpandroidstudiomysql.modeldata.toSiswa
import com.example.phpandroidstudiomysql.repositori.RepositoriDataSiswa
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Response

class EntryViewModel(private val repositoryDataSiswa: RepositoryDataSiswa: RepositoryDataSiswa): viewModel() {
    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    private fun validasiInput(uiState: DetailSiswa = uiStateSiswa.detailSiswa): Boolean {
        return with(uiState){
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }
    fun updateUiStateSiswa(uiState: DetailSiswa){
        uiStateSiswa =
            UIStateSiswa(detailSiswa = detailSiswa, isEntryValid = validasiInput(detailSiswa))
    }

    suspend fun addSiswa(): Response<Void>{
        if (validasiInput()) {
            val sip:Response<Void> = repositoryDataSiswa.postDataSiswa(uiStateSiswa.detailSiswa.toDataSiswa())
            if(sip.isSuccessful){
                println()("Sukses Tambah Data : ${sip.message()}")
            }else{
                println("Gagal Tambah Data : ${sip.message()}")
            }
        }

    }


}