package com.example.phpandroidstudiomysql.apiservice

import com.example.phpandroidstudiomysql.modeldata.DataSiswa
import retrofit2.http.GET

interface ServiceApiSiswa {
    @GET("bacaTeman.php")
    suspend fun getSemuaSiswa(): List<DataSiswa>
}