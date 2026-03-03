# March Madness Predictor Web Application

<a name="readme-top"></a>

## Overview

**March Madness Predictor** is a full‑stack web application that predicts NCAA basketball game outcomes based on statistical inputs and a trained machine learning model exported to JavaScript. It combines a **frontend UI**, **backend** in Kotlin, and a **model API** calling a JS model file that was originally trained using Python.

This project demonstrates how to integrate typed backend APIs with a JavaScript‑based model and a dynamic user interface.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Frontend → Backend → Model Flow

1. **Frontend**  
   - Serves the user interface for submitting game stats.  
   - Collects user input (team stats, past performance, etc.) through HTML forms. 
   - Sends a **POST** request with JSON to the backend endpoint `/predict` (within the Spring Boot app).

2. **Backend (Kotlin + Spring Boot)**  
   - Receives the prediction request from the frontend.  
   - Converts JSON into a `GameRequest` object.  
   - Uses `PredictionService` to communicate with the Node.js prediction API running in `march-madness-api` at `http://localhost:3000/predict`.  
   - Receives the prediction response (`PredictionResponse`) from Node.js and returns it to the frontend.

3. **Node.js Prediction API (`march-madness-api/predict.js`)**  
   - Exposes a `/predict` endpoint that accepts feature data from the Kotlin backend.  
   - Calls `model.js`, which contains the Python-trained Random Tree model logic translated to JavaScript.  
   - Returns prediction results (winner and scores) to the Kotlin backend as JSON.

4. **Model (`model.js`)**  
   - Implements the trained Random Tree model in JavaScript.  
   - Accepts feature arrays and returns prediction results.  
   - Runs entirely in Node.js — no Python runtime required.

**Flow Summary:**

[Frontend (Spring Boot UI)] → POST JSON → [Kotlin Backend /predict] → POST JSON → [Node.js API → model.js] → JSON → [Backend] → JSON → [Frontend UI]


**Key Notes:**

- Frontend only handles the UI and user interaction.  
- Kotlin backend abstracts API communication and converts request/response objects.  
- Node.js handles ML inference using the JS model, keeping model logic separate.  
- This design keeps responsibilities clear and allows the Python-trained model to run fully in JavaScript.


<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Requirements

To run the project locally you need:

- **Node.js & npm** (for frontend and model API)
- **Kotlin + Java 17+** (for backend)
- **Gradle** (for building and running backend)

## Getting Started

1. Clone the repository:

```bash
git clone https://github.com/mollyoconnorr/MarchMadnessPredicter-KotlinWebApp.git
cd MarchMadnessPredicter-KotlinWebApp
```

2. Install frontend dependencies and start the frontend server:

``` bash
cd march-madness-api
npm install
npm run dev # Starts the Node.js model API on http://localhost:3000
```

3. In a separate terminal window, start the backend server:
   
```bash
cd MarchMadnessPredicter-KotlinWebApp
./gradlew bootRun
```

4. Open your browser and navigate to:
```bash
http://localhost:8080/
```
You should now see the March Madness Predictor interface.

## Folder Structure 

```bash
MarchMadnessPredicter-KotlinWebApp/
├── src/                        # Kotlin backend
│   ├── main/kotlin/             # Controllers, services, models
│   │   └── PredictionService.kt
│   └── resources/               # Templates, static assets for UI
├── march-madness-api/           # Node.js prediction API
│   ├── model.js                 # Python-trained Random Tree in JS
│   ├── predict.js               # Express API endpoint for predictions
│   ├── package.json
│   └── package-lock.json
├── build.gradle
├── gradlew
└── README.md
```

## Referneces 
Node.js Express – https://expressjs.com/

ChatGPT – Assisted with css
