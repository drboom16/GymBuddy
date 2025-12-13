# GymBuddy Dependencies Explanation

This document explains every dependency used in the GymBuddy project setup.

## Backend Dependencies (Spring Boot)

All backend dependencies are managed through Maven (`pom.xml`). When you run `mvn clean install`, Maven will automatically download all required dependencies and their transitive dependencies.

### Core Spring Boot Dependencies

#### 1. **spring-boot-starter-parent** (v3.2.0)
- **Type:** Parent POM (Project Object Model)
- **Purpose:** Provides dependency management and default configurations for all Spring Boot projects
- **What it includes:**
  - Version management for all Spring dependencies
  - Default Maven plugin configurations
  - Resource filtering
  - Property defaults
- **Why needed:** Ensures all Spring dependencies use compatible versions and provides sensible defaults

#### 2. **spring-boot-starter-web** (v3.2.0)
- **Type:** Starter dependency
- **Purpose:** Provides everything needed to build RESTful web services
- **What it includes:**
  - **Spring MVC** - Model-View-Controller framework for building web applications
  - **Embedded Tomcat** - Web server (runs on port 8080 by default)
  - **Jackson** - JSON serialization/deserialization library
  - **Spring Web** - Core web functionality
- **Why needed:** Enables the backend to receive HTTP requests, parse JSON, and send JSON responses to the iOS app
- **Transitive dependencies:** Automatically includes ~20+ related libraries

#### 3. **spring-boot-starter-data-jpa** (v3.2.0)
- **Type:** Starter dependency
- **Purpose:** Provides database access using Java Persistence API (JPA)
- **What it includes:**
  - **Hibernate** - Most popular JPA implementation (ORM - Object-Relational Mapping)
  - **Spring Data JPA** - Simplifies database operations with repository pattern
  - **Transaction management** - Automatic transaction handling
- **Why needed:** Allows defining database entities (User, Workout, Buddy) as Java classes and automatically generates SQL queries
- **How it works:** You define `@Entity` classes, and Spring Data JPA creates database tables and provides methods like `save()`, `findById()`, etc.

#### 4. **postgresql** (runtime scope)
- **Type:** Database driver
- **Version:** Managed by Spring Boot (typically 42.x.x)
- **Purpose:** JDBC driver that allows Java to connect to PostgreSQL database
- **Why needed:** Without this, Spring Boot cannot communicate with PostgreSQL
- **Runtime scope:** Only needed when running the application, not during compilation

### Security Dependencies

#### 5. **spring-boot-starter-security** (v3.2.0)
- **Type:** Starter dependency
- **Purpose:** Provides authentication and authorization framework
- **What it includes:**
  - **Spring Security Core** - Security framework
  - **Password encoding** - BCrypt password hashing
  - **Authentication filters** - Request interception for security
  - **CSRF protection** - Cross-site request forgery prevention
- **Why needed:** Secures API endpoints, handles user authentication, and protects against common web vulnerabilities
- **How it works:** Intercepts HTTP requests, validates JWT tokens, and allows/denies access to endpoints

#### 6. **jjwt-api** (v0.12.3)
- **Type:** JWT library (API)
- **Purpose:** Provides interfaces and classes for creating and parsing JSON Web Tokens
- **Why needed:** Defines the API for JWT operations (creating tokens, parsing tokens, validating signatures)
- **Note:** This is the API only - actual implementation comes from jjwt-impl

#### 7. **jjwt-impl** (v0.12.3, runtime scope)
- **Type:** JWT library (Implementation)
- **Purpose:** Actual implementation of JWT operations
- **Why needed:** Provides the code that creates, parses, and validates JWT tokens
- **Runtime scope:** Implementation details not needed at compile time

#### 8. **jjwt-jackson** (v0.12.3, runtime scope)
- **Type:** JWT library (Jackson integration)
- **Purpose:** Integrates JWT library with Jackson JSON processor
- **Why needed:** Allows JWT library to serialize/deserialize JSON payloads within tokens
- **Runtime scope:** Only needed when actually processing tokens

#### 9. **spring-security-crypto** (v3.2.0)
- **Type:** Security utility
- **Purpose:** Provides password encoding utilities (BCrypt)
- **Why needed:** Securely hash user passwords before storing in database
- **How it works:** Uses BCrypt algorithm to create one-way password hashes that cannot be reversed

### Validation Dependencies

#### 10. **spring-boot-starter-validation** (v3.2.0)
- **Type:** Starter dependency
- **Purpose:** Provides input validation framework
- **What it includes:**
  - **Bean Validation API** (JSR-303) - Standard validation annotations
  - **Hibernate Validator** - Implementation of Bean Validation
- **Why needed:** Validates incoming request data (e.g., email format, required fields, string length)
- **How it works:** Use annotations like `@NotNull`, `@Email`, `@Size` on request DTOs to automatically validate input

### Development Dependencies

#### 11. **spring-boot-devtools** (v3.2.0, runtime, optional)
- **Type:** Development tool
- **Purpose:** Provides development-time features
- **What it includes:**
  - **Automatic restart** - Restarts application when code changes
  - **LiveReload** - Browser refresh on changes
  - **Property defaults** - Development-friendly defaults
- **Why needed:** Speeds up development by automatically restarting the app when you make code changes
- **Optional:** Not required for production, only for development convenience

### Testing Dependencies

#### 12. **spring-boot-starter-test** (v3.2.0, test scope)
- **Type:** Starter dependency (test)
- **Purpose:** Provides testing framework and utilities
- **What it includes:**
  - **JUnit 5** - Unit testing framework
  - **Mockito** - Mocking framework for creating test doubles
  - **AssertJ** - Fluent assertions library
  - **Hamcrest** - Matcher library
  - **Spring Test** - Spring-specific testing utilities
- **Why needed:** Enables writing and running unit tests and integration tests
- **Test scope:** Only available during testing, not included in production build

#### 13. **spring-security-test** (test scope)
- **Type:** Testing utility
- **Purpose:** Provides utilities for testing Spring Security
- **Why needed:** Allows testing secured endpoints without manually setting up authentication
- **How it works:** Provides annotations like `@WithMockUser` to simulate authenticated users in tests

### Build Tool Dependencies

#### 14. **spring-boot-maven-plugin** (v3.2.0)
- **Type:** Maven plugin
- **Purpose:** Packages the application as an executable JAR and provides Spring Boot-specific Maven goals
- **Why needed:**
  - Creates "fat JAR" (includes all dependencies) that can run standalone
  - Provides `mvn spring-boot:run` command
  - Optimizes JAR for Spring Boot applications
- **How it works:** Bundles all dependencies into a single JAR file that can be executed with `java -jar`

## iOS App Dependencies

The iOS app uses **zero external dependencies** for the MVP. All functionality is provided by native iOS frameworks:

### Native iOS Frameworks (No Download Required)

#### 1. **SwiftUI**
- **Type:** Native iOS framework
- **Purpose:** Modern declarative UI framework for building user interfaces
- **Why used:** Provides all UI components (buttons, lists, navigation, etc.) without external libraries
- **Included in:** iOS SDK (comes with Xcode)

#### 2. **URLSession**
- **Type:** Native iOS framework
- **Purpose:** Network communication framework
- **Why used:** Handles all HTTP requests to the Spring Boot backend API
- **Capabilities:**
  - GET, POST, PUT, DELETE requests
  - JSON encoding/decoding
  - Authentication headers
  - Error handling
- **Included in:** Foundation framework (comes with iOS SDK)

#### 3. **Keychain Services**
- **Type:** Native iOS framework
- **Purpose:** Secure storage for sensitive data (passwords, tokens)
- **Why used:** Stores JWT authentication tokens securely
- **Security:** Data encrypted by iOS, protected by device passcode/biometrics
- **Included in:** Security framework (comes with iOS SDK)

#### 4. **Foundation**
- **Type:** Native iOS framework
- **Purpose:** Core Swift framework providing fundamental types and utilities
- **What it includes:**
  - Data types (String, Int, Date, etc.)
  - Collections (Array, Dictionary, Set)
  - Networking (URLSession)
  - JSON encoding/decoding (Codable)
- **Included in:** iOS SDK (comes with Xcode)

#### 5. **Combine** (if needed for reactive programming)
- **Type:** Native iOS framework
- **Purpose:** Reactive programming framework
- **Why might be used:** For handling asynchronous operations and data streams
- **Included in:** iOS SDK (comes with Xcode)

## Dependency Management

### Backend (Maven)
- **Location:** `backend/pom.xml`
- **Installation:** Run `mvn clean install` in the `backend/` directory
- **Where stored:** Maven downloads dependencies to `~/.m2/repository/` (local Maven repository)
- **Version management:** Spring Boot parent POM manages versions automatically

### iOS (No Package Manager Needed)
- **No Swift Package Manager dependencies** for MVP
- **No CocoaPods** required
- **No Carthage** required
- All frameworks are part of the iOS SDK included with Xcode

## Summary

### Backend: 14 Dependencies
- **Core:** 4 dependencies (web, data, database driver, parent)
- **Security:** 5 dependencies (security framework, JWT x3, crypto)
- **Validation:** 1 dependency
- **Development:** 1 dependency (devtools)
- **Testing:** 2 dependencies
- **Build:** 1 plugin

### iOS: 0 External Dependencies
- All functionality provided by native iOS frameworks
- No package manager setup required
- No dependency downloads needed

## Next Steps

After setup, when you run:
- **Backend:** `mvn clean install` - Downloads all Maven dependencies
- **iOS:** Open Xcode project - No dependency installation needed

All dependencies are now configured and ready to use!

