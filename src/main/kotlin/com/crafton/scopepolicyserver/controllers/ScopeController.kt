package com.crafton.scopepolicyserver.controllers

import com.crafton.scopepolicyserver.models.Scope
import com.crafton.scopepolicyserver.repositories.ScopeRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
class ScopeController(private val scopeRepository: ScopeRepository) {

    @GetMapping("/scopes")
    fun findAllScopes() = scopeRepository.findAll()

    @GetMapping("/scopes/{id}")
    fun findScope(@PathVariable("id") scopeId: String) = scopeRepository.findById(scopeId)
            .map { ResponseEntity.ok(it) }
            .defaultIfEmpty(ResponseEntity.notFound().build())

    @PostMapping("/scopes")
    fun createScope(@RequestBody scope: Scope) = scopeRepository.save(scope)

    @PutMapping("/scopes/{id}")
    fun updateScope(@PathVariable("id") scopeId: String, @RequestBody scope: Scope) = scopeRepository.findById(scopeId)
            .flatMap { existingScope ->
                existingScope.name = scope.name
                existingScope.description = scope.description
                scopeRepository.save(existingScope)
            }.map { updatedScope -> ResponseEntity.ok(updatedScope) }
            .defaultIfEmpty(ResponseEntity.notFound().build())

    @DeleteMapping("/scopes/{id}")
    fun deleteScope(@PathVariable("id") scopeId: String) = scopeRepository.findById(scopeId)
            .flatMap { existingScope ->
                scopeRepository.delete(existingScope)
                        .then(Mono.just(ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
            }
            .defaultIfEmpty(ResponseEntity.notFound().build())

}