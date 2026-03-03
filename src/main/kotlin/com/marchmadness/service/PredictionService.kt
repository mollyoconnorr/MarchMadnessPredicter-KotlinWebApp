package com.marchmadness.service

import com.marchmadness.dto.GameRequest
import com.marchmadness.dto.PredictionResponse
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

/**
 * PredictionService
 *
 * Service layer responsible for communicating with the external
 * Node.js prediction API. Sends game data to the ML inference
 * service and returns the structured prediction response.
 *
 * This layer abstracts API communication away from the controller,
 * maintaining a clean separation of concerns.
 */
@Service
class PredictionService {

    // WebClient configured to connect to the local Node.js API
    private val webClient = WebClient.create("http://localhost:3000")

    /**
     * Sends a prediction request to the Node.js backend and
     * returns the model's prediction response.
     */
    fun getPrediction(request: GameRequest): PredictionResponse? {

        return webClient.post()                // Send POST request
            .uri("/predict")                   // Target Node endpoint
            .bodyValue(request)                // Attach request payload
            .retrieve()                        // Execute request
            .bodyToMono(PredictionResponse::class.java) // Map response
            .block()                           // Convert reactive call to result
    }
}