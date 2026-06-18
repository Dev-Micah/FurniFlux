package com.Micah.FurniFlux.controller

import com.Micah.FurniFlux.dto.AuthResponse
import com.Micah.FurniFlux.dto.LoginRequest
import com.Micah.FurniFlux.dto.SignUpRequest
import com.Micah.FurniFlux.model.User
import com.Micah.FurniFlux.service.AuthService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/auth")
class AuthController(
    private val authService: AuthService,
){
    @PostMapping("/signup")
    fun signUp(@Valid @RequestBody request: SignUpRequest): ResponseEntity<AuthResponse> {
        val response =authService.signUp(request)

        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @PostMapping("/signin")
    fun signIn(@Valid @RequestBody request: LoginRequest): ResponseEntity<AuthResponse> {
        val response =authService.signIn(request)

        return ResponseEntity(response, HttpStatus.OK)
    }
}