package com.example.phpandroidstudiomysql.repositori

import com.example.phpandroidstudiomysql.apiservice.ServiceApiSiswa
import com.example.phpandroidstudiomysql.modeldata.DataSiswa

interface RepositoryDataSiswa {
    suspend fun getDataSiswa(): List<DataSiswa>
}

class JaringanRepositoryDataSiswa(
    private val serviceApiSiswa: ServiceApiSiswa
):RepositoryDataSiswa {
    override suspend fun getDataSiswa() : List<DataSiswa> = serviceApiSiswa.getSemuaSiswa()
}