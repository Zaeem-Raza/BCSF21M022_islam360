# Islam360 Clone - BCSf21M022

## Project Overview

The **Islam360 Clone** is a mobile application designed to replicate the key features of the original Islam360 app. It offers users access to essential Islamic resources, including the **Quran**, **Ahadith**, **Duas**, and a **Qibla direction** finder. This version primarily focuses on the UI/UX aspect, with plans to integrate complete functionality in the future. The goal is to create an intuitive and user-friendly interface for spiritual guidance.

---

## Setup Instructions

Follow these steps to set up and run the project:

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-repo/islam360-clone.git
   cd islam360-clone

Open the Project in Android Studio:

Launch Android Studio.
Open the cloned project from the root directory.
Build and Run the Project:

Sync the Gradle files and install any missing dependencies.
Click on Run or press Shift+F10 to launch the application on an emulator or physical device.
Dependencies:

The project uses Kotlin and View Binding for its UI components.
Ensure you have installed Android SDK version 30+.
Gradle Plugin: Version 4.0+.
Screens and Their Purpose
Splash Screen (MainActivity):

Purpose: A transition screen that introduces users to the app and automatically redirects them to the direction page after a brief delay.
Direction Screen (DirectionActivity):

Purpose: Displays a Qibla compass and prompts users to grant location access for calculating accurate prayer times and qibla direction. Users can either "Allow" the location permission or skip this step.
Home Screen (HomeActivity):

Purpose: This screen will serve as the main hub for accessing different functionalities like Quran, Hadith, Duas, and more. The layout is currently simple, with plans to expand it in the future.
Login Screen:

Purpose: Provides users with options to log in via Facebook or Gmail, along with a display of the app's features. Future implementations will involve handling user authentication.
Technical Challenges
UI/UX Design:

Creating an aesthetically pleasing yet functional UI was a significant challenge. Achieving consistent alignment, ensuring responsiveness across different screen sizes, and maintaining readability were the primary focus areas.
View Binding & Navigation:

Implementing View Binding for smoother view management and setting up navigation between different activities was an initial challenge. Ensuring seamless transitions and correct lifecycle handling required careful management.
Managing System UI Insets:

Handling system UI elements like the status bar and navigation bars using WindowInsetsCompat was complex, especially for edge-to-edge layouts, which required additional padding management for a visually appealing interface.
Future Plans
Implement Full Functionality:

We plan to implement Quran and Hadith browsing, including search and bookmark features, alongside full Dua collections. The Qibla direction feature will also integrate real-time location updates for precision.
Settings and Customization:

Future iterations will include Settings for language options, app themes, and notification preferences for prayer times and important events.
Notifications:

Add notifications for prayer times, Islamic dates, and reminders for daily dua and hadith.
Authentication & User Profiles:

Support user login through social platforms, with personal bookmarks and progress tracking for reading Quran or Hadith.
Conclusion
This project is an ongoing effort to create a fully-featured Islamic resource application, combining usability with a rich array of features for users. The current phase emphasizes design and initial navigation, with more features to come in future updates.
