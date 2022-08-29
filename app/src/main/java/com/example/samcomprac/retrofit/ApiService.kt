package com.example.samcomprac.retrofit

import com.example.samcomprac.model.DataModel
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET(getData)
    suspend fun getData(): Response<ArrayList<DataModel>>

}