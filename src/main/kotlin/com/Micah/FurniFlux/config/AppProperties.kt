package com.Micah.FurniFlux.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "app")
data class AppProperties @ConstructorBinding constructor(
    val jwt: JwtProperties,
    val cloudinary: CloudinaryProperties
)

data class JwtProperties(
    val secret: String,
    val expirationMs: Long
)

data class CloudinaryProperties(
    val cloudName: String,
    val apiKey: String,
    val apiSecret: String
)