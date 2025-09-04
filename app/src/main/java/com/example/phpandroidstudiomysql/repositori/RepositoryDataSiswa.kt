package com.example.phpandroidstudiomysql.repositori

import com.example.phpandroidstudiomysql.apiservice.ServiceApiSiswa
import com.example.phpandroidstudiomysql.modeldata.DataSiswa

interface RepositoriDataSiswa {
    suspend fun getSiswa(): List<DataSiswa>
    suspend fun postDataSiswa(dataSiswa: DataSiswa) : retrofit2.Response<DataSiswa>
    suspend fun getStatusSiswa(id: Int): List<DataSiswa>
    suspend fun deleteSiswa(dataSiswa: DataSiswa)
    suspend fun updateSiswa(dataSiswa: DataSiswa)

}

class JaringanRepositoriDataSiswa(
    private val serviceApiSiswa: ServiceApiSiswa

): RepositoriDataSiswa {
    override suspend fun getSiswa(): List<DataSiswa> = serviceApiSiswa.getSiswa()
    override suspend fun postDataSiswa(dataSiswa: DataSiswa): retrofit2.Response<DataSiswa> = serviceApiSiswa.postDataSiswa(dataSiswa)
    override suspend fun getStatusSiswa(id: Int): List<DataSiswa> = serviceApiSiswa.getStatusSiswa(id)
    override suspend fun deleteSiswa(dataSiswa: DataSiswa) = serviceApiSiswa.deleteSiswa(dataSiswa.id)
    override suspend fun updateSiswa(dataSiswa: DataSiswa) = serviceApiSiswa.updateSiswa(dataSiswa.id, dataSiswa)



}