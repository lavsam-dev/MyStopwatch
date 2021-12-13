package lavsam.gb.mystopwatch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import lavsam.gb.mystopwatch.entity.ElapsedTimeCalculator
import lavsam.gb.mystopwatch.entity.StopwatchListOrchestrator
import lavsam.gb.mystopwatch.entity.StopwatchStateCalculator
import lavsam.gb.mystopwatch.entity.StopwatchStateHolder
import lavsam.gb.mystopwatch.model.data.TimestampProvider
import lavsam.gb.mystopwatch.utils.TimestampMillisecondsFormatter

class MainActivityViewModel : ViewModel() {

    private val timestampProvider = object : TimestampProvider {
        override fun getMilliseconds(): Long {
            return System.currentTimeMillis()
        }
    }

    private val stopwatchListOrchestrator = StopwatchListOrchestrator(
        StopwatchStateHolder(
            StopwatchStateCalculator(
                timestampProvider,
                ElapsedTimeCalculator(timestampProvider)
            ),
            ElapsedTimeCalculator(timestampProvider),
            TimestampMillisecondsFormatter()
        ),
        CoroutineScope(
            Dispatchers.Main
                    + SupervisorJob()
        )
    )

    val ticker: LiveData<String> = stopwatchListOrchestrator.ticker.asLiveData()

    fun stopwatchStart() {
        stopwatchListOrchestrator.start()
    }

    fun stopwatchPause() {
        stopwatchListOrchestrator.pause()
    }

    fun stopwatchStop() {
        stopwatchListOrchestrator.stop()
    }
}