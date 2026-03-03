package com.marchmadness.controller

import com.marchmadness.dto.GameRequest
import com.marchmadness.service.PredictionService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

/**
 * PredictionController
 * Handles form submissions from the predictor page.
 * Receives team stats via POST request, calls the PredictionService,
 * and returns the predicted winner to the Thymeleaf template.
 */
@Controller
class PredictionController(private val predictionService: PredictionService) {

    /*
     * Handle POST requests to /predict
     * Receives all team stats as request parameters,
     * builds a GameRequest object, and retrieves the predicted winner.
     */
    @PostMapping("/predict")
    fun handlePredict(
        @RequestParam SEED_A: Int,        // Seed for Team A
        @RequestParam AdjEM_A: Double,    // Adjusted Efficiency Margin for Team A
        @RequestParam AdjTempo_A: Double, // Adjusted Tempo for Team A
        @RequestParam FG2Pct_A: Double,   // 2-point FG % for Team A
        @RequestParam ORPct_A: Double,    // Offensive Rebound % for Team A
        @RequestParam TOPct_A: Double,    // Turnover % for Team A
        @RequestParam SEED_B: Int,        // Seed for Team B
        @RequestParam AdjEM_B: Double,    // Adjusted Efficiency Margin for Team B
        @RequestParam AdjTempo_B: Double, // Adjusted Tempo for Team B
        @RequestParam FG2Pct_B: Double,   // 2-point FG % for Team B
        @RequestParam ORPct_B: Double,    // Offensive Rebound % for Team B
        @RequestParam TOPct_B: Double,    // Turnover % for Team B
        model: Model                       // Spring MVC Model to pass data to the view
    ): String {
        // Create GameRequest object from submitted form values
        val request = GameRequest(
            teamA = "Team A",
            teamB = "Team B",
            featuresA = listOf(SEED_A.toDouble(), AdjEM_A, AdjTempo_A, FG2Pct_A, ORPct_A, TOPct_A),
            featuresB = listOf(SEED_B.toDouble(), AdjEM_B, AdjTempo_B, FG2Pct_B, ORPct_B, TOPct_B)
        )

        // Call the PredictionService to get the winner
        val prediction = predictionService.getPrediction(request)

        // Add the winner to the model so Thymeleaf can display it
        model.addAttribute("winner", prediction?.winner ?: "Error")

        // Return the predictor template (predictor.html)
        return "predictor"
    }
}