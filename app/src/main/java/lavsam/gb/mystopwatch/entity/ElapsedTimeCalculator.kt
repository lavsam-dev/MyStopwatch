package lavsam.gb.mystopwatch.entity

import lavsam.gb.mystopwatch.model.data.AppState
import lavsam.gb.mystopwatch.model.data.TimestampProvider

class ElapsedTimeCalculator(
    private val timestampProvider: TimestampProvider,
) {

    fun calculate(state: AppState.Running): Long {
        val currentTimestamp = timestampProvider.getMilliseconds()
        val timePassedSinceStart = if (currentTimestamp > state.startTime) {
            currentTimestamp - state.startTime
        } else {
            0
        }
        return timePassedSinceStart + state.elapsedTime
    }
}