package com.Micah.FurniFlux.exception

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFoundException(
        e: ResourceNotFoundException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            status =HttpStatus.NOT_FOUND.value(),
            error ="Resource not found",
            message = e.message,
            path = request.requestURL
        )

        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(ResourceAlreadyExistsException::class)
    fun handleResourceAlreadyExistsException(
        e: ResourceAlreadyExistsException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            status =HttpStatus.CONFLICT.value(),
            error ="Resource already exists",
            message = e.message,
            path = request.requestURL
        )

        return ResponseEntity(error, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(UnauthorizedException::class)
    fun handleUnauthorizedException(
        e: UnauthorizedException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            status =HttpStatus.UNAUTHORIZED.value(),
            error ="Unauthorized Access",
            message = e.message,
            path = request.requestURL
        )

        return ResponseEntity(error, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(Exception::class)
    fun handleGeneralException(
        e: Exception,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            status =HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error ="Internal Server Error",
            message = e.message,
            path = request.requestURL
        )

        e.printStackTrace()
        return ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR)
    }



}