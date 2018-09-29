package com.crafton.scopepolicyserver.controllers

import com.crafton.scopepolicyserver.services.ApplicationApprovalService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ApprovalController(private val applicationApprovalService: ApplicationApprovalService) {

    @GetMapping("/approvals")
    fun getAllApprovals() = applicationApprovalService.findAllApprovals()

    @GetMapping("/approvals/applications/{id}")
    fun getApprovalsForApplication(@PathVariable("id") applicationId: String) = applicationApprovalService.findApprovalsByApplicationId(applicationId)
            .map { ResponseEntity.ok(it) }
            .defaultIfEmpty(ResponseEntity.notFound().build())


}