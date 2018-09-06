package com.crafton.scopepolicyserver.models

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class RecordNotFoundException(message: String?) : RuntimeException(message)