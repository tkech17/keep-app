package ge.edu.freeuni.keepapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Note")
data class NoteEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String?,

    val content: String?,

    val createTime: Date,

    val lastUpdateTime: Date,

    val pinned: Boolean

)