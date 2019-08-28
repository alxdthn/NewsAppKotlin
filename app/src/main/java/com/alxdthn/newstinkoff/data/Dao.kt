package com.alxdthn.newstinkoff.data

import androidx.room.*

@Dao
interface NewsDao {
	@Query("SELECT * FROM NewsEntity ORDER BY publicationDate DESC")
	fun getAll(): MutableList<NewsEntity>

	@Query("SELECT * FROM NewsEntity WHERE id = :id")
	fun getById(id: Long): NewsEntity

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(newsEntity: NewsEntity)

	@Update
	fun update(newsEntity: NewsEntity)

	@Delete
	fun delete(newsEntity: NewsEntity)
}