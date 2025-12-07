# android-login-signup-clean-mvi-demo
Android Login/Signup Demo — Kotlin | Compose | MVI | Clean Architecture

This project is a modern Android architecture demo built using:

* Jetpack Compose
* Kotlin
* Dagger-Hilt
* MVI + Clean Architecture
* Retrofit API Integration
* Custom BaseScreen Setup (Preview-friendly architecture)

### 🚀 Why BaseScreen is important?

Compose previews break when screens depend on:
* ViewModels
* Business logic
* Navigation arguments
  
I built a BaseScreen pattern to make every screen previewable by injecting fake params & fake viewmodels.
This helps in faster UI development + isolated UI testing.

### 📌 Features

* Login & Signup UI
* API integration
* MVI state handling
* Clean layered architecture

### 🛠 Tech Stack

* Kotlin, Compose, Dagger-Hilt
* Retrofit
* Coroutines + Flow
* Material 3
* MVI + UseCases

### ▶️ Screenrecord

<video width="100%" controls>
  <source src="screenrecord/mvidemo.webm" type="video/webm">
  Your browser does not support HTML video.
</video>

### 📦 How to Run
```bash
git clone https://github.com/NaimishTrivedi/android-login-signup-clean-mvi-demo.git
open in Android Studio
Build → Run
```
---
## 🔄 Related Backend Project (Ktor)

This Android demo app is fully connected with my Ktor backend, which handles the Login & Signup API.
If you want to explore the complete flow (client + server), check the backend repo:

### 👉 Ktor Backend Repo:
https://github.com/NaimishTrivedi/ktor-auth-backend-clean-architecture-demo

### 📌 Backend Highlights

* Kotlin + Ktor
* Clean Architecture
* Koin Dependency Injection
* SQLite database
* /login and /signup POST APIs
* Lightweight, easy-to-run demo server

This helps understand the end-to-end authentication architecture between Android + Ktor.

---
<div align="center">
⭐ If you like this project, consider giving it a star!
</div>
