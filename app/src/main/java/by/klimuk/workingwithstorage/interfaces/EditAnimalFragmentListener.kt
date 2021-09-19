package by.klimuk.workingwithstorage.interfaces

import by.klimuk.workingwithstorage.models.Animal

interface EditAnimalFragmentListener {
    fun create(animal: Animal)
    fun update(animal: Animal)
}