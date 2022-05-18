package my.movieproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import my.movieproject.R
import my.movieproject.databinding.FilmFragmentLayoutBinding
import my.movieproject.view_model.MovieViewModel

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private val viewModel: MovieViewModel by lazy {
        ViewModelProvider(this)[MovieViewModel::class.java]
    }

    private lateinit var binding: FilmFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.film_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FilmFragmentLayoutBinding.bind(view)
        binding.movieModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.recyclerView.adapter = MovieAdapter()
        binding.recyclerView.setHasFixedSize(true)
    }
}