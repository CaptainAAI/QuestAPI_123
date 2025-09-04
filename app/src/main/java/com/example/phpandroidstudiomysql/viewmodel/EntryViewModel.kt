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

class EntryViewModel (private val repositoriDataSiswa: RepositoriDataSiswa): ViewModel() {
    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    private fun validasiInput(uiState: DetailSiswa = uiStateSiswa.detailSiswa): Boolean {
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }

    fun updateUiState(detailSiswa: DetailSiswa) {
        uiStateSiswa =
            UIStateSiswa(detailSiswa = detailSiswa, isEntryValid = validasiInput(detailSiswa))
    }

    suspend fun addSiswa() {
        if (validasiInput()) {
            val sip: Response<DataSiswa> =
                repositoriDataSiswa.postDataSiswa(uiStateSiswa.detailSiswa.toSiswa())
            if (sip.isSuccessful) {
                print("sukses" + sip.message())
            } else {
                print("gagal" + sip.errorBody())
            }
        }
    }

    suspend fun saveSiswa() {
        if (validasiInput()) {
            repositoriDataSiswa.postDataSiswa(uiStateSiswa.detailSiswa.toSiswa())
        }
    }



}