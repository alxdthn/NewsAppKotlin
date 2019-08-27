package com.alxdthn.newstinkoff.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsEntity (
	@PrimaryKey var id: Int,
	@ColumnInfo(name = "news id") var newsId: Int,
	@ColumnInfo(name = "name") var name: String,
	@ColumnInfo(name = "content") var text: String,
	@ColumnInfo(name = "publication date") var publicationDate: Long,
	@ColumnInfo(name = "bank info") var bankInfoTypeId: Int
)