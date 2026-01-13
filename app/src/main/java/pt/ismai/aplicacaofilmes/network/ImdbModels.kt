package pt.ismai.aplicacaofilmes.network

import com.google.gson.annotations.SerializedName

data class ImdbApiResponse(
    @SerializedName("titles")
    val titles: List<ImdbTitle>?
)

data class ImdbTitle(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String?,
    @SerializedName("year")
    val year: Int?,
    @SerializedName("image")
    val image: ImdbImage?,
    @SerializedName("rating")
    val rating: Float?
)

data class ImdbImage(
    @SerializedName("url")
    val url: String?
)

// Modelos alternativos para pesquisa
data class SearchResponse(
    @SerializedName("results")
    val results: List<SearchResult>?
)

data class SearchResult(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String?,
    @SerializedName("year")
    val year: String?,
    @SerializedName("image")
    val image: String?
)
