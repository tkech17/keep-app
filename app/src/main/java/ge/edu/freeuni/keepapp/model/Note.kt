package ge.edu.freeuni.keepapp.model

import java.util.*
import kotlin.collections.ArrayList


data class Note(

    var id: Int = 0,

    val title: String?,

    var currentTasks: List<String> = ArrayList(),
    var checkedTasks: List<String> = ArrayList(),

    var createTime: Date? = null,

    var lastUpdateTime: Date? = null,

    var pinned: Boolean

)