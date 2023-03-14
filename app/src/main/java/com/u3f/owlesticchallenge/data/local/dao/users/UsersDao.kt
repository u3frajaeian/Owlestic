package com.u3f.owlesticchallenge.data.local.dao.users

import androidx.room.*
import com.u3f.owlesticchallenge.domain.model.profile.ProfileModel

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFav(repo: ProfileModel): Long

    //    @Delete
//    suspend fun deleteGroup()
    @Query("SELECT * FROM tbl_profile")
    fun getAllFavUsers(): List<ProfileModel>

    @Delete
    fun deleteFav(user: ProfileModel)


}