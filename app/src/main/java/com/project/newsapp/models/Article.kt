package com.project.newsapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "articles"
)
data class Article(
    val author: String? = null,
    val content: String? = null,
    val description: String? = null,
    val publishedAt: String? = null,
    val source: Source ? = null,
    val title: String? = null,
    @PrimaryKey
    val url: String,
    val urlToImage: String? = null
) : Serializable


