# GymBuddy API Documentation

Base URL: `http://localhost:8080`

All endpoints return JSON unless otherwise specified.

---

## User Endpoints

### GET /api/user/coins

Returns the current user's coin balance.

**Response:**
```json
{
  "coins": 150
}
```

**Status Codes:**
- `200 OK` - Success

---

## User GymBuddy Endpoints

### GET /api/user/gymbuddies

Returns all gymbuddies owned by the user with their active status.

**Response:**
```json
[
  {
    "id": 1,
    "name": "GymBuddy",
    "imagePath": "/public/shop/gymbuddy.png",
    "isActive": true
  },
  {
    "id": 2,
    "name": "EarthBuddy",
    "imagePath": "/public/shop/earthbuddy.png",
    "isActive": false
  }
]
```

**Status Codes:**
- `200 OK` - Success
- `500 Internal Server Error` - Server error

---

### POST /api/user/gymbuddies/{gymBuddyId}/set-active

Sets a specific gymbuddy as active. Only one gymbuddy can be active at a time.

**Path Parameters:**
- `gymBuddyId` (Long) - ID of the gymbuddy to activate

**Response:**
- `200 OK` - Empty body on success

**Status Codes:**
- `200 OK` - Success
- `400 Bad Request` - Invalid gymbuddy ID or gymbuddy not owned

---

### POST /api/user/gymbuddies/{gymBuddyId}/purchase

Purchases a gymbuddy, deducting coins from the user's balance.

**Path Parameters:**
- `gymBuddyId` (Long) - ID of the gymbuddy to purchase

**Response (Success):**
```json
{
  "success": true,
  "message": "Purchase successful!"
}
```

**Response (Error):**
```json
{
  "success": false,
  "message": "Insufficient coins"
}
```

**Status Codes:**
- `200 OK` - Purchase successful
- `400 Bad Request` - Insufficient coins, gymbuddy already owned, or other error
- `500 Internal Server Error` - Server error

---

### GET /api/user/gymbuddies/active

Returns the currently active gymbuddy.

**Response:**
```json
{
  "id": 1,
  "name": "GymBuddy",
  "imagePath": "/public/shop/gymbuddy.png"
}
```

**Status Codes:**
- `200 OK` - Success
- `500 Internal Server Error` - Server error

---

## GymBuddy Endpoints

### GET /api/gymbuddies

Returns all gymbuddies available in the shop. Excludes the default "GymBuddy".

**Response:**
```json
[
  {
    "id": 2,
    "name": "EarthBuddy",
    "coinCost": 600,
    "imagePath": "/public/shop/earthbuddy.png",
    "requiresAllAchievements": false,
    "requiresAllDailyAchievements": false,
    "isAvailable": true
  }
]
```

**Status Codes:**
- `200 OK` - Success

---

### GET /api/gymbuddies/{id}

Returns a specific gymbuddy by ID.

**Path Parameters:**
- `id` (Long) - ID of the gymbuddy

**Response:**
```json
{
  "id": 2,
  "name": "EarthBuddy",
  "coinCost": 600,
  "imagePath": "/public/shop/earthbuddy.png",
  "requiresAllAchievements": false,
  "requiresAllDailyAchievements": false
}
```

**Status Codes:**
- `200 OK` - Success

---

## Workout Endpoints

### POST /api/workouts

Creates a new workout template.

**Request Body:**
```json
{
  "workoutName": "Morning Routine",
  "exercises": [
    {
      "name": "Push-ups",
      "noSets": 3,
      "type": "chest"
    },
    {
      "name": "Squats",
      "noSets": 4,
      "type": "legs"
    }
  ]
}
```

**Exercise Types:** `chest`, `back`, `arms`, `legs`

**Response:**
```json
{
  "id": 1,
  "workoutName": "Morning Routine",
  "message": "Workout saved successfully"
}
```

**Status Codes:**
- `201 Created` - Workout created successfully
- `500 Internal Server Error` - Failed to save workout
  ```json
  {
    "error": "Failed to save workout: [error message]"
  }
  ```

---

### GET /api/workouts

Returns all workout templates for the user.

**Response:**
```json
[
  {
    "id": 1,
    "workoutName": "Morning Routine",
    "exercises": [
      {
        "name": "Push-ups",
        "noSets": 3,
        "type": "chest"
      }
    ],
    "completedAt": null,
    "color": "#f48952"
  }
]
```

**Status Codes:**
- `200 OK` - Success
- `500 Internal Server Error` - Server error

---

### GET /api/workouts/{workoutId}

Returns a specific workout template by ID.

**Path Parameters:**
- `workoutId` (Long) - ID of the workout

**Response:**
```json
{
  "id": 1,
  "workoutName": "Morning Routine",
  "exercises": [
    {
      "name": "Push-ups",
      "noSets": 3,
      "type": "chest",
      "sets": [
        {"completed": false},
        {"completed": false},
        {"completed": false}
      ]
    }
  ],
  "completedAt": null,
  "color": "#f48952"
}
```

**Status Codes:**
- `200 OK` - Success
- `404 Not Found` - Workout not found

---

### PUT /api/workouts/{workoutId}

Updates an existing workout template.

**Path Parameters:**
- `workoutId` (Long) - ID of the workout to update

**Request Body:**
```json
{
  "workoutName": "Updated Morning Routine",
  "exercises": [
    {
      "name": "Push-ups",
      "noSets": 5,
      "type": "chest"
    }
  ]
}
```

**Response:**
```json
{
  "id": 1,
  "workoutName": "Updated Morning Routine",
  "message": "Workout saved successfully"
}
```

**Status Codes:**
- `201 Created` - Workout updated successfully
- `500 Internal Server Error` - Failed to update workout
  ```json
  {
    "error": "Failed to update workout: [error message]"
  }
  ```

---

### DELETE /api/workouts/{workoutId}

Deletes a workout template.

**Path Parameters:**
- `workoutId` (Long) - ID of the workout to delete

**Response:**
```json
{
  "message": "Workout deleted successfully"
}
```

**Status Codes:**
- `200 OK` - Workout deleted successfully
- `500 Internal Server Error` - Failed to delete workout
  ```json
  {
    "error": "Failed to delete workout: [error message]"
  }
  ```

---

### PUT /api/workouts/{workoutId}/complete

Marks a workout as completed by setting the `completedAt` timestamp.

**Path Parameters:**
- `workoutId` (Long) - ID of the workout

**Response:**
```json
{
  "id": 1,
  "workoutName": "Morning Routine",
  "completedAt": "2024-01-19T10:30:00",
  "message": "Workout marked as completed"
}
```

**Status Codes:**
- `200 OK` - Workout marked as completed
- `500 Internal Server Error` - Failed to mark workout as completed
  ```json
  {
    "error": "Failed to mark workout as completed: [error message]"
  }
  ```

---

### GET /api/workouts/completed/recent

Returns the last 3 completed workouts.

**Response:**
```json
[
  {
    "id": 1,
    "workoutName": "Morning Routine",
    "completedAt": "2024-01-19T10:30:00"
  }
]
```

**Status Codes:**
- `200 OK` - Success
- `500 Internal Server Error` - Server error

---

### PATCH /api/workouts/{workoutId}/exercises/{exerciseIndex}/sets/{setIndex}/complete

Marks a specific set within an exercise as completed.

**Path Parameters:**
- `workoutId` (Long) - ID of the workout
- `exerciseIndex` (int) - Index of the exercise (0-based)
- `setIndex` (int) - Index of the set (0-based)

**Response:**
```json
{
  "message": "Set marked as completed",
  "workoutId": 1,
  "exerciseIndex": 0,
  "setIndex": 0
}
```

**Status Codes:**
- `200 OK` - Set marked as completed
- `404 Not Found` - Workout, exercise, or set not found
  ```json
  {
    "error": "[error message]"
  }
  ```
- `500 Internal Server Error` - Failed to mark set as completed
  ```json
  {
    "error": "Failed to mark set as completed: [error message]"
  }
  ```

---

### DELETE /api/workouts/{workoutId}/reset

Resets all set completions for a workout.

**Path Parameters:**
- `workoutId` (Long) - ID of the workout

**Response:**
```json
{
  "message": "Workout sets reset successfully",
  "workoutId": 1
}
```

**Status Codes:**
- `200 OK` - Workout sets reset successfully
- `404 Not Found` - Workout not found
  ```json
  {
    "error": "[error message]"
  }
  ```
- `500 Internal Server Error` - Failed to reset workout sets
  ```json
  {
    "error": "Failed to reset workout sets: [error message]"
  }
  ```

---

## Achievement Endpoints

### GET /api/achievements

Returns all achievements (daily and lifetime).

**Response:**
```json
[
  {
    "id": 1,
    "name": "First Workout",
    "description": "Complete your first workout",
    "current": 0,
    "target": 1,
    "isDaily": false,
    "coinReward": 10
  },
  {
    "id": 2,
    "name": "Daily Warrior",
    "description": "Complete 3 workouts today",
    "current": 1,
    "target": 3,
    "isDaily": true,
    "coinReward": 50
  }
]
```

**Status Codes:**
- `200 OK` - Success
- `500 Internal Server Error` - Server error

---

### PUT /api/achievements/update

Updates achievement progress based on completed workouts and returns the updated coin total.

**Response:**
```json
{
  "coins": 160
}
```

**Status Codes:**
- `200 OK` - Achievements updated successfully
- `500 Internal Server Error` - Failed to update achievements
  ```json
  {
    "error": "Achievements could not be updated: [error message]"
  }
  ```

---

### PUT /api/achievements/exercise-response

Records an exercise completion for achievement tracking.

**Request Body:**
```json
{
  "type": "chest",
  "duration": 60
}
```

**Request Parameters:**
- `type` (String) - Exercise type: `chest`, `back`, `arms`, or `legs`
- `duration` (Number or String) - Duration in seconds

**Response:**
```json
{
  "exerciseResponse": {
    "type": "chest",
    "duration": 60,
    "completedAt": "2024-01-19T10:30:00"
  }
}
```

**Status Codes:**
- `200 OK` - Exercise response created successfully
- `500 Internal Server Error` - Failed to create exercise response
  ```json
  {
    "error": "Failed to create exercise response: [error message]"
  }
  ```

---

## HTML Page Endpoints

These endpoints return HTML pages (Content-Type: `text/html`):

- **GET /** - Landing page
- **GET /home** - Home page with animated buddy
- **GET /train** - Start workout page
- **GET /workouts** - Workout templates list
- **GET /achievements** - Achievements page
- **GET /shop** - Shop page
- **GET /collection** - Gymbuddy collection page
- **GET /workout-animation** - Workout animation page (requires `type` and `duration` query parameters)
- **GET /train/{workoutId}** - Workout detail page for a specific workout template
- **GET /workout-fragments/{fragmentName}** - Workout fragment HTML

---

## Error Responses

All endpoints may return standard HTTP error codes:

- **400 Bad Request** - Invalid request parameters or body
- **404 Not Found** - Resource not found
- **500 Internal Server Error** - Server error

Error responses typically include a JSON body with an `error` field:
```json
{
  "error": "Error message describing what went wrong"
}
```
