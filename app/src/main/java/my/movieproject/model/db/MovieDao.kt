package my.movieproject.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import my.movieproject.model.web.Films

@Dao
interface MovieDao {

    @Query("SELECT * FROM films")
    fun getMovies() : Flow<List<Films>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data : List<Films>)

    @Query("DELETE FROM films")
    suspend fun deleteAll()
}