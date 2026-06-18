package com.Micah.FurniFlux.service

import com.Micah.FurniFlux.config.AppProperties
import com.Micah.FurniFlux.model.User
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import org.springframework.stereotype.Service
import java.util.Date

@Service
class JwtService(appProperties: AppProperties) {

    private val jwtProps = appProperties.jwt
    private val algorithm: Algorithm = Algorithm.HMAC256(jwtProps.secret)

    fun generateToken(user: User): String {
        return JWT.create()
            .withSubject(user.email)
            .withClaim("role", user.role.name)
            .withClaim("firstName", user.firstName)
            .withIssuedAt(Date())
            .withExpiresAt(Date(System.currentTimeMillis() + jwtProps.expirationMs))
            .sign(algorithm)
    }

    fun validateTokenAndGetSubject(token: String): String? {
        return try {
            val verifier = JWT.require(algorithm).build()
            val decodedJWT: DecodedJWT = verifier.verify(token)
            decodedJWT.subject
        } catch (ex: Exception) {
            null // Token is expired, tampered with, or structurally malformed
        }
    }
}