package my.movieproject.model.web

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class MovieInfo(

    @SerializedName("pagesCount")
    var pagesCount: Int? = null,

    @SerializedName("films")
    var films: ArrayList<Films> = arrayListOf()

) {
    override fun toString(): String {
        return "MovieInfo(pagesCount=$pagesCount, films=$films)"
    }
}

data class Countries(

    @SerializedName("country")
    var country: String? = null

) {
    override fun toString(): String {
        return "Countries(country=$country)"
    }
}

data class Genres(

    @SerializedName("genre") var genre: String? = null

) {
    override fun toString(): String {
        return "Genres(genre=$genre)"
    }
}

@Entity(tableName = "films")
data class Films(

    @SerializedName("filmId")
    @PrimaryKey
    var filmId: Int? = null,

    @SerializedName("nameRu")
    var nameRu: String? = null,

    @SerializedName("nameEn")
    var nameEn: String? = null,

    @SerializedName("year")
    var year: String? = null,

    @SerializedName("filmLength")
    var filmLength: String? = null,

    @SerializedName("countries")
    @Ignore
    var countries: ArrayList<Countries> = arrayListOf(),

    @SerializedName("genres")
    @Ignore
    var genres: ArrayList<Genres> = arrayListOf(),

    @SerializedName("rating")
    var rating: String? = null,

    @SerializedName("ratingVoteCount")
    var ratingVoteCount: Int? = null,

    @SerializedName("posterUrl")
    var posterUrl: String? = null,

    @SerializedName("posterUrlPreview")
    var posterUrlPreview: String? = null,

    @SerializedName("ratingChange")
    var ratingChange: String? = null
) {
    override fun toString(): String {
        return "Films(filmId=$filmId, nameRu=$nameRu, nameEn=$nameEn, year=$year, filmLength=$filmLength, countries=$countries, genres=$genres, rating=$rating, ratingVoteCount=$ratingVoteCount, posterUrl=$posterUrl, posterUrlPreview=$posterUrlPreview, ratingChange=$ratingChange)"
    }
}