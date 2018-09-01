package com.crafton.scopepolicyserver.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Scope(
        @Id val id: String,
        val name: String,
        val description: String)