package com.Micah.FurniFlux.exception

import java.time.LocalDateTime

data class ErrorResponse(
    val status: Int,
    val error: String,
    val message: String?,
    val path: StringBuffer?,
    val timestamp: LocalDateTime = LocalDateTime.now()
)