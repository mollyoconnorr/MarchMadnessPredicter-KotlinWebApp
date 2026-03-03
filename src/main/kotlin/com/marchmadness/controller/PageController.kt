package com.marchmadness.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

/**
 * PageController
 * Page Controller handles routing for all main pages of the site.
 * Each method returns the name of a Thymeleaf template to render.
 */
@Controller
class PageController {

    // Home Page
    // URL: /
    // Template: index.html
    // Description: Landing page of the site
    @GetMapping("/")
    fun home(): String = "index"

    // Predictor Page
    // URL: /predict
    // Template: predictor.html
    // Description: Users input team stats to get a predicted winner
    @GetMapping("/predict")
    fun predictForm(): String = "predictor"

    // Report Page
    // URL: /report
    // Template: report.html
    // Description: Displays generated reports
    @GetMapping("/report")
    fun report(): String = "report"

    // Stats Page
    // URL: /stats
    // Template: stats.html
    // Description: Explains different stats and their meanings
    @GetMapping("/stats")
    fun stats(): String = "stats"
}