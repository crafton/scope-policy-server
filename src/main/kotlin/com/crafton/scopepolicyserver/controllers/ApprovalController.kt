package com.crafton.scopepolicyserver.controllers

import com.crafton.scopepolicyserver.models.Approval
import com.crafton.scopepolicyserver.repositories.ApplicationRepository
import com.crafton.scopepolicyserver.repositories.ApprovalRepository
import org.springframework.data.domain.Example
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ApprovalController(private val approvalRepository: ApprovalRepository, private val applicationRepository: ApplicationRepository) {

    @GetMapping("/approvals")
    fun getAllApprovals() = approvalRepository.findAll()

    @GetMapping("/approvals/applications/{id}")
    fun getApprovalsForApplication(@PathVariable("id") applicationId: String) = applicationRepository.findById(applicationId)
            .flatMap { existingApp ->
                approvalRepository.findAll(
                        Example.of(Approval(null, existingApp, null, null, null, null, null)))
                        .collectList()
            }.map { ResponseEntity.ok(it) }
            .defaultIfEmpty(ResponseEntity.notFound().build())


}