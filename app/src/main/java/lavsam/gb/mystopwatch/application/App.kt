package lavsam.gb.mystopwatch.application

import android.app.Application
import lavsam.gb.mystopwatch.di.mainActivityViewModel
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(mainActivityViewModel)
        }
    }
}