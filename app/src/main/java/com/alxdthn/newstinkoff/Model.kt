package com.alxdthn.newstinkoff

data class Date (
        val milliseconds: ULong
)

data class Payload (
         val id: Int,
         val name: String,
         val text: String,
         val publicationDate: Date,
         val bankInfoTypeId: Int
)

data class Post (
        val resultCode: String,
        val payload: ArrayList<Payload>,
        val trackingId: String
)

data class ContentPayload (
        val title: Payload,
        val creationDate: Date,
        val lastModificationDate: Date,
        val content: String,
        val bankInfoTypeId: Int,
        val typeId: String
)

data class Content (
        val resultCode: String,
        val payload: ContentPayload,
        val trackingId: String
)