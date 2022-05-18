package my.movieproject.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import my.movieproject.model.web.Films
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context : Context) : AppDB {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDB::class.java,
            "movies.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(appDb: AppDB) : MovieDao {
        return appDb.movieDao()
    }
}

@Database(entities = [Films::class], version = 1, exportSchema = true)
abstract class AppDB : RoomDatabase() {

    abstract fun movieDao() : MovieDao
}