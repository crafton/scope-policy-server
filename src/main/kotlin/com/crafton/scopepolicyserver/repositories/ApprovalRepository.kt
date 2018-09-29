package com.crafton.scopepolicyserver.repositories

import com.crafton.scopepolicyserver.models.Approval
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface ApprovalRepository : ReactiveMongoRepository<Approval, String>