# Bus Routes Project

The "Bus Routes" project is a demo application that showcases the use of MVVM architecture, Clean Architecture principles, network calls with Retrofit, Hilt for dependency injection, and periodic API calls to fetch bus route data. The application stores the network data in a Room database and displays it in the user interface.

## Features

- **MVVM Architecture**: The project is structured using the Model-View-ViewModel architectural pattern, promoting separation of concerns and maintainability.
- **Clean Architecture**: The project follows Clean Architecture principles to ensure modularity, scalability, and a clear separation of layers.
- **Network Calls**: The application makes network requests to a bus route API using Retrofit, allowing the retrieval of route information.
- **Dependency Injection**: Hilt is used for dependency injection, making it easy to provide and inject dependencies throughout the app.
- **Periodic API Calls**: The app is designed to call the bus route API every minute, ensuring the data is up-to-date.
- **Local Data Storage**: Data fetched from the API is stored in a Room database, enabling efficient offline access and data persistence.

## Getting Started

Follow the steps below to set up the project locally and run it on your Android device or emulator:

1. Clone the repository:

   ```bash
   git clone https://github.com/PanktiSP13/BusRouteDemo.git
   ```

2. Open the project in Android Studio.
3. Build and run the project on an Android device or emulator.
4. Explore the application's user interface and features.

## Dependencies

The project relies on the following key dependencies:

- [Retrofit](https://square.github.io/retrofit/): For making network requests.
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android): For dependency injection.
- [Room](https://developer.android.com/training/data-storage/room): For local data storage.

---
