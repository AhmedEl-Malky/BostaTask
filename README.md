# 🎮 Bosta Games Explorer

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Compose](https://img.shields.io/badge/Jetpack_Compose-4285F4?style=for-the-badge&logo=android&logoColor=white)
![Clean Architecture](https://img.shields.io/badge/Clean_Architecture-MVI-orange?style=for-the-badge)

A high-performance Android application built for the **Bosta Technical Assessment**. Designed to
explore video games using the **RAWG API** with a focus on clean, scalable architecture and a
premium user experience.

---

## 📱 Screenshots

| Browse Games | Search (In-Memory) | Shimmer Loading |
|:---:|:---:|:---:|
| <img width="260" alt="Browse Games" src="https://github.com/user-attachments/assets/3abbb5c0-a464-40e4-9c13-c872bffc5145" /> | <img width="260" alt="Search for Game" src="https://github.com/user-attachments/assets/fb81ab90-1483-47f3-85a5-778e89626e8c" /> | <img width="260" alt="Shimmer Loading" src="https://github.com/user-attachments/assets/c01243c2-f81a-417c-af43-14b72dfddb1c" /> |

| Game Hero | Game Details | Screenshots  |
|:---:|:---:|:---:|
| <img width="260" alt="Game Hero" src="https://github.com/user-attachments/assets/42c86278-4bfd-4165-9938-27995b143b58" /> | <img width="260" alt="Game Details" src="https://github.com/user-attachments/assets/458508f9-f064-47a6-bb2b-74dc08461b3e" /> | <img width="260" alt="Game ScreenShot" src="https://github.com/user-attachments/assets/ce51c3df-b471-405a-9232-147b3433b557" /> |

| Errors | Empty Search Results |
|:---:|:---:|
| <img width="260" alt="Network Error" src="https://github.com/user-attachments/assets/5a56fdfd-5c31-4f68-a385-e67e4250ef29" /> | <img width="260" alt="No Results" src="https://github.com/user-attachments/assets/24cc9b96-42f0-44d7-8949-9a3171450713" /> |


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
   git clone https://github.com/AhmedEl-Malky/BostaTask.git
   cd BostaTask
   ```
2. **API Key Configuration 🔑:**
    - Register at [RAWG.io](https://rawg.io/apidocs).
    - Create/Update `local.properties` in the root and add:
      ```properties
      API_KEY="your_rawg_api_key"
      ```
3. **Build:** Sync Gradle and launch on an emulator or device.

> **📥 Download APK:** [Ready-to-launch APK](https://bit.ly/4uuDnpP)

---

<div align="center">
    <p>Made with ❤️ by <b>Ahmed Malky</b> for Bosta Team</p>
</div>
