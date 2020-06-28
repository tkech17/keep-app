package ge.edu.freeuni.keepapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.util.*
import kotlin.collections.ArrayList

@Entity(tableName = "Note")
data class NoteEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String?,

    val currentTasks: ArrayList<String> = ArrayList(),
    val checkedTasks: ArrayList<String> = ArrayList(),

    val createTime: Date?,

    val lastUpdateTime: Date?,

    val pinned: Boolean

)