package amitapps.media.data.room

import amitapps.media.domain.model.Blog
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Blog::class, BlogKey::class], version = 1, exportSchema = false)
@TypeConverters(RoomTypeConverter::class, ListOfStringToStringTypeConverter::class)
abstract class BlogDataBase : RoomDatabase(){

    companion object {
        fun getInstance(context: Context) : BlogDataBase {
            return Room.databaseBuilder(context, BlogDataBase::class.java,"blog").build()
        }
    }

    abstract fun getBlogDao(): BlogDao
}