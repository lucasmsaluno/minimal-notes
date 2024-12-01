package com.example.minimalnotes

import android.app.Application
import androidx.room.Room
import com.example.minimalnotes.data.NoteDatabase

class MainApplication: Application() {
    companion object {
        lateinit var todoDatabase: NoteDatabase
    }

    override fun onCreate() {
        super.onCreate()
        todoDatabase = Room.databaseBuilder(
            applicationContext,
            NoteDatabase::class.java,
            NoteDatabase.NAME
        ).fallbackToDestructiveMigration().build()
    }
}