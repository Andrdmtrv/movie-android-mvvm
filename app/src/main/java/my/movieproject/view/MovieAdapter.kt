package my.movieproject.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import my.movieproject.databinding.ListFilmElementBinding
import my.movieproject.model.web.Films

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var data: List<Films> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Films>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            ListFilmElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class MovieViewHolder(private val binding: ListFilmElementBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(film: Films) {
            binding.film = film
        }
    }

}

@BindingAdapter("bindMovieAdapter")
fun bindMovieAdapter(recyclerView: RecyclerView, data: List<Films>?) {
    data?.let {
        (recyclerView.adapter as MovieAdapter).setData(data)
    }
}