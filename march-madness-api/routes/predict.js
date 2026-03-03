/**
 * predict.js
 *
 * This router handles prediction requests for the March Madness Predictor.
 *
 * Endpoint:
 *   POST /
 *
 * Expects a JSON body containing:
 *   - teamA (string)
 *   - teamB (string)
 *   - featuresA (array<number>)
 *   - featuresB (array<number>)
 *
 * It uses the exported `score()` function from model.js
 * to compute prediction scores for each team and returns
 * the predicted winner along with numeric scores.
 */

import express from 'express';
import { score } from '../model/model.js';

const router = express.Router();

/**
 * POST /
 * Processes team statistics and returns predicted winner.
 */
router.post('/', (req, res) => {
    const { teamA, teamB, featuresA, featuresB } = req.body;

    // Validate required fields
    if (!teamA || !teamB || !featuresA || !featuresB) {
        return res.status(400).json({
            error: 'teamA, teamB, featuresA, and featuresB are required'
        });
    }

    try {
        // Run model inference for both teams
        const scoreA = score(featuresA);
        const scoreB = score(featuresB);

        // Extract numeric probability (handles array or scalar return)
        const numericScoreA = Array.isArray(scoreA) ? scoreA[1] : scoreA;
        const numericScoreB = Array.isArray(scoreB) ? scoreB[1] : scoreB;

        // Determine predicted winner
        const winner = numericScoreA > numericScoreB ? teamA : teamB;

        // Return JSON response
        res.json({
            numericScoreA,
            numericScoreB,
            winner
        });

    } catch (err) {
        console.error('Prediction error:', err);
        res.status(500).json({ error: 'Prediction failed' });
    }
});

export default router;