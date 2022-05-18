package my.movieproject.model.repository

import kotlinx.coroutines.flow.Flow
import my.movieproject.model.web.Films

interface MovieRepository {

    val films: Flow<List<Films>>

    suspend fun fetchMovies()

    suspend fun updateMovies()
}