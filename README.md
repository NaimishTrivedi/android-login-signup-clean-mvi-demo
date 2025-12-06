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

▶️ Screenshots

### 📦 How to Run
```bash
git clone https://github.com/NaimishTrivedi/android-login-signup-clean-mvi-demo.git
open in Android Studio
Build → Run
```
