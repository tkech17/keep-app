package ge.edu.freeuni.keepapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ge.edu.freeuni.keepapp.helpers.converters.DateConverter
import ge.edu.freeuni.keepapp.helpers.converters.StringListTypeConverter
import ge.edu.freeuni.keepapp.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 2, exportSchema = false)
@TypeConverters(value = [StringListTypeConverter::class, DateConverter::class])
abstract class NoteDatabase : RoomDatabase() {

    abstract fun getNoteDao(): NoteDAO

    companion object {

        @Volatile
        private var instance: NoteDatabase? = null

        fun getInstance(context: Context): NoteDatabase {
            instance?.let {
                synchronized(this) {
                    instance?.let {
                        instance = build(context)
                    }
                }
            }
            return instance!!
        }

        private fun build(context: Context): NoteDatabase {
            return Room.databaseBuilder(context, NoteDatabase::class.java, "NoteDatabase").fallbackToDestructiveMigration().build()
        }
    }

}
