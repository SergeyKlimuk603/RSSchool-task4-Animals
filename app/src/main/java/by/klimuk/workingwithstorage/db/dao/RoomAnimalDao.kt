package by.klimuk.workingwithstorage.db.dao

import by.klimuk.workingwithstorage.App
import by.klimuk.workingwithstorage.interfaces.AnimalDao
import by.klimuk.workingwithstorage.models.Animal

class RoomAnimalDao : AnimalDao {

    private val dao = App.instance.roomDatabase?.animalRoomDao()

    override fun create(animal: Animal) {
        dao?.insert(animal)
    }

    override fun update(animal: Animal) {
        dao?.update(animal)
    }

    override fun delete(animal: Animal) {
        dao?.delete(animal)
    }

    override fun queryForAll(): List<Animal> {
        return dao?.getAll() ?: listOf<Animal>()
    }

    override fun close() {}
}