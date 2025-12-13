# GymBuddy

A gamified workout companion iOS app where an animated buddy does workouts with the user, rewarding consistency and difficulty by unlocking new buddies and buddy slots.

## Project Overview

GymBuddy is a focused, gamified workout timer with a companion character and simple progression system. Users select workout types, intensity, and duration, then watch their buddy perform the workout animation while a timer counts down.

## Tech Stack

### Backend
- **Java 17** with **Spring Boot 3.2.0**
- **PostgreSQL** database
- **Spring Security** + **JWT** for authentication
- **Spring Data JPA** for database operations
- **REST API** over HTTPS with JSON

### iOS App
- **Swift 5.9+** with **SwiftUI**
- Native frameworks only (no external dependencies)
- **URLSession** for networking
- **Keychain Services** for secure token storage

## Project Structure

```
GymBuddy/
├── backend/              # Spring Boot REST API
│   ├── src/
│   │   ├── main/java/   # Backend source code
│   │   └── main/resources/ # Configuration files
│   └── pom.xml            # Maven dependencies
├── ios-app/              # iOS Swift application
│   └── Package.swift     # Swift package configuration
└── README.md
```

## Quick Start

### Backend Setup

1. **Prerequisites:**
   - Java 17+
   - Maven 3.6+
   - PostgreSQL

2. **Database Setup:**
   ```bash
   createdb gymbuddy
   ```

3. **Run Backend:**
   ```bash
   cd backend
   mvn clean install
   mvn spring-boot:run
   ```
   API available at `http://localhost:8080`

### iOS App Setup

1. **Prerequisites:**
   - Xcode 15+
   - macOS

2. **Create Xcode Project:**
   - Open Xcode
   - Create new iOS App project
   - Choose SwiftUI interface
   - Save in `ios-app/` directory

## Features (MVP)

### Core Functionality
- ✅ User authentication (email/password)
- ✅ Workout type selection (legs, back, chest, arms)
- ✅ Intensity selection (low, medium, hard, maxed)
- ✅ Duration selection (30s, 1m, 2m, 5m)
- ✅ Buddy animations during workouts
- ✅ Progress tracking and unlock system

### Progression System
- **Intensity-based unlocks:** Unlock buddy slots by completing workouts at different intensity levels
- **Duration-based unlocks:** Unlock new buddies by completing workouts of different durations

## Development Status

🚧 **Setup Complete** - Project structure and dependencies configured. Ready for implementation.

## Documentation

- [Backend README](backend/README.md) - Backend setup and API documentation
- [iOS App README](ios-app/README.md) - iOS app setup and structure
