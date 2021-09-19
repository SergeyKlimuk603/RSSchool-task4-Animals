package by.klimuk.workingwithstorage

import android.app.Application
import androidx.room.Room
import by.klimuk.workingwithstorage.db.RoomAppDatabase
import by.klimuk.workingwithstorage.ulilites.ROOM_DATABASE

class App : Application() {

    var roomDatabase: RoomAppDatabase? = null
    private set

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        roomDatabase = Room.databaseBuilder(this, RoomAppDatabase::class.java, ROOM_DATABASE)
            .allowMainThreadQueries()
            .build()
    }

    companion object {
        lateinit var instance: App
    }
}