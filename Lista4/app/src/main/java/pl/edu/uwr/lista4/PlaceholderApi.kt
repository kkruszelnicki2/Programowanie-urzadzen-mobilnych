package pl.edu.uwr.lista4

import retrofit2.Call
import retrofit2.http.GET

interface PlaceholderApi {
    @GET("v2/all")
    fun posts(): Call<List<MyDataItem>>
}