# Islam360 Clone - BCSf21M022

## Project Overview

The **Islam360 Clone** is a mobile application designed to replicate the key features of the original Islam360 app. It offers users access to essential Islamic resources, including the **Quran**, **Ahadith**, **Duas**, and a **Qibla direction** finder. This project primarily focuses on UI/UX with plans to implement complete functionality in future iterations. The goal is to create an intuitive and user-friendly interface for spiritual guidance.

---

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-repo/islam360-clone.git
   cd islam360-clone```
## Setup Instructions

Follow these steps to set up and run the project:

1. Open the Project in Android Studio:
   - Launch **Android Studio**.
   - From the welcome screen, select **Open**.
   - Navigate to the root directory of the cloned project and select it.

2. Build and Run the Project:
   - Sync the Gradle files and install any missing dependencies.
   - Once the project is synced, click **Run** or press `Shift + F10` to launch the application on an emulator or physical device.

### Dependencies:
- **Kotlin**
- **View Binding** for UI components
- **Android SDK** version 30+

---

## Screens and Their Purpose

1. **Splash Screen (MainActivity)**
   - **Purpose**: A welcoming screen that introduces users to the app and automatically redirects to the Direction screen after a brief delay.

2. **Direction Screen (DirectionActivity)**
   - **Purpose**: Displays a Qibla compass and prompts users to grant location access for calculating accurate prayer times and Qibla direction.
   - **Note**: Users can allow location permission or skip this step.

3. **Home Screen (HomeActivity)**
   - **Purpose**: The main hub where users can access functionalities like **Quran**, **Hadith**, **Duas**, and more. The layout is currently simple, with plans for future feature expansion.

4. **Login Screen**
   - **Purpose**: Provides users with options to log in via **Facebook** or **Gmail** and showcases the app’s features. User authentication will be fully implemented in future versions.

5. **Quran Screen (Fragment)**
   - **Purpose**: Allows users to browse the Quran, with features like **Surah cards** and **Ayat cards** to organize and view verses. Future updates will include search and bookmark functionality.

6. **Hadith Screen (Fragment)**
   - **Purpose**: Displays collections of Hadiths organized in an accessible format. This screen is designed to allow future addition of search and bookmarking features.

7. **Tasbih Screen (Fragment)**
   - **Purpose**: A digital tasbih counter to help users keep track of their zikr (remembrance) with options to increment and reset the count.

8. **Prayer Time Screen (Fragment)**
   - **Purpose**: Displays daily prayer times for Fajr, Dhuhr, Asr, Maghrib, and Isha, as well as supplementary information like **Sunrise**, **Zawal**, and **Sunset** times.

9. **Shahada Screen (Fragment)**
   - **Purpose**: Presents the Shahada (declaration of faith) in Arabic along with an English translation for users to read and reflect on.

10. **About Us Screen (Fragment)**
    - **Purpose**: Provides information about the application’s purpose, vision, and future updates.

11. **More Screen (Fragment)**
    - **Purpose**: A section where users can find additional resources and options within the app.

### Navigation Bar and Drawer
The app includes a **bottom navigation bar** and a **navigation drawer** for seamless navigation:
- **Bottom Navigation Bar**: Provides quick access to main features, such as **Home**, **Quran**, **Ibadat**, and **More**.
- **Navigation Drawer**: Allows access to additional sections like **About Us**, as well as the **Home**, **Quran**, **Ibadat**, and **More** pages.

---

## Technical Challenges

1. **UI/UX Design**
   - Crafting an aesthetically pleasing and functional UI was essential to this project. Key focus areas included ensuring consistent alignment, responsiveness across various screen sizes, and maintaining readability.

2. **View Binding and Navigation**
   - Implementing View Binding for smoother view management and navigating between different activities and fragments was initially challenging. Ensuring seamless transitions and proper lifecycle handling required careful management.

3. **System UI Insets Management**
   - Handling system UI elements like the status bar and navigation bars using `WindowInsetsCompat` was complex, especially for edge-to-edge layouts, requiring additional padding management to achieve a visually appealing interface.

---

## Future Plans

1. **Implement Full Functionality**
   - **Quran and Hadith Browsing**: Add search and bookmark features.
   - **Dua Collections**: Provide a comprehensive dua library for users.
   - **Qibla Direction**: Integrate real-time location updates for precise Qibla direction.

2. **Settings and Customization**
   - **Language Options**: Support multiple languages for broader accessibility.
   - **App Themes**: Offer theme options for user preference.
   - **Notification Preferences**: Allow users to enable reminders for prayer times, daily duas, and hadith.

3. **Notifications**
   - Add notifications for **prayer times**, **Islamic dates**, and reminders for daily duas and hadith.

4. **Authentication and User Profiles**
   - Support login through social platforms with personal bookmarks and progress tracking for reading Quran and Hadith.

---

## Conclusion

This project is an ongoing effort to create a fully-featured Islamic resource application, balancing usability with a rich array of features. The current phase emphasizes design and navigation, with further functionality to be added in future updates.

