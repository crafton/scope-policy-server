package com.crafton.scopepolicyserver.models

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class Scope(
        @Id val id: String?,
        var name: String,
        var description: String,
        @CreatedDate val dateCreated: LocalDateTime?,
        @LastModifiedDate val lastUpdated: LocalDateTime?
)