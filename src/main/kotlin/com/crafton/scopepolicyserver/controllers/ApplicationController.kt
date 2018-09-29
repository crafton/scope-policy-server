package com.crafton.scopepolicyserver.controllers

import com.crafton.scopepolicyserver.models.Application
import com.crafton.scopepolicyserver.models.Policy
import com.crafton.scopepolicyserver.services.ApplicationApprovalService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
class ApplicationController(private val applicationApprovalService: ApplicationApprovalService) {

    @GetMapping("/applications")
    fun getAllApplications() = applicationApprovalService.findAllApplications()

    @GetMapping("/applications/{id}")
    fun getApplication(@PathVariable("id") applicationId: String) = applicationApprovalService.findApplicationById(applicationId)
            .map { ResponseEntity.ok(it) }
            .defaultIfEmpty(ResponseEntity.notFound().build())

    @PostMapping("/applications")
    fun createApplication(@RequestBody application: Application) = applicationApprovalService.upsertApplication(application)

    @PutMapping("/applications/{id}")
    fun updateApplication(@PathVariable("id") applicationId: String, @RequestBody application: Application) = applicationApprovalService.findApplicationById(applicationId)
            .flatMap { existingApp ->
                existingApp.name = application.name
                existingApp.baseUrl = application.baseUrl
                existingApp.policies = application.policies
                applicationApprovalService.upsertApplication(existingApp)
            }.map { ResponseEntity.ok(it) }
            .defaultIfEmpty(ResponseEntity.notFound().build())

    @PatchMapping("/applications/{id}/policy")
    fun addPolicy(@PathVariable("id") applicationId: String, @RequestBody policy: Policy) = applicationApprovalService.findApplicationById(applicationId)
            .flatMap { existingApp ->
                existingApp.policies?.add(policy)
                applicationApprovalService.upsertApplication(existingApp)
            }.map { ResponseEntity.ok(it) }
            .defaultIfEmpty(ResponseEntity.notFound().build())

    @DeleteMapping("/applications/{id}")
    fun deletePolicy(@PathVariable("id") applicationId: String) = applicationApprovalService.findApplicationById(applicationId)
            .flatMap { existingApp ->
                applicationApprovalService.removeApplication(existingApp)
                        .then(Mono.just(ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
            }
            .defaultIfEmpty(ResponseEntity.notFound().build())
}