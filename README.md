# Islam360

Islam360 is an Android application designed to provide comprehensive Islamic resources, including the Quran by Surah, translations, and other religious content. The app features a user-friendly interface with dynamic functionalities like searching and viewing Quranic content.

---

## Features

### Core Functionalities
- **Quran by Surah**
  - Displays all Surahs from the Quran.
  - Includes information like Surah name (Urdu and English), number of verses, and type (Makki/Madni).
  - Search functionality for quick access to Surahs.
  - Differentiated icons for Makki (Makka) and Madni (Madina) Surahs.

- **Surah Details**
  - Shows Arabic text, translations (Urdu and English), and metadata for each Ayat.
  - Allows users to toggle between Urdu and English translations.

- **User Authentication**
  - Firebase-based login and signup.
  - Option to save login credentials for automatic login.

### Additional Features
- **Dynamic RecyclerView**
  - Custom layout for displaying Surah details.
  - Card-based design for an intuitive look and feel.

- **SQLite Database Integration**
  - Fetch Surah and Ayat data dynamically.
  - Optimized queries for performance.

---

## Tech Stack

- **Languages:** Kotlin
- **Database:** SQLite
- **Authentication:** Firebase Authentication
- **UI Framework:** ConstraintLayout, RecyclerView, and CardView
- **Tools:** Android Studio

---

## Project Structure

```
Islam360
├── Adapters
│   └── SurahAdapter.kt
├── dataAccess
│   └── DbHelper.kt
├── models
│   └── SurahModel.kt
│   └── tayah.kt
├── Activities
│   ├── Login.kt
│   ├── BySurah.kt
│   ├── AyatActivity.kt
│   └── Seerat_un_Nabi.kt
├── Fragments
│   └── QuranFragment.kt
├── Layouts
│   ├── activity_login.xml
│   ├── activity_by_surah.xml
│   ├── activity_ayat.xml
│   ├── fragment_quran.xml
│   └── surah_list_item.xml
└── Resources
    ├── drawable
    ├── values
    └── fonts
```

---

## Installation

1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-repo/Islam360.git
   ```

2. **Open in Android Studio**
   - Open Android Studio and select "Open an Existing Project."

3. **Add the Database**
   - Place the `QuranDb.db` file in the `assets` folder of the project.

4. **Configure Firebase**
   - Add your Firebase project `google-services.json` file in the `app` directory.

5. **Build and Run**
   - Build the project and run it on an Android device/emulator.

---

## Usage

1. **Login/Signup**
   - Register or log in with Firebase Authentication.

2. **Explore Surahs**
   - Navigate to "Quran by Surah" to view all Surahs.
   - Use the search bar to find specific Surahs.

3. **View Ayat Details**
   - Click on a Surah to see its Ayat.
   - Toggle between Urdu and English translations.

---

## Contributing

Contributions are welcome! If you want to contribute:
1. Fork the repository.
2. Create a feature branch.
3. Commit your changes and submit a pull request.

---

