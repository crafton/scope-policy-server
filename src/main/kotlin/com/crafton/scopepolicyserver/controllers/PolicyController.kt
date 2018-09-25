package com.crafton.scopepolicyserver.controllers

import com.crafton.scopepolicyserver.models.Policy
import com.crafton.scopepolicyserver.repositories.PolicyRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class PolicyController(private val policyRepository: PolicyRepository) {

    @GetMapping("/policies")
    fun findAllPolicies() = policyRepository.findAll()

    @GetMapping("/policies/{id}")
    fun findPolicy(@PathVariable("id") policyId: String) = policyRepository.findById(policyId)
            .map { ResponseEntity.ok(it) }
            .defaultIfEmpty(ResponseEntity.notFound().build())

    @PostMapping("/policies")
    fun createPolicy(@RequestBody policy: Policy) = policyRepository.save(policy)
}