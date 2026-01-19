# GymBuddy

A gamified workout companion app where an animated buddy performs workouts with you, rewarding consistency and progress by unlocking new buddies and achievements.

## Tech Stack

### Backend
- **Java 17** with **Spring Boot 3.2.0**
- **H2 Database** (in-memory)
- **Spring Data JPA** for database operations
- **REST API** with JSON responses
- **Maven** for dependency management

### Frontend
- **HTML/CSS/JavaScript** (vanilla)
- **Lucide Icons** (CDN)
- **Capacitor** for iOS integration

## Project Structure

```
GymBuddy/
├── backend/              # Spring Boot REST API
│   ├── src/
│   │   ├── main/java/    # Backend source code
│   │   └── main/resources/ # Configuration files
│   └── pom.xml           # Maven dependencies
├── frontend/             # Frontend web assets
│   ├── static/           # HTML, CSS, JavaScript files
│   └── public/           # Images, GIFs, and static assets
├── ios/                  # iOS native app (Capacitor)
└── README.md
```

## Quick Start

### Prerequisites
- **Java 17** or higher
- **Maven 3.6+**

### Running the Application

1. Navigate to the backend directory:
   ```bash
   cd backend
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```

4. Access the application:
   - Open your browser and navigate to `http://localhost:8080`
   - Right-click and select "Inspect" to open DevTools, then choose iPhone dimensions from the device toolbar, or optionally check out "Xcode iOS Simulator" below

## Features

### Core Functionality
- Workout type selection (legs, back, chest, arms)
- Duration selection (30s, 1m, 2m, 3m)
- Custom workout templates with exercises and sets
- Animated buddy workouts with countdown timer
- Progress tracking through achievements (daily and lifetime)
- Gymbuddy collection system
- Shop to purchase new gymbuddies with coins
- Coin system earned from completing workouts

### Pages
- **Home** - Main hub with animated buddy
- **Workouts** - Create and manage custom workout templates
- **Train** - Select workout type and start training
- **Achievements** - View daily and lifetime achievements
- **Shop** - Purchase new gymbuddies
- **Collection** - View and manage your gymbuddy collection

## Development

This project is focused on mastering Spring Boot fundamentals, including:
- RESTful API design
- JPA entity relationships
- Service layer architecture
- Repository pattern
- Spring Boot configuration
- Testing with JUnit and Mockito

## Documentation

## View in iPhone Mode

### Optionally: Xcode iOS Simulator (macOS only)

**Complete Setup Instructions:**

1. **Start the backend** (if not already running):
   ```bash
   cd backend
   mvn spring-boot:run
   ```
   Keep this terminal window open - the backend must be running.

2. **In a new terminal at the same project level (`GymBuddy/`), run the Capacitor commands:**
   ```bash
   npx cap sync ios
   npx cap open ios
   ```
   This will open Xcode automatically.

3. **Install iOS Simulator** (if not already installed):
   - In Xcode, go to **Xcode > Settings** (or **Preferences**)
   - Click on **Platforms** tab
   - Install **iOS Simulator** if it's not already installed

4. **Select iPhone model**:
   - In Xcode, use the device selector dropdown at the top (next to the play button)
   - Choose an iPhone model (e.g., iPhone 15 Pro, iPhone 14)

5. **Run the app**:
   - Click the **Play** button (▶️) in Xcode or press `Cmd + R`
   - The app will launch in the iOS Simulator

**Note:** The backend must remain running in your terminal for the app to work.
