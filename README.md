# Moodify: Mood-Based Song Recommendation Android App 🎶

Moodify is an innovative Android application that uses OpenAI’s API to analyze a user's mood and recommend personalized song . The app seamlessly integrates YouTube for music playback, ensuring an immersive and responsive experience. Built with Java, XML, and Android Studio, Moodify adjusts to user emotions and provides the perfect soundtrack to enhance well-being at any given moment.

## Features

- **Mood-Based Music Recommendation**: Analyzes users' mood using the OpenAI API and recommends personalized playlists based on the detected emotional state.
- **YouTube Integration**: Users can enjoy in-app music playback, directly streaming songs from YouTube, without leaving the app.
- **Optimized Performance**: Fast load times, reduced crashes, and optimized API calls ensure a smooth and responsive experience.
- **Intuitive UI**: Sleek, modern interface with easy navigation, designed using XML and Android Studio for a polished user experience.
- **Dynamic Song Suggestions**: The app adapts to different moods and continuously provides relevant song recommendations as the user's emotional state changes.

## Installation

### Prerequisites

To run the app, you need to have the following installed:

- Android Studio
- JDK 8 or later
- Android SDK

### Steps to Set Up

1. **Clone the Repository**:

    ```bash
    git clone https://github.com/yourusername/moodify.git
    ```

2. **Open the Project in Android Studio**:
    - Launch Android Studio.
    - Select “Open an existing project” and navigate to the folder where you cloned the repository.

3. **Install Dependencies**: The project relies on the OpenAI API and YouTube player integration. Make sure you have the necessary keys and dependencies set up:
    - Add your OpenAI API key in `strings.xml`.
    - Integrate YouTube Player API https://github.com/PierfrancescoSoffritti/android-youtube-player

4. **Run the App**:
    - Once the setup is complete, click the "Run" button in Android Studio to launch the app on an emulator or connected device.

## Usage

1. **Launch the App**: Upon opening the app, users are prompted to input or allow access to their mood data (using OpenAI’s sentiment analysis).
2. **Music Playback**: Based on the mood detected, the app will generate a playlist and start streaming songs from YouTube directly within the app interface.
3. **Mood Changes**: As the user’s emotional state evolves, the app will adapt the music recommendations in real-time.



## Optimizations

- **Performance Enhancements**: Reduced app load time by 15% through efficient API calls and background processing.
- **Bug Fixes**: Addressed crashes and UI responsiveness issues, improving the user experience by 20%.
- **Error Handling**: Implemented robust error handling for API calls and YouTube player integration.

## Contributing

Contributions are welcome! If you'd like to enhance Moodify or suggest features, feel free to fork the repository and submit a pull request. Please ensure any changes align with the project's goals and follow the code conventions used.


## Acknowledgments

- **OpenAI** for providing the API to analyze mood.
- **YouTube API** for allowing in-app music playback.(https://github.com/PierfrancescoSoffritti/android-youtube-player)
- The **Android Developer Community** for their contributions to mobile app development.

## Contact

For any questions or feedback, feel free to contact me at [theabhishekjaiswal12@gmail.com].
