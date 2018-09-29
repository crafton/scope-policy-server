package com.crafton.scopepolicyserver.models

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class Application(
        @Id val id: String?,
        @Indexed(unique = true) var name: String?,
        var description: String?,
        var baseUrl: String?,
        @DBRef var policies: MutableSet<Policy>?,
        @CreatedDate val dateCreated: LocalDateTime?,
        @LastModifiedDate val lastUpdated: LocalDateTime?
)