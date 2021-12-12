package lavsam.gb.mystopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*
import lavsam.gb.mystopwatch.databinding.ActivityMainBinding
import lavsam.gb.mystopwatch.viewmodel.MainActivityViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel: MainActivityViewModel by inject()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainActivityViewModel.ticker.observe(this) {
            binding.textTime.text = it
        }

        with(binding) {
            buttonStart.setOnClickListener {
                mainActivityViewModel.stopwatchStart()
            }
            buttonPause.setOnClickListener {
                mainActivityViewModel.stopwatchPause()
            }
            buttonStop.setOnClickListener {
                mainActivityViewModel.stopwatchStop()
            }
        }
    }
}


