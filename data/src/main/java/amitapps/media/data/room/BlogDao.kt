package amitapps.media.data.room

import amitapps.media.domain.model.Blog
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BlogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBlogs(list: List<Blog>)


    @Query("SELECT * FROM Blog")
    fun getAllBlogItems(): PagingSource<Int, Blog>


    @Query("DELETE FROM Blog")
    suspend fun deleteAllItems()


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBlogKeys(l: List<BlogKey>)

    @Query("DELETE FROM BlogKey")
    suspend fun deleteAllBlogKeys()

    @Query("SELECT * FROM BlogKey WHERE id=:id")
    suspend fun getAllKeys(id: String): BlogKey
}