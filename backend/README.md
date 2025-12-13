# GymBuddy Backend

Spring Boot REST API backend for the GymBuddy iOS application.

## Tech Stack

- **Java 17**
- **Spring Boot 3.2.0**
- **PostgreSQL** (Database)
- **Spring Security** (Authentication & Authorization)
- **JWT** (JSON Web Tokens for stateless authentication)
- **Spring Data JPA** (Database ORM)

## Setup Instructions

### Prerequisites

1. **Java 17** or higher
2. **Maven 3.6+**
3. **PostgreSQL** installed and running

### Database Setup

```bash
# Create database
createdb gymbuddy

# Or using psql
psql -U postgres
CREATE DATABASE gymbuddy;
```

### Running the Application

```bash
# Navigate to backend directory
cd backend

# Install dependencies and build
mvn clean install

# Run the application
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

### Configuration

Edit `src/main/resources/application.properties` to configure:
- Database connection (default: localhost:5432/gymbuddy)
- JWT secret key (change in production!)
- Server port (default: 8080)

## Project Structure

```
backend/
├── src/
│   ├── main/
│   │   ├── java/com/gymbuddy/
│   │   │   ├── controller/     # REST API endpoints
│   │   │   ├── service/        # Business logic
│   │   │   ├── repository/     # Data access layer
│   │   │   ├── model/          # Entity models
│   │   │   ├── config/         # Configuration classes
│   │   │   ├── security/       # Security configuration
│   │   │   └── GymBuddyApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/gymbuddy/   # Test files
└── pom.xml
```

## API Endpoints (To be implemented)

### Authentication
- `POST /api/auth/register` - User registration
- `POST /api/auth/login` - User login

### Workouts
- `POST /api/workouts` - Create workout session
- `POST /api/workouts/{id}/complete` - Complete workout session
- `GET /api/workouts` - List user's workouts

### Buddies
- `GET /api/buddies` - List unlocked/locked buddies
- `GET /api/buddy-slots` - Get buddy slots information

