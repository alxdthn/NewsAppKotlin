package com.alxdthn.newstinkoff.data

import androidx.room.*

@Dao
interface NewsDao {
	@Query("SELECT * FROM NewsEntity")
	suspend fun getAll(): List<NewsEntity>

	@Query("SELECT * FROM NewsEntity WHERE id = :id")
	suspend fun getById(id: Long): NewsEntity

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(newsEntity: NewsEntity)

	@Update
	suspend fun update(newsEntity: NewsEntity)

	@Delete
	suspend fun delete(newsEntity: NewsEntity)
}