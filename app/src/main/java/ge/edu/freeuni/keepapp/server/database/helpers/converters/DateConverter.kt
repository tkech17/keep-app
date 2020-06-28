package ge.edu.freeuni.keepapp.server.database.helpers.converters

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun toDate(timestamp: Long?) : Date? {
        timestamp?.let {
            return Date(it)
        }
        return null
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        date?.let {
            return it.time
        }
        return null
    }

}