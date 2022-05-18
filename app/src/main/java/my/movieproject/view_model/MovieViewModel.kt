package my.movieproject.view_model

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.*
import com.squareup.picasso.Picasso
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import my.movieproject.model.repository.MovieRepository
import my.movieproject.model.web.Films
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor (private val repository: MovieRepository) : ViewModel() {

    val films : LiveData<List<Films>> = repository.films.asLiveData()

    val isLoading = MutableLiveData(false)

    init {
        fetchMovies()
    }

    fun fetchMovies() {
        viewModelScope.launch(Dispatchers.IO){
            isLoading.postValue(true)
            repository.fetchMovies()
            isLoading.postValue(false)
        }
    }

    fun updateMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.postValue(true)
            repository.updateMovies()
            isLoading.postValue(false)
        }
    }
}

@BindingAdapter("bindImage")
fun loadMoviePoster( imageView: ImageView?, movieUrl : String?){
    imageView?.let {
        Picasso.get().load(movieUrl).into(imageView)
    }
}

@BindingAdapter("bindMovieName")
fun loadMovieName(textView: TextView?, movieName : String?){
    textView?.let {
        it.text = movieName
    }
}

@BindingAdapter("bindMovieRating")
fun loadMovieRating(textView: TextView?, movieRating : String?){
    textView?.let {
        val rating = movieRating?.toFloat() ?: 0f
        if (rating >= 7f){
            it.setTextColor(Color.GREEN)
        } else if (rating < 7 && rating >= 4) {
            it.setTextColor(Color.YELLOW)
        } else {
            it.setTextColor(Color.RED)
        }

        it.text = movieRating
    }
}