package com.crafton.scopepolicyserver.models

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class Policy(
        @Id val id: String,
        val path: String,
        val scopes: List<Scope>,
        val httpActions: List<HttpAction>,
        @CreatedDate val dateCreated: LocalDateTime,
        @LastModifiedDate val lastUpdated: LocalDateTime
        )