package com.tisto.simpleapp.core.source.local.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.REPLACE
import com.tisto.simpleapp.core.source.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = REPLACE)
    fun insert(data: UserEntity)

    @Insert(onConflict = REPLACE)
    fun insert(data: List<UserEntity>)

    @Delete
    fun delete(data: UserEntity)

    @Update
    fun update(data: UserEntity)

    @Update
    fun update(data: List<UserEntity>)

    @Query("SELECT * from UserEntity ORDER BY id ASC")
    fun getAll(): Flow<List<UserEntity>>

    @Query("SELECT * from UserEntity WHERE email = :email  LIMIT 1")
    fun getByEmail(email: String): Flow<UserEntity>

    @Query(
        "SELECT * FROM UserEntity WHERE (:search IS NULL OR name LIKE '%' || :search || '%' " +
                "OR email LIKE '%' || :search || '%') ORDER BY id DESC"
    )
    fun getAll(search: String?): Flow<List<UserEntity>>

    @Query("DELETE FROM UserEntity")
    fun clearAll()
}