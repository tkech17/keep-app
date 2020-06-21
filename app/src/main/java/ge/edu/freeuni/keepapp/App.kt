package ge.edu.freeuni.keepapp

import android.app.Application
import ge.edu.freeuni.keepapp.manager.NotesManager
import ge.edu.freeuni.keepapp.manager.NotesManagerImpl

class App : Application() {

    companion object {
        val notesManager: NotesManager = NotesManagerImpl()
    }


    override fun onCreate() {
        super.onCreate()
    }

}