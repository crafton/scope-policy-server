package com.crafton.scopepolicyserver.models

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class Approval (
        @Id val id: String?,
        val application: Application,
        val policy: Policy,
        val approved: Boolean,
        val approvedBy: String,
        @CreatedDate val dateCreated: LocalDateTime?,
        @LastModifiedDate val lastUpdated: LocalDateTime?
)