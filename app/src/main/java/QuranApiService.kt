import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface QuranApiService {

    @GET("api/Ayat")
    fun getAllAyats(): Call<List<Ayah>> // Replace `Ayah` with your Ayah data class.

    @GET("api/Ayat/{id}")
    fun getAyatById(@Path("id") id: Int): Call<Ayah> // Replace `Ayah` with your Ayah data class.

    @GET("api/Para")
    fun getAllParas(): Call<List<Para>> // Replace `Para` with your Para data class.

    @GET("api/Para/{id}")
    fun getParaById(@Path("id") id: Int): Call<Para> // Replace `Para` with your Para data class.

    @GET("api/Surah")
    fun getAllSurahs(): Call<List<Surah>> // Replace `Surah` with your Surah data class.

    @GET("api/Surah/{id}")
    fun getSurahById(@Path("id") id: Int): Call<Surah> // Replace `Surah` with your Surah data class.
}
