package by.klimuk.workingwithstorage.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import by.klimuk.workingwithstorage.ulilites.*

class SQLiteDatabaseOpenHelper(context: Context) : SQLiteOpenHelper(
    context,
    SQLITE_DATABASE,
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " $COLUMN_NAME TEXT, $COLUMN_AGE INTEGER, $COLUMN_BREED TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // TODO("Not yet implemented")
    }
}