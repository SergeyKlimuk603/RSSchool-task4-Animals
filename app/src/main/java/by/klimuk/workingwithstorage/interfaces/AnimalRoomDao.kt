package by.klimuk.workingwithstorage.interfaces

import androidx.room.*
import by.klimuk.workingwithstorage.models.Animal
import by.klimuk.workingwithstorage.ulilites.TABLE_NAME

@Dao
interface AnimalRoomDao {

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAll(): List<Animal>

    @Insert
    fun insert(animal: Animal)

    @Update
    fun update(animal: Animal)

    @Delete
    fun delete(animal: Animal)
}