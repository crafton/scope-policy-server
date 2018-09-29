package com.crafton.scopepolicyserver.controllers

import com.crafton.scopepolicyserver.models.Policy
import com.crafton.scopepolicyserver.models.Scope
import com.crafton.scopepolicyserver.repositories.PolicyRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
class PolicyController(private val policyRepository: PolicyRepository) {

    @GetMapping("/policies")
    fun findAllPolicies() = policyRepository.findAll()

    @GetMapping("/policies/{id}")
    fun findPolicy(@PathVariable("id") policyId: String)
            = policyRepository.findById(policyId)
            .map { ResponseEntity.ok(it) }
            .defaultIfEmpty(ResponseEntity.notFound().build())

    @PostMapping("/policies")
    fun createPolicy(@RequestBody policy: Policy) = policyRepository.save(policy)

    @PutMapping("/policies/{id}")
    fun updatePolicy(@PathVariable("id") policyId: String, @RequestBody policy: Policy)
            = policyRepository.findById(policyId)
            .flatMap { existingPolicy ->
                existingPolicy.path = policy.path
                existingPolicy.scopes = policy.scopes
                existingPolicy.httpActions = policy.httpActions
                policyRepository.save(existingPolicy)
            }.map { ResponseEntity.ok(it) }
            .defaultIfEmpty(ResponseEntity.notFound().build())

    @PatchMapping("/policies/{id}")
    fun addScopeToPolicy(@PathVariable("id") policyId: String, @RequestBody scope: Scope)
            = policyRepository.findById(policyId)
            .flatMap { existingPolicy ->
                existingPolicy.scopes.add(scope)
                policyRepository.save(existingPolicy)
            }.map { ResponseEntity.ok(it) }
            .defaultIfEmpty(ResponseEntity.notFound().build())

    @DeleteMapping("/policies/{id}")
    fun deletePolicy(@PathVariable("id") policyId: String)
            = policyRepository.findById(policyId)
            .flatMap { existingPolicy ->
                policyRepository.delete(existingPolicy)
                        .then(Mono.just(ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
            }.defaultIfEmpty(ResponseEntity.notFound().build())
}