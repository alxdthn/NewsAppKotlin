package com.alxdthn.newstinkoff.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsEntity (
	@PrimaryKey var id: Int,
	var name: String,
	var text: String,
	var publicationDate: Long,
	var bankInfoTypeId: Int
)