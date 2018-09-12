package com.crafton.scopepolicyserver.models

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class Application(
        @Id val id: String,
        val name: String,
        val description: String,
        val baseUrl: String,
        val policies: List<Policy>,
        @CreatedDate val dateCreated: LocalDateTime,
        @LastModifiedDate val lastUpdated: LocalDateTime )