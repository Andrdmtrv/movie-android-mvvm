package my.movieproject.model.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import my.movieproject.model.repository.impl.MovieRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun provideMovieRepositoryImpl(repository : MovieRepositoryImpl) : MovieRepository

}