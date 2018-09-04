package com.crafton.scopepolicyserver.repositories

import com.crafton.scopepolicyserver.models.Policy
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface PolicyRepository : ReactiveMongoRepository<Policy, String>