/**
 * index.js
 *
 * Entry point for the March Madness Prediction API.
 * Sets up the Express server, middleware, and routes.
 */

import express from 'express';
import bodyParser from 'body-parser';
import predictRoute from './routes/predict.js';

const app = express();
const PORT = 3000;

// Middleware to parse incoming JSON requests
app.use(bodyParser.json());

// Health check route (confirms server is running)
app.get('/', (req, res) => {
  res.send('March Madness API running!');
});

// Mount prediction route at /predict
// Example: POST http://localhost:3000/predict
app.use('/predict', predictRoute);

// Start the server
app.listen(PORT, () => {
  console.log(`Server running on http://localhost:${PORT}`);
});