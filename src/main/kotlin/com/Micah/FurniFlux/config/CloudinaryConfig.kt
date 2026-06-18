package com.Micah.FurniFlux.config

import com.cloudinary.Cloudinary
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CloudinaryConfig (
    private val appProperties: AppProperties
){
    @Bean
    fun cloudinaryClient(): Cloudinary {
        val configMap = mapOf(
            "cloud_name" to appProperties.cloudinary.cloudName,
            "api_key" to appProperties.cloudinary.apiKey,
            "api_secret" to appProperties.cloudinary.apiSecret
        )

        return Cloudinary(configMap)
    }
}