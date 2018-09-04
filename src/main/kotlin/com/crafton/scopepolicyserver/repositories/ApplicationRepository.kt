package com.crafton.scopepolicyserver.repositories

import com.crafton.scopepolicyserver.models.Application
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface ApplicationRepository : ReactiveMongoRepository<Application, String>