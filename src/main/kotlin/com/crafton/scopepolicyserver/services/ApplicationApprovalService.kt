package com.crafton.scopepolicyserver.services

import com.crafton.scopepolicyserver.models.Application
import com.crafton.scopepolicyserver.models.Approval
import com.crafton.scopepolicyserver.repositories.ApplicationRepository
import com.crafton.scopepolicyserver.repositories.ApprovalRepository
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service

@Service
class ApplicationApprovalService(private val applicationRepository: ApplicationRepository, private val approvalRepository: ApprovalRepository) {

    fun findAllApplications() = applicationRepository.findAll()

    fun findApplicationById(id: String) = applicationRepository.findById(id)

    fun upsertApplication(application: Application) = applicationRepository.save(application)

    fun removeApplication(application: Application) = applicationRepository.delete(application)

    fun findAllApprovals() = approvalRepository.findAll()

    fun findApprovalsByApplicationId(applicationId: String) = applicationRepository.findById(applicationId)
            .flatMap { existingApp ->
                approvalRepository.findAll(Example.of(Approval(null, existingApp, null, null, null, null, null)))
                        .collectList()
            }
}