package lavsam.gb.mystopwatch.entity

import lavsam.gb.mystopwatch.model.data.AppState
import lavsam.gb.mystopwatch.model.data.TimestampProvider

class StopwatchStateCalculator(
    private val timestampProvider: TimestampProvider,
    private val elapsedTimeCalculator: ElapsedTimeCalculator,
) {

    fun calculateRunningState(oldState: AppState): AppState.Running =
        when (oldState) {
            is AppState.Running -> oldState
            is AppState.Paused -> {
                AppState.Running(
                    startTime = timestampProvider.getMilliseconds(),
                    elapsedTime = oldState.elapsedTime
                )
            }
        }

    fun calculatePausedState(oldState: AppState): AppState.Paused =
        when (oldState) {
            is AppState.Running -> {
                val elapsedTime = elapsedTimeCalculator.calculate(oldState)
                AppState.Paused(elapsedTime = elapsedTime)
            }
            is AppState.Paused -> oldState
        }
}