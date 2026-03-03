package com.marchmadness.dto

/**
 * GameRequest
 *
 * Data Transfer Object (DTO) representing the payload
 * received when requesting a game prediction.
 *
 * Contains team identifiers and their corresponding
 * feature vectors used by the prediction model.
 */
data class GameRequest(

    // Name or identifier for Team A
    val teamA: String,

    // Name or identifier for Team B
    val teamB: String,

    // Feature vector representing Team A's statistical inputs
    val featuresA: List<Double>,

    // Feature vector representing Team B's statistical inputs
    val featuresB: List<Double>
)