package lavsam.gb.mystopwatch.model.data

interface TimestampProvider {
    fun getMilliseconds(): Long
}