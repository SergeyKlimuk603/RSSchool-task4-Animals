package by.klimuk.workingwithstorage.models


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import java.io.Serializable

@Entity
@DatabaseTable(tableName = "animals")
data class Animal (

    @PrimaryKey
    @DatabaseField(generatedId = true)
    val id: Int? = null,

    @ColumnInfo
    @DatabaseField
    val name: String = "",

    @ColumnInfo
    @DatabaseField
    val age: Int? = null,

    @ColumnInfo
    @DatabaseField
    val breed: String = ""
    ) : Serializable

