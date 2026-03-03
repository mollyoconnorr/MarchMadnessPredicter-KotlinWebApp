package com.marchmadness

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * MarchMadnessApplication
 *
 * Main entry point for the Spring Boot application.
 *
 * Enables configuration, component scanning, and
 * application bootstrapping for the March Madness backend.
 */
@SpringBootApplication
class MarchMadnessApplication

/**
 * Application startup function.
 *
 * Launches the Spring Boot application and initializes
 * the embedded server
 */
fun main(args: Array<String>) {
    runApplication<MarchMadnessApplication>(*args)
}