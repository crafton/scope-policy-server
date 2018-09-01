package com.crafton.scopepolicyserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ScopePolicyServerApplication

fun main(args: Array<String>) {
    runApplication<ScopePolicyServerApplication>(*args)
}
