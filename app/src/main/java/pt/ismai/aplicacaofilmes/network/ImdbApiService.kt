package pt.ismai.aplicacaofilmes.network

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ImdbApiService {
    
    @GET("api/imdb/cast/{castId}/titles")
    suspend fun getMoviesByCast(
        @Path("castId") castId: String = "nm0000190",
        @Header("x-rapidapi-key") apiKey: String,
        @Header("x-rapidapi-host") apiHost: String = "imdb236.p.rapidapi.com"
    ): ImdbApiResponse
    
    @GET("api/imdb/search")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Header("x-rapidapi-key") apiKey: String,
        @Header("x-rapidapi-host") apiHost: String = "imdb236.p.rapidapi.com"
    ): ImdbApiResponse
}
