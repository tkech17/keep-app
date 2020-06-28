package ge.edu.freeuni.keepapp

import android.app.Application
import ge.edu.freeuni.keepapp.server.manager.NotesManager
import ge.edu.freeuni.keepapp.server.manager.NotesManagerImpl

class App : Application() {

    companion object {
        lateinit var notesManager: NotesManager
    }

    override fun onCreate() {
        super.onCreate()
    }

}