package com.crafton.scopepolicyserver.controllers

import com.crafton.scopepolicyserver.models.Application
import com.crafton.scopepolicyserver.models.Policy
import com.crafton.scopepolicyserver.repositories.ApplicationRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
class ApplicationController(private val applicationRepository: ApplicationRepository) {

    @GetMapping("/applications")
    fun getAllApplications() = applicationRepository.findAll()

    @GetMapping("/applications/{id}")
    fun getApplication(@PathVariable("id") applicationId: String) = applicationRepository.findById(applicationId)
            .map { ResponseEntity.ok(it) }
            .defaultIfEmpty(ResponseEntity.notFound().build())

    @PostMapping("/applications")
    fun createApplication(@RequestBody application: Application) = applicationRepository.save(application)

    @PutMapping("/applications/{id}")
    fun updateApplication(@PathVariable("id") applicationId: String, @RequestBody application: Application) = applicationRepository.findById(applicationId)
            .flatMap { existingApp ->
                existingApp.name = application.name
                existingApp.baseUrl = application.baseUrl
                existingApp.policies = application.policies
                applicationRepository.save(existingApp)
            }.map { ResponseEntity.ok(it) }
            .defaultIfEmpty(ResponseEntity.notFound().build())

    @PatchMapping("/applications/{id}/policy")
    fun addPolicy(@PathVariable("id") applicationId: String, @RequestBody policy: Policy) = applicationRepository.findById(applicationId)
            .flatMap { existingApp ->
                existingApp.policies?.add(policy)
                applicationRepository.save(existingApp)
            }.map { ResponseEntity.ok(it) }
            .defaultIfEmpty(ResponseEntity.notFound().build())

    @DeleteMapping("/applications/{id}")
    fun deletePolicy(@PathVariable("id") applicationId: String) = applicationRepository.findById(applicationId)
            .flatMap { existingApp ->
                applicationRepository.delete(existingApp)
                        .then(Mono.just(ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
            }
            .defaultIfEmpty(ResponseEntity.notFound().build())
}