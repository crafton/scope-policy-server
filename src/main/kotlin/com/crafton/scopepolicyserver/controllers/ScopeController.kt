package com.crafton.scopepolicyserver.controllers

import com.crafton.scopepolicyserver.repositories.ScopeRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ScopeController(private val scopeRepository: ScopeRepository) {

    @GetMapping("/scopes")
    fun findAllScopes() = scopeRepository.findAll()

    @GetMapping("/scopes/{id}")
    fun findScope(@PathVariable("id") scopeId: String) = scopeRepository.findById(scopeId)
            .map { ResponseEntity.ok(it) }
            .defaultIfEmpty(ResponseEntity.notFound().build())

}