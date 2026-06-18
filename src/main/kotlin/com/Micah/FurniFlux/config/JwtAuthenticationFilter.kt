package com.Micah.FurniFlux.config

import com.Micah.FurniFlux.repository.UserRepository
import com.Micah.FurniFlux.service.JwtService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter (
    private val jwtService: JwtService,
    private val userRepository: UserRepository
): OncePerRequestFilter(){

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader(HttpHeaders.AUTHORIZATION)

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response)
            return
        }

        val token = authHeader.substring(7)
        val email = jwtService.validateTokenAndGetSubject(token)

        if (email !=  null && SecurityContextHolder.getContext().authentication == null) {
            userRepository.findByEmail(email).ifPresent { user ->
                val authority = SimpleGrantedAuthority("ROLE_${user.role.name}")
                val authToken = UsernamePasswordAuthenticationToken(
                    user, null, listOf(authority)
                )

                authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authToken

            }
        }

        filterChain.doFilter(request, response)

    }

}