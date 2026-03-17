# 🎮 Bosta Games Explorer

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Compose](https://img.shields.io/badge/Jetpack_Compose-4285F4?style=for-the-badge&logo=android&logoColor=white)
![Clean Architecture](https://img.shields.io/badge/Clean_Architecture-MVVM-orange?style=for-the-badge)

A high-performance Android application built for the **Bosta Technical Assessment**. Designed to
explore video games using the **RAWG API** with a focus on clean, scalable architecture and a
premium user experience.

---

## 📱 Screenshots

| Browse Games | Search (In-Memory) | Shimmer Loading |
|:---:|:---:|:---:|
| <img width="260" src="images/Browse Games.png" /> | <img width="260" src="images/Search for Game.png" /> | <img width="260" src="images/Shimmer Loading.png" /> |

| Game Hero | Game Details | Screenshots Pager |
|:---:|:---:|:---:|
| <img width="260" src="images/Game Hero.png" /> | <img width="260" src="images/Game Details.png" /> | <img width="260" src="images/Game ScreenShot.png" /> |

---

## ✨ Project Features

* **🎮 Dynamic Discovery:** Browse games via paginated lists with responsive genre filtering.
* **🔍 Instant Local Search:** Filter loaded games in-memory, ensuring zero-latency results and zero
  redundant API calls.
* **⚡ Offline-First Architecture:** Powered by **Room Database**. Your browsing history and game
  details are cached locally, ensuring seamless operation even without an internet connection.
* **🎨 Material 3 Design:** A modern, polished UI built with Jetpack Compose featuring custom shimmer
  effects and smooth transitions.
* **🚦 Robust State Handling:** Proactive management of Loading, Error (with retry), and Empty states
  to ensure a frustration-free UX.

---

## 🛠 Tech Stack

| Category | Technology |
| :--- | :--- |
| **Language** | Kotlin |
| **UI Framework** | Jetpack Compose (Material 3) |
| **Architecture** | Clean Architecture (MVVM) |
| **DI** | Dagger Hilt |
| **Networking** | Retrofit 2 + Kotlinx Serialization |
| **Local Database** | Room |
| **Async** | Coroutines & Flow |
| **Pagination** | Paging 3 |
| **Image Loading** | Coil 3 |

---

## 🏗️ Project Structure

This project follows a clean architectural approach, separating the codebase into three main layers:

* **`domain`**: The heart of the app. Contains pure business logic, models, and repository
  interfaces.
* **`data`**: Implements the repositories. Handles data orchestration between Remote (API) and
  Local (Room) sources.
* **`presentation`**: Houses the UI (Composables) and State Management (ViewModels), keeping the UI
  reactive and state-driven.

---

## ⚙️ Setup & Installation

1. **Clone the Repo:**
   ```bash
   git clone <repository-url>
   cd BostaTask
   ```
2. **API Key Configuration 🔑:**
    - Register at [RAWG.io](https://rawg.io/apidocs).
    - Create/Update `local.properties` in the root and add:
      ```properties
      API_KEY="your_rawg_api_key"
      ```
3. **Build:** Sync Gradle and launch on an emulator or device.

> **📥 Download APK:** [Ready-to-launch APK](#)

---

<div align="center">
    <p>Made with ❤️ by <b>Ahmed Malky</b> for Bosta Team</p>
</div>
