package com.example.phpandroidstudiomysql.viewmodel

import android.net.http.UrlRequest.Status
import android.view.View
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.phpandroidstudiomysql.modeldata.DataSiswa
import com.example.phpandroidstudiomysql.repositori.JaringanRepositoryDataSiswa
import com.example.phpandroidstudiomysql.repositori.RepositoryDataSiswa
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

sealed interface StatusUiSiswa {
    data class Success(val siswa: List<DataSiswa> = listOf()) : StatusUiSiswa
    object Error : StatusUiSiswa
    object Loading : StatusUiSiswa
}
class HomeViewModel(private val repositoryDataSiswa: RepositoryDataSiswa): ViewModel() {
    var listSiswa: StatusUiSiswa by mutableStateOf(StatusUiSiswa.Loading)
        private set

    init{
        loadSiswa()
    }

    fun loadSiswa(){
        viewModelScope.launch {
            listSiswa = StatusUiSiswa.Loading
            listSiswa = try {
                StatusUiSiswa.Success(repositoryDataSiswa.getDataSiswa())
            }catch (e:IOException){
                StatusUiSiswa.Error
            }
            catch (e:HttpException){
                StatusUiSiswa.Error
            }
        }
    }
}