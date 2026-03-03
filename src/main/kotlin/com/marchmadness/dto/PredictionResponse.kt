package com.marchmadness.dto

/**
 * PredictionResponse
 *
 * Data Transfer Object (DTO) representing the prediction result
 * returned to the client after model evaluation.
 *
 * Contains the predicted numeric scores for both teams
 * and the model-determined winner.
 */
data class PredictionResponse(

    // Predicted numeric score for Team A
    val numericScoreA: Double,

    // Predicted numeric score for Team B
    val numericScoreB: Double,

    // Name or identifier of the predicted winning team
    val winner: String
)