# SnoozeSpot API - Complete Documentation

## Table of Contents

1. [Project Overview](#project-overview)
2. [Architecture](#architecture)
3. [Technology Stack](#technology-stack)
4. [Database Schema](#database-schema)
5. [Project Structure](#project-structure)
6. [Core Components](#core-components)
7. [API Endpoints](#api-endpoints)
8. [Authentication & Security](#authentication--security)
9. [Configuration](#configuration)
10. [Build & Deployment](#build--deployment)
11. [Development Guide](#development-guide)

---

## Project Overview

**SnoozeSpot** is a REST API built with Kotlin and Ktor that provides a social platform for discovering and sharing sleeping locations (spots). The application includes features for user management, spot discovery, post creation, comments, ratings, and social interactions like following and liking.

### Key Features

- **User Management**: User registration, authentication, profiles, and karma system
- **Spot Discovery**: Create, view, and manage sleeping spots with attributes and pictures
- **Social Features**: Follow users, like spots/posts, comment on spots and posts
- **Rating System**: Rate and comment on sleeping spots
- **File Management**: Upload and manage media files for spots and posts
- **Role-Based Access Control**: Permission system with role hierarchy
- **JWT Authentication**: Secure API access with JWT tokens
- **HTTPS Support**: Built-in SSL/TLS certificate management
- **OpenAPI & Swagger**: Automatic API documentation and interactive exploration

---

## Architecture

The application follows a **layered architecture** pattern:

```
┌─────────────────────────────────┐
│      API Routes (Endpoints)      │
│  (authRoutes, userRoutes, etc.)  │
└──────────────┬──────────────────┘
               │
┌──────────────▼──────────────────┐
│     Services (Business Logic)    │
│  (Handle operations & validation)│
└──────────────┬──────────────────┘
               │
┌──────────────▼──────────────────┐
│    Repositories (Data Access)    │
│  (Database queries via Exposed)  │
└──────────────┬──────────────────┘
               │
┌──────────────▼──────────────────┐
│     Database (SQLite + Exposed)  │
│  (Entities, Tables, Queries)     │
└─────────────────────────────────┘
```

### Design Patterns Used

- **Repository Pattern**: Data access abstraction through repository classes
- **Service Layer**: Business logic encapsulation
- **DTO Pattern**: Data Transfer Objects for API communication
- **Dependency Injection**: Koin framework for IoC container management
- **Factory Pattern**: Table and entity definitions

---

## Technology Stack

### Core Framework
- **Ktor 2.x**: High-performance asynchronous web framework
- **Kotlin 1.9+**: Modern JVM language with coroutine support

### Database
- **Exposed 0.41.1**: Type-safe SQL framework for Kotlin
- **SQLite 3.41.2**: Lightweight embedded database
- **Java Time**: DateTime support in database

### Security & Authentication
- **JWT (JSON Web Tokens)**: Bearer token authentication
- **Ktor Auth**: Authentication and authorization framework
- **SSL/TLS**: HTTPS certificate generation and management

### Dependency Injection
- **Koin 3.4.0**: Lightweight Kotlin dependency injection framework
- **Koin SLF4J Logger**: Logging integration

### Serialization
- **kotlinx.serialization**: JSON serialization/deserialization

### API Documentation
- **Swagger UI**: Interactive API documentation
- **OpenAPI**: Standardized API documentation format
- **AsyncAPI**: Event-driven API documentation

### Monitoring & Logging
- **Call Logging**: HTTP request/response logging
- **Logback**: Logging framework configuration
- **Status Pages**: Exception handling and error responses

### Testing
- **Ktor Server Test Host**: Integration testing framework
- **Kotlin Test JUnit**: Unit testing support

---

## Database Schema

### Core Tables

#### Users (`user`)
Stores user account information and credentials.

| Column | Type | Constraints | Notes |
|--------|------|-----------|-------|
| id | INTEGER | PRIMARY KEY | Auto-generated |
| uuid | UUID | UNIQUE INDEX | System identifier |
| username | VARCHAR(50) | UNIQUE INDEX | Login identifier |
| email | VARCHAR(320) | UNIQUE INDEX, NULLABLE | Contact email |
| password | VARCHAR(64) | - | Hashed password |
| profile_picture_id | INTEGER | FK (Files), NULLABLE | Profile image |
| role_id | INTEGER | FK (Roles) | User's role |
| karma | INTEGER | DEFAULT 0 | Reputation score |
| can_connect | INTEGER | DEFAULT 1 | Account status (1=active) |
| created_at | DATETIME | DEFAULT NOW | Account creation |
| deleted_at | DATETIME | NULLABLE | Soft delete timestamp |

#### Roles (`role`)
Defines user roles for access control.

| Column | Type | Constraints | Notes |
|--------|------|-----------|-------|
| id | INTEGER | PRIMARY KEY | Auto-generated |
| name | VARCHAR(50) | UNIQUE INDEX | Role name |
| description | VARCHAR(255) | NULLABLE | Role description |

#### Permissions (`permission`)
Defines available permissions.

| Column | Type | Constraints | Notes |
|--------|------|-----------|-------|
| id | INTEGER | PRIMARY KEY | Auto-increment |
| name | VARCHAR(50) | UNIQUE INDEX | Permission name |
| description | VARCHAR(255) | NULLABLE | Permission details |

#### Role Permissions (`role_permission`)
Maps roles to permissions (many-to-many).

| Column | Type | Constraints | Notes |
|--------|------|-----------|-------|
| role_id | INTEGER | FK (Roles), PK | Role reference |
| permission_id | INTEGER | FK (Permissions), PK | Permission reference |

#### Files (`file`)
Manages uploaded media files.

| Column | Type | Constraints | Notes |
|--------|------|-----------|-------|
| id | INTEGER | PRIMARY KEY | Auto-generated |
| uuid | UUID | UNIQUE INDEX | File identifier |
| description | VARCHAR(255) | - | File name/description |
| path | VARCHAR(512) | UNIQUE INDEX | File system path |
| type | INTEGER | DEFAULT 0 | File type (0=image, etc.) |
| usage | INTEGER | DEFAULT 0 | File usage count |
| can_be_used | INTEGER | DEFAULT 1 | Availability flag |
| created_at | DATETIME | DEFAULT NOW | Upload timestamp |
| deleted_at | DATETIME | NULLABLE | Soft delete timestamp |

### Spot-Related Tables

#### Spots (`spot`)
Sleeping location information.

| Column | Type | Constraints | Notes |
|--------|------|-----------|-------|
| id | INTEGER | PRIMARY KEY | Auto-generated |
| creator_id | INTEGER | FK (Users) | Spot creator |
| name | VARCHAR(255) | - | Spot name |
| description | VARCHAR(1000) | - | Spot details |
| latitude | DOUBLE | - | GPS latitude |
| longitude | DOUBLE | - | GPS longitude |
| can_be_displayed | INTEGER | DEFAULT 1 | Visibility flag |
| created_at | DATETIME | DEFAULT NOW | Creation timestamp |
| deleted_at | DATETIME | NULLABLE | Soft delete timestamp |

#### Spot Attributes (`spot_attribute`)
Tags/characteristics for spots (e.g., "Quiet", "Safe", "Scenic").

| Column | Type | Constraints | Notes |
|--------|------|-----------|-------|
| id | INTEGER | PRIMARY KEY | Auto-increment |
| name | VARCHAR(50) | UNIQUE INDEX | Attribute name |
| icon_id | INTEGER | FK (Files), NULLABLE | Icon file |

#### Spot Attribute Links (`spot_attribute_link`)
Associates attributes with spots (many-to-many).

| Column | Type | Constraints | Notes |
|--------|------|-----------|-------|
| spot_id | INTEGER | FK (Spots), PK | Spot reference |
| attribute_id | INTEGER | FK (Attributes), PK | Attribute reference |

#### Spot Pictures (`spot_picture`)
Associates pictures with spots (many-to-many).

| Column | Type | Constraints | Notes |
|--------|------|-----------|-------|
| spot_id | INTEGER | FK (Spots), PK | Spot reference |
| file_id | INTEGER | FK (Files), PK | Picture file |

#### Spot Comments (`spot_comment`)
User comments on spots with ratings.

| Column | Type | Constraints | Notes |
|--------|------|-----------|-------|
| id | INTEGER | PRIMARY KEY | Auto-generated |
| user_id | INTEGER | FK (Users) | Commenter |
| spot_id | INTEGER | FK (Spots) | Commented spot |
| description | VARCHAR(2000) | - | Comment text |
| rating | INTEGER | - | Rating (1-5) |
| created_at | DATETIME | DEFAULT NOW | Comment timestamp |
| deleted_at | DATETIME | NULLABLE | Soft delete timestamp |

#### Spot Likes (`spot_like`)
Tracks user likes on spots (many-to-many).

| Column | Type | Constraints | Notes |
|--------|------|-----------|-------|
| user_id | INTEGER | FK (Users), PK | User who liked |
| spot_id | INTEGER | FK (Spots), PK | Liked spot |

### Post-Related Tables

#### Posts (`post`)
User-generated content posts.

| Column | Type | Constraints | Notes |
|--------|------|-----------|-------|
| id | INTEGER | PRIMARY KEY | Auto-generated |
| user_id | INTEGER | FK (Users) | Post author |
| content | VARCHAR(5000) | - | Post text content |
| can_be_displayed | INTEGER | DEFAULT 1 | Visibility flag |
| created_at | DATETIME | DEFAULT NOW | Creation timestamp |
| deleted_at | DATETIME | NULLABLE | Soft delete timestamp |

#### Post Pictures (`post_picture`)
Associates pictures with posts (many-to-many).

| Column | Type | Constraints | Notes |
|--------|------|-----------|-------|
| post_id | INTEGER | FK (Posts), PK | Post reference |
| file_id | INTEGER | FK (Files), PK | Picture file |

#### Post Comments (`post_comment`)
User comments on posts.

| Column | Type | Constraints | Notes |
|--------|------|-----------|-------|
| id | INTEGER | PRIMARY KEY | Auto-generated |
| user_id | INTEGER | FK (Users) | Commenter |
| post_id | INTEGER | FK (Posts) | Commented post |
| content | VARCHAR(2000) | - | Comment text |
| created_at | DATETIME | DEFAULT NOW | Comment timestamp |
| deleted_at | DATETIME | NULLABLE | Soft delete timestamp |

#### Post Likes (`post_like`)
Tracks user likes on posts (many-to-many).

| Column | Type | Constraints | Notes |
|--------|------|-----------|-------|
| user_id | INTEGER | FK (Users), PK | User who liked |
| post_id | INTEGER | FK (Posts), PK | Liked post |

### Social Tables

#### Following (`following`)
User relationship tracking (many-to-many).

| Column | Type | Constraints | Notes |
|--------|------|-----------|-------|
| follower_id | INTEGER | FK (Users), PK | Follower user |
| followed_id | INTEGER | FK (Users), PK | Followed user |

---

## Project Structure

```
src/main/kotlin/iut/fauryollivier/snoozespot/api/
├── Application.kt                    # Entry point and module configuration
├── Routing.kt                        # Route registration
├── auth/                             # Authentication logic
│   └── Security.kt                   # JWT and auth configuration
├── config/                           # Configuration modules
│   ├── Config.kt                     # Constants and configuration
│   ├── HTTP.kt                       # HTTP feature configuration
│   ├── Koin.kt                       # Dependency injection setup
│   ├── Monitoring.kt                 # Logging and monitoring
│   └── serialization/                # Serialization configuration
├── database/                         # Database layer
│   ├── Tables.kt                     # Table definitions and helper functions
│   ├── ORM.kt                        # Exposed framework configuration
│   └── Extensions.kt                 # Database extension functions
├── dtos/                             # Data Transfer Objects
│   ├── DTOBase.kt                    # Base DTO class
│   ├── UserDTO.kt
│   ├── PostDTO.kt
│   ├── PostCommentDTO.kt
│   ├── SpotDTO.kt
│   ├── SpotCommentDTO.kt
│   ├── SpotAttributeDTO.kt
│   └── StoredFileDTO.kt
├── entities/                         # Domain entities
│   ├── EntityBase.kt                 # Base entity class
│   ├── User.kt
│   ├── Post.kt
│   ├── PostComment.kt
│   ├── Spot.kt
│   ├── SpotAttribute.kt
│   ├── SpotComment.kt
│   └── StoredFile.kt
├── enums/                            # Enumerations
│   ├── UserRole.kt
│   ├── Permission.kt
│   └── FileType.kt
├── repositories/                     # Data access layer
│   ├── BaseRepository.kt             # Base repository with common operations
│   ├── UserRepository.kt
│   ├── PostRepository.kt
│   ├── PostCommentRepository.kt
│   ├── SpotRepository.kt
│   ├── SpotCommentRepository.kt
│   └── StoredFileRepository.kt
├── routes/                           # HTTP route handlers
│   ├── authRoutes.kt                 # Authentication endpoints
│   ├── userRoutes.kt                 # User management endpoints
│   ├── postRoutes.kt                 # Post management endpoints
│   ├── spotRoutes.kt                 # Spot management endpoints
│   └── fileRoutes.kt                 # File upload/download endpoints
└── services/                         # Business logic layer
    ├── UserService.kt
    ├── PostService.kt
    ├── SpotService.kt
    └── FileService.kt

src/main/resources/
├── application.yaml                  # Ktor configuration
├── logback.xml                       # Logging configuration
├── openapi/                          # OpenAPI documentation
│   └── documentation.yaml
└── static/                           # Static files
    └── files/
        └── post_media/
            └── images/

src/test/
└── kotlin/
    └── ApplicationTest.kt            # Integration tests
```

---

## Core Components

### 1. Application Module (`Application.kt`)

The entry point for the Ktor server. Initializes all modules and configurations:

```kotlin
fun Application.module() {
    configureORM()              // Initialize Exposed framework
    configureKoin()             // Setup dependency injection
    configureSerialization()    // JSON serialization
    configureMonitoring()       // Logging and monitoring
    configureSecurity()         // JWT and authentication
    configureHTTP()             // HTTP features and headers
    configureRouting()          // Register all routes
}
```

### 2. Database Layer

#### Tables.kt
Defines all database tables using Exposed's DSL:
- Object-oriented table definitions
- Type-safe column definitions
- Foreign key relationships
- Default values and constraints

#### Helper Functions
- `selectVisible()`: Filter records by `deleted_at IS NULL` and `can_be_displayed = 1`
- `selectActive()`: Filter active users by `deleted_at IS NULL` and `can_connect = 1`

### 3. Repository Layer (`repositories/`)

**BaseRepository**: Generic repository providing CRUD operations
- `getAll()`: Retrieve all records
- `getById(id)`: Get single record by ID
- `create()`: Insert new record
- `update()`: Modify existing record
- `delete()`: Soft delete (mark as deleted)

**Specialized Repositories**:
- `UserRepository`: User operations with authentication checks
- `PostRepository`: Post creation, retrieval, and filtering
- `SpotRepository`: Spot management with location queries
- `SpotCommentRepository`: Comment operations with ratings
- `PostCommentRepository`: Post comment management
- `StoredFileRepository`: File metadata management

### 4. Service Layer (`services/`)

Business logic encapsulation:
- Input validation
- Business rule enforcement
- Error handling
- Transaction management
- Complex operations spanning multiple repositories

### 5. DTO Layer (`dtos/`)

Data Transfer Objects for API communication:
- Separate from internal entities
- Version control friendly
- API contract definition
- Optional field handling

### 6. Route Handlers (`routes/`)

HTTP endpoint definitions:
- `authRoutes.kt`: Login, registration, token refresh
- `userRoutes.kt`: Profile, follow, karma operations
- `postRoutes.kt`: Create, read, update, delete posts
- `spotRoutes.kt`: Spot discovery and management
- `fileRoutes.kt`: File upload and retrieval

### 7. Authentication & Security (`auth/`)

JWT-based authentication:
- Token generation and validation
- Role-based access control
- Permission checking
- Secure password handling

### 8. Configuration (`config/`)

- **Config.kt**: Constants (SSL domains, JWT settings, etc.)
- **Koin.kt**: Dependency injection container setup
- **HTTP.kt**: Header management, CORS, compression
- **Monitoring.kt**: Logging levels and formats
- **Serialization**: Custom JSON serializers and deserializers

---

## API Endpoints

### Authentication Routes (`/auth`)

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---|
| POST | `/auth/register` | Register new user | No |
| POST | `/auth/login` | Authenticate and get JWT | No |
| POST | `/auth/refresh` | Refresh expired token | Yes |
| POST | `/auth/logout` | Invalidate token | Yes |

**Request/Response Examples:**

```json
// POST /auth/register
{
  "username": "sleepyuser",
  "email": "user@example.com",
  "password": "securepassword123"
}

// Response: 201 Created
{
  "id": 1,
  "uuid": "550e8400-e29b-41d4-a716-446655440000",
  "username": "sleepyuser",
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### User Routes (`/users`)

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---|
| GET | `/users/{id}` | Get user profile | No |
| PUT | `/users/{id}` | Update user profile | Yes |
| GET | `/users/{id}/posts` | Get user's posts | No |
| GET | `/users/{id}/spots` | Get user's spots | No |
| POST | `/users/{id}/follow` | Follow user | Yes |
| DELETE | `/users/{id}/follow` | Unfollow user | Yes |
| GET | `/users/{id}/followers` | Get followers | No |
| GET | `/users/{id}/following` | Get following list | No |

### Spot Routes (`/spots`)

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---|
| GET | `/spots` | List all spots (paginated) | No |
| GET | `/spots/{id}` | Get spot details | No |
| POST | `/spots` | Create new spot | Yes |
| PUT | `/spots/{id}` | Update spot | Yes |
| DELETE | `/spots/{id}` | Delete spot | Yes |
| GET | `/spots/{id}/comments` | Get spot comments | No |
| POST | `/spots/{id}/comments` | Add comment to spot | Yes |
| POST | `/spots/{id}/like` | Like a spot | Yes |
| DELETE | `/spots/{id}/like` | Unlike a spot | Yes |
| GET | `/spots/{id}/likes` | Get spot likes count | No |

### Post Routes (`/posts`)

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---|
| GET | `/posts` | List all posts (paginated) | No |
| GET | `/posts/{id}` | Get post details | No |
| POST | `/posts` | Create new post | Yes |
| PUT | `/posts/{id}` | Update post | Yes |
| DELETE | `/posts/{id}` | Delete post | Yes |
| GET | `/posts/{id}/comments` | Get post comments | No |
| POST | `/posts/{id}/comments` | Add comment to post | Yes |
| POST | `/posts/{id}/like` | Like a post | Yes |
| DELETE | `/posts/{id}/like` | Unlike a post | Yes |
| GET | `/posts/{id}/likes` | Get post likes count | No |

### File Routes (`/files`)

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---|
| POST | `/files/upload` | Upload media file | Yes |
| GET | `/files/{id}` | Download file | No |
| DELETE | `/files/{id}` | Delete file | Yes |

---

## Authentication & Security

### JWT Authentication Flow

1. **User Registration/Login**
   - User credentials validated
   - JWT token generated with expiration time
   - Token includes user ID and role

2. **Token Usage**
   - Client includes token in `Authorization: Bearer <token>` header
   - Server validates token signature and expiration
   - User identity extracted from token claims

3. **Token Refresh**
   - Expired tokens can be refreshed with valid refresh token
   - New token issued with updated expiration

### JWT Configuration (`application.yaml`)

```yaml
jwt:
  domain: "https://jwt-provider-domain/"
  audience: "jwt-audience"
  realm: "ktor sample app"
```

### Role-Based Access Control (RBAC)

- **Roles**: User, Moderator, Admin
- **Permissions**: Assigned to roles
- **Enforcement**: Checked on protected routes
- **Examples**:
  - Only spot creator can edit/delete their spot
  - Only comment author can delete their comment
  - Admins can delete/moderate any content

### Security Features

- Password hashing (bcrypt recommended)
- HTTPS/SSL enforced with self-signed certificates
- CORS protection
- SQL injection prevention (Exposed framework)
- XSS protection via input validation
- Soft deletes preserve audit trail

---

## Configuration

### Environment Variables (application.yaml)

```yaml
ktor:
  application:
    modules:
      - iut.fauryollivier.snoozespot.api.ApplicationKt.module
  deployment:
    port: 443              # HTTPS port
    host: "127.0.0.1"     # Localhost only (change for production)

jwt:
  domain: "https://jwt-provider-domain/"
  audience: "jwt-audience"
  realm: "ktor sample app"
```

### Logging Configuration (logback.xml)

- Configurable log levels for packages
- Console and file appenders
- Structured logging format
- AsyncAppender for performance

### SSL/TLS Certificate

- Auto-generated self-signed certificate on startup
- Stored in `build/keystore.jks`
- Certificate exported for Android in `build/exportedCertificateForAndroid.cer`
- Configuration in `Config.kt`:

```kotlin
object Config {
    const val SSL_CERTIFICATE_ALIAS = "mykey"
    const val SSL_CERTIFICATE_PASSWORD = "changeit"
    const val SSL_CERTIFICATE_FILE_PASSWORD = "changeit"
    val SSL_CERTIFICATE_DOMAINS = listOf("127.0.0.1", "localhost")
}
```

---

## Build & Deployment

### Prerequisites

- JDK 11+
- Kotlin 1.9+
- Gradle 7.x+

### Build Commands

```bash
# Run tests
./gradlew test

# Build the project
./gradlew build

# Build fat JAR (all dependencies included)
./gradlew buildFatJar

# Build Docker image
./gradlew buildImage

# Run locally
./gradlew run

# Run with Docker
./gradlew runDocker
```

### Gradle Configuration

**build.gradle.kts** includes:
- Ktor plugins for Kotlin and serialization
- Dependencies management via `gradle/libs.versions.toml`
- Koin DI configuration
- Exposed ORM with SQLite
- JWT and authentication libraries
- Testing frameworks
- Docker integration

### Deployment Checklist

- [ ] Update JWT domain in `application.yaml`
- [ ] Change default SSL certificate password
- [ ] Configure database path for production
- [ ] Set appropriate log levels
- [ ] Enable CORS for frontend domain
- [ ] Configure HTTPS certificate from trusted CA
- [ ] Set database backup strategy
- [ ] Review and update permissions/roles
- [ ] Load test before production
- [ ] Setup monitoring and alerting

---

## Development Guide

### Adding a New Entity

1. **Create Entity Class** (`entities/MyEntity.kt`)
   ```kotlin
   data class MyEntity(
       val id: Int,
       val name: String,
       val createdAt: LocalDateTime,
       val deletedAt: LocalDateTime?
   ) : EntityBase()
   ```

2. **Create DTO** (`dtos/MyEntityDTO.kt`)
   ```kotlin
   @Serializable
   data class MyEntityDTO(
       val id: Int,
       val name: String
   )
   ```

3. **Create Table** (add to `database/Tables.kt`)
   ```kotlin
   object MyEntities : IntIdTable("my_entity") {
       val name = varchar("name", 255)
       val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
       val deletedAt = datetime("deleted_at").nullable()
   }
   ```

4. **Create Repository** (`repositories/MyEntityRepository.kt`)
   ```kotlin
   class MyEntityRepository : BaseRepository<MyEntity, MyEntityTable>() {
       // Custom queries here
   }
   ```

5. **Create Service** (`services/MyEntityService.kt`)
   ```kotlin
   class MyEntityService(private val repository: MyEntityRepository) {
       // Business logic here
   }
   ```

6. **Create Routes** (`routes/myEntityRoutes.kt`)
   ```kotlin
   fun Route.myEntityRoutes(service: MyEntityService) {
       route("/my-entities") {
           get { /* list all */ }
           post { /* create */ }
           get("{id}") { /* get by id */ }
           put("{id}") { /* update */ }
           delete("{id}") { /* delete */ }
       }
   }
   ```

7. **Register in Routing** (`Routing.kt`)
   ```kotlin
   myEntityRoutes(inject())
   ```

8. **Register in DI** (`config/Koin.kt`)
   ```kotlin
   single { MyEntityRepository() }
   single { MyEntityService(get()) }
   ```

### Testing

Create integration tests in `src/test/kotlin/`:

```kotlin
@Test
fun testUserCreation() {
    testApplication {
        val response = client.post("/auth/register") {
            contentType(ContentType.Application.Json)
            setBody(CreateUserRequest(...))
        }
        assertEquals(HttpStatusCode.Created, response.status)
    }
}
```

### Common Tasks

#### Query with Filters
```kotlin
val activeUsers = Tables.Users
    .selectVisible()
    .selectActive()
    .toList()
```

#### Transaction Management
```kotlin
transaction {
    // Database operations automatically wrapped in transaction
    val user = repository.create(newUser)
    repository.update(user)
}
```

#### Error Handling
```kotlin
try {
    val user = repository.getById(id)
        ?: throw NotFoundException("User not found")
} catch (e: SQLException) {
    throw DatabaseException("Database error", e)
}
```

---

## Troubleshooting

### Common Issues

**SSL Certificate Error**
- Ensure `build/keystore.jks` exists
- Check certificate password matches config
- For Android clients, import `exportedCertificateForAndroid.cer`

**Port Already in Use**
- Change port in `application.yaml`
- Or kill process: `lsof -ti :443 | xargs kill -9`

**Database Lock**
- SQLite locks can occur with concurrent writes
- Ensure transactions are properly scoped
- Consider upgrading to PostgreSQL for production

**JWT Token Invalid**
- Verify JWT domain matches in config
- Check token hasn't expired
- Ensure Authorization header format is correct

**Permission Denied Errors**
- Verify user has required role/permission
- Check route protection attributes
- Review role-permission mappings

### Performance Optimization

- Use connection pooling for database
- Enable response compression in HTTP config
- Implement caching for frequently accessed data
- Add database indexes for common queries
- Monitor slow queries in logs

---

## References

- [Ktor Documentation](https://ktor.io/docs/home.html)
- [Exposed ORM Documentation](https://github.com/JetBrains/Exposed/wiki)
- [Koin Documentation](https://insert-koin.io/)
- [JWT Introduction](https://jwt.io/)
- [REST API Best Practices](https://restfulapi.net/)
- [OpenAPI Specification](https://swagger.io/specification/)

---

## Support

For issues and questions, contact the development team or create an issue in the repository.

---

**Last Updated**: January 13, 2026  
**Version**: 0.0.1

