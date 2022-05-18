package my.movieproject.view

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val frameLayout : FrameLayout by lazy {
        FrameLayout(this).apply { id = View.generateViewId() + 1 }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(frameLayout)

        supportFragmentManager.beginTransaction()
            .replace(frameLayout.id,
                MovieFragment(),
                MovieFragment::class.java.simpleName)
            .addToBackStack(null)
            .commit()
    }

}