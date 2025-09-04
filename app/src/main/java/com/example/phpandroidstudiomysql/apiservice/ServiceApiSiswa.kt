package com.example.phpandroidstudiomysql.apiservice

import com.example.phpandroidstudiomysql.modeldata.DataSiswa
import retrofit2.http.GET
import retrofit2.http.POST

interface ServiceApiSiswa {
    @GET("bacaTeman.php")
    suspend fun getSemuaSiswa(): List<DataSiswa>

    @POST("InsertTM.php")
    suspend fun postSiswa(dataSiswa: DataSiswa): retrofit2.Response<Void>


}
