package com.crafton.scopepolicyserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@EnableReactiveMongoRepositories
@SpringBootApplication
class ScopePolicyServerApplication

fun main(args: Array<String>) {
    runApplication<ScopePolicyServerApplication>(*args)
}
