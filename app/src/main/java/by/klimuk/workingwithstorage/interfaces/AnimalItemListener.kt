package by.klimuk.workingwithstorage.interfaces

import by.klimuk.workingwithstorage.models.Animal

interface AnimalItemListener {
    fun delete(animal: Animal)
    fun edit(animal: Animal)
}