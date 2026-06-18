package com.Micah.FurniFlux.service

import com.cloudinary.Cloudinary
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException

@Service
class CloudinaryService (
    private val cloudinaryClient: Cloudinary
){

    fun uploadImage(file: MultipartFile ,folderName: String = "furniflux/products"): String {

        if (file.isEmpty) {
            throw IllegalArgumentException("Cannot upload an empty file.")
        }

        try {
            val options = mapOf(
                "folder" to folderName,
                "resource_type" to "image"
            )

            val uploadResult = cloudinaryClient.uploader().upload(file.bytes, options)

            return uploadResult["secure_url"] as String
        }catch (ioException: IOException) {
            throw IllegalArgumentException("Failed to upload image .", ioException)
        }
    }
}