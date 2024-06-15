package com.example.myapp.network

import com.example.myapp.model.Hewan
import com.example.myapp.model.HewanResponse
import com.example.myapp.model.OpStatus
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query


private const val BASE_URL = "http://18.141.144.140:8081/v1/"
val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val client = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor)
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(client)
    .baseUrl(BASE_URL)
    .build()

interface HewanApiService {
    @GET("hewan")
    suspend fun getHewan(
        @Header("Authorization") userId: String
    ): HewanResponse

    @Multipart
    @POST("hewan")
    suspend fun postHewan(
        @Header("Authorization") userId: String,
        @Part("nama") nama: RequestBody,
        @Part("namaLatin") namaLatin: RequestBody,
        @Part image: MultipartBody.Part
    ): OpStatus

    @DELETE("hewan")
    suspend fun deleteHewan(
        @Header("Authorization") userId: String,
        @Query("id") imageId: String
    ): OpStatus
}

object HewanApi {
    val service: HewanApiService by lazy {
        retrofit.create(HewanApiService::class.java)
    }

    fun getHewanUrl(imageId: String): String {
        return "${BASE_URL}hewan/image?id=$imageId"
    }
}


enum class ApiStatus { LOADING, SUCCESS, FAILED }
