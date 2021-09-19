package by.klimuk.workingwithstorage.db

import androidx.room.Database
import androidx.room.RoomDatabase
import by.klimuk.workingwithstorage.interfaces.AnimalRoomDao
import by.klimuk.workingwithstorage.models.Animal

@Database(entities = [Animal::class], version = 1)
abstract class RoomAppDatabase : RoomDatabase() {
    abstract fun animalRoomDao(): AnimalRoomDao
}