package com.crafton.scopepolicyserver.models

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class Policy(
        @Id val id: String?,
        @Indexed(unique = true) val name: String,
        var path: String,
        @DBRef var scopes: MutableSet<Scope>,
        var httpActions: MutableSet<HttpAction>,
        val createdBy: String?,
        @CreatedDate val dateCreated: LocalDateTime?,
        @LastModifiedDate val lastUpdated: LocalDateTime?
)