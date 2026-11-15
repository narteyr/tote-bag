package hu.ait.tote.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Item::class], version =  1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun itemDao(): ItemDao

    companion object {

        @Volatile
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            return Instance ?: synchronized(this) {

                Instance ?: Room.databaseBuilder(
                    context = context,
                    klass = AppDatabase::class.java,
                    name = "item_database"
                )
                    .fallbackToDestructiveMigration(true)
                    .build()
                    .also {Instance = it}
            }
        }
    }
}