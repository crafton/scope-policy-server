package com.crafton.scopepolicyserver.repositories

import com.crafton.scopepolicyserver.models.Scope
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface ScopeRepository : ReactiveMongoRepository<Scope, String>