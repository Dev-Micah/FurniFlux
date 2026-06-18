package com.Micah.FurniFlux.service

import com.Micah.FurniFlux.dto.AuthResponse
import com.Micah.FurniFlux.dto.LoginRequest
import com.Micah.FurniFlux.dto.SignUpRequest
import com.Micah.FurniFlux.exception.ResourceAlreadyExistsException
import com.Micah.FurniFlux.exception.UnauthorizedException
import com.Micah.FurniFlux.model.Role
import com.Micah.FurniFlux.model.User
import com.Micah.FurniFlux.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService
){

    fun signUp(request: SignUpRequest): AuthResponse {
        if (userRepository.existsByEmail(request.email)){
            throw ResourceAlreadyExistsException("An email address already exists")
        }
        val newUser = User(
            email = request.email.lowercase().trim(),
            passwordHush = passwordEncoder.encode(request.password),
            firstName = request.firstName.trim(),
            lastName = request.lastName.trim(),
            role = Role.CUSTOMER,
        )

        val savedUser = userRepository.save(newUser)

        val token = jwtService.generateToken(savedUser)

        return  AuthResponse(
            token = token,
            email = savedUser.email,
            firstName = savedUser.firstName,
            lastName = savedUser.lastName,
            role = savedUser.role
        )
    }

    fun signIn(request: LoginRequest): AuthResponse {
        val user= userRepository.findByEmail(request.email.lowercase().trim())
            .orElseThrow(){ UnauthorizedException("Invalid email or password.") }

        if (!passwordEncoder.matches(request.password, user.passwordHush)){
            throw UnauthorizedException("Invalid email or password.")
        }


        val token = jwtService.generateToken(user)

        return AuthResponse(
            token = token,
            email = user.email,
            firstName = user.firstName,
            lastName = user.lastName,
            role = user.role
        )
    }
}