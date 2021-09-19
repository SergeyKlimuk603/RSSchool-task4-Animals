package by.klimuk.workingwithstorage.interfaces

import by.klimuk.workingwithstorage.models.Animal

interface AnimalsFragmentListener {
    fun runSettings()
    fun createAnimal(animal: Animal? = null)
}