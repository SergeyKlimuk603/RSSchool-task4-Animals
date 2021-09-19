package by.klimuk.workingwithstorage.interfaces

import by.klimuk.workingwithstorage.models.Animal

interface AnimalDao {
    fun create(animal: Animal)
    fun update(animal: Animal)
    fun delete(animal: Animal)
    fun queryForAll(): List<Animal>
    fun close()
}