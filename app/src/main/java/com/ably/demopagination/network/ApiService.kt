package com.ably.demopagination.network
import com.ably.demopagination.model.ApiResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users/google/repos")
    suspend fun getPassengersData(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): ApiResponse

    companion object {

        private const val BASE_URL = "https://api.github.com/"

        operator fun invoke(): ApiService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().also { client ->
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(logging)
            }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}