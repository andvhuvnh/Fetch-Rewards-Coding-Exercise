package andvhuvnh.itemlist.data.api

import andvhuvnh.itemlist.data.model.Item
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.http.GET

interface ApiService {
    @GET("hiring.json")
    suspend fun getItems(): List<Item>

    companion object {
        private const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"

        // Use lazy initialization to create the Retrofit service
        val instance: ApiService by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}