package by.klimuk.workingwithstorage.db.dao

import android.content.ContentValues
import android.content.Context
import by.klimuk.workingwithstorage.db.SQLiteDatabaseOpenHelper
import by.klimuk.workingwithstorage.interfaces.AnimalDao
import by.klimuk.workingwithstorage.models.Animal
import by.klimuk.workingwithstorage.ulilites.*

class SQLiteAnimalDao(val context: Context) : AnimalDao {

    private val dbHelper = SQLiteDatabaseOpenHelper(context)
    private val db = dbHelper.writableDatabase

    override fun create(animal: Animal) {
        val contentValues = ContentValues()
        contentValues.apply {
            put(COLUMN_NAME, animal.name)
            put(COLUMN_AGE, animal.age)
            put(COLUMN_BREED, animal.breed)
        }
        db.insert(TABLE_NAME, null, contentValues)
    }

    override fun update(animal: Animal) {
        val contentValues = ContentValues()
        contentValues.apply {
            put(COLUMN_NAME, animal.name)
            put(COLUMN_AGE, animal.age)
            put(COLUMN_BREED, animal.breed)
        }
        db.update(TABLE_NAME, contentValues, "$COLUMN_ID = ${animal.id}", null)
        //dbHelper.close()
    }

    override fun delete(animal: Animal) {
        db.delete(TABLE_NAME, "$COLUMN_ID = ${animal.id}", null)
    }

    override fun queryForAll(): List<Animal> {
        val animals = mutableListOf<Animal>()
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        cursor.use {
            if (it.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                    val name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                    val age = cursor.getInt(cursor.getColumnIndex(COLUMN_AGE))
                    val breed = cursor.getString(cursor.getColumnIndex(COLUMN_BREED))
                    val animal = Animal(id, name, age, breed)
                    animals.add(animal)
                } while (cursor.moveToNext())
            }
        }
        return animals
    }

    override fun close() {
        dbHelper.close()
    }
}