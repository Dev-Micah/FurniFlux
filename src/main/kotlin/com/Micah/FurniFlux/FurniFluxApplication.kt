package com.Micah.FurniFlux

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class FurniFluxApplication

fun main(args: Array<String>) {
	runApplication<FurniFluxApplication>(*args)
}
