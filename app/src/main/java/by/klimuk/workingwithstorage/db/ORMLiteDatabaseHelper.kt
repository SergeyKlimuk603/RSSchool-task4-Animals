package by.klimuk.workingwithstorage.db

import android.database.sqlite.SQLiteDatabase
import by.klimuk.workingwithstorage.App
import by.klimuk.workingwithstorage.models.Animal
import by.klimuk.workingwithstorage.ulilites.ORMLITE_DATABASE
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils

object ORMLiteDatabaseHelper : OrmLiteSqliteOpenHelper(
    App.instance,
    ORMLITE_DATABASE,
    null,
    1
) {

    override fun onCreate(database: SQLiteDatabase?, connectionSource: ConnectionSource?) {
        TableUtils.createTableIfNotExists(connectionSource, Animal::class.java)
    }

    override fun onUpgrade(
        database: SQLiteDatabase?,
        connectionSource: ConnectionSource?,
        oldVersion: Int,
        newVersion: Int
    ) {
        TableUtils.dropTable<Animal, Any>(connectionSource, Animal::class.java, true)
        onCreate(database, connectionSource)
    }
}