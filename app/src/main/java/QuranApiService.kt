import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface QuranApiService {

    @GET("api/Ayat")
    fun getAllAyats(): Call<List<Ayah>>

    @GET("api/Ayat/{id}")
    fun getAyatById(@Path("id") id: Int): Call<Ayah>

    @GET("api/Para")
    fun getAllParas(): Call<List<Para>>

    @GET("api/Para/{id}")
    fun getParaById(@Path("id") id: Int): Call<Para>

    @GET("api/Surah")
    fun getAllSurahs(): Call<List<Surah>> // Renamed for clarity

    @GET("api/Surah/{id}")
    fun getSurahById(@Path("id") id: Int): Call<Surah>
}
