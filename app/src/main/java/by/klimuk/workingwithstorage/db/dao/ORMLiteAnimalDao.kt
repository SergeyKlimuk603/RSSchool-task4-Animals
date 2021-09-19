package by.klimuk.workingwithstorage.db.dao

import by.klimuk.workingwithstorage.db.ORMLiteDatabaseHelper
import by.klimuk.workingwithstorage.interfaces.AnimalDao
import by.klimuk.workingwithstorage.models.Animal

class ORMLiteAnimalDao : AnimalDao {

    private val dao = ORMLiteDatabaseHelper.getDao(Animal::class.java)

    override fun create(animal: Animal) {
        dao.createOrUpdate(animal)
    }

    override fun update(animal: Animal) {
        dao.update(animal)
    }

    override fun delete(animal: Animal) {
        dao.delete(animal)
    }

    override fun queryForAll(): List<Animal> {
        return dao.queryForAll()
    }

    override fun close() {}
}