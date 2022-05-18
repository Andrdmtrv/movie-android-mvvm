package my.movieproject.model.repository.impl

import kotlinx.coroutines.flow.Flow
import my.movieproject.model.db.MovieDao
import my.movieproject.model.repository.MovieRepository
import my.movieproject.model.web.Films
import my.movieproject.model.web.MovieApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(private val movieDao: MovieDao, private val movieService: MovieApi) :
    MovieRepository {

    override val films: Flow<List<Films>> = movieDao.getMovies()

    override suspend fun fetchMovies() {
        movieService.getMovieInfo().let {
            movieDao.insert(it.films)
        }
    }

    override suspend fun updateMovies() {
        movieDao.deleteAll()
        fetchMovies()
    }
}