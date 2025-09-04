package com.example.phpandroidstudiomysql.apiservice

import com.example.phpandroidstudiomysql.modeldata.DataSiswa
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ServiceApiSiswa {@GET("coba.php")
suspend fun getSiswa(): List<DataSiswa>

    @POST("bacaTeman.php")
    suspend fun postDataSiswa(@Body dataSiswa: DataSiswa) : retrofit2.Response<DataSiswa>

    @GET("coba.php")
    suspend fun getStatusSiswa(@Query("id") id: Int): List<DataSiswa>

    @DELETE("coba.php")
    suspend fun deleteSiswa(@Query("id") id: Int)

    @PUT("coba.php")
    suspend fun updateSiswa(@Query("id") id: Int, @Body dataSiswa: DataSiswa)
}