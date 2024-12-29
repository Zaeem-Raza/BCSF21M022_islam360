import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    // Base URL of your API
    private const val BASE_URL = "https://quranapiservice-ekfkd2dvcsbmekcq.eastus-01.azurewebsites.net/index.html" // Replace with your actual API URL

    // Lazy initialization of Retrofit instance
    val api: QuranApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Use Gson to parse JSON
            .build()
            .create(QuranApiService::class.java) // Replace QuranApiService with your API service interface
    }
}
