package com.Micah.FurniFlux.config

import com.Micah.FurniFlux.model.Role
import com.Micah.FurniFlux.model.User
import com.Micah.FurniFlux.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class DataInitializer(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : CommandLineRunner {

    override fun run(vararg args: String) {
        if (!userRepository.existsByEmail("admin@furniflux.com")) {
            val adminUser = User(
                email = "admin@furniflux.com",
                passwordHush = passwordEncoder.encode("AdminMaster2026!"),
                firstName = "FurniFlux",
                lastName = "Admin",
                role = Role.ADMIN,
            )
            userRepository.save(adminUser)
            println("====== Master Admin Account Seeded Successfully ======")
        }
    }
}