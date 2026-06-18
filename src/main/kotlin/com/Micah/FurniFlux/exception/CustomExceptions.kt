package com.Micah.FurniFlux.exception

class ResourceNotFoundException(message: String) : RuntimeException(message)

class ResourceAlreadyExistsException(message: String) : RuntimeException(message)

class UnauthorizedException(message: String) : RuntimeException(message)