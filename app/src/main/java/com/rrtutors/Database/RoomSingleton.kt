package com.rrtutors.Database
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.TypeConverters
import com.rrtutors.Converter.Converters
import com.rrtutors.Person
import com.rrtutors.PersonDao


@Database(entities = [Person::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class RoomSingleton : RoomDatabase(){
    abstract fun personDao(): PersonDao

    companion object{
        private var INSTANCE: RoomSingleton? = null
        fun getInstance(context:Context): RoomSingleton {
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    RoomSingleton::class.java,
                    "person_db")
                    .build()
            }

            return INSTANCE as RoomSingleton
        }
    }
}