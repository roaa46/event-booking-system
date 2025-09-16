# Event Booking System

A full-stack web application that lets users browse and book events, while providing administrative capabilities to manage events, bookings, and user roles. It uses JWT-based authentication with role-based authorization, supporting regular users, pending admins, and confirmed admins.

---

## Features

* **User registration & login** with JWT-based authentication
* Role-based access control: regular users vs admins
* Browse events with images, descriptions, dates, etc.
* Book events; manage your own bookings
* Admin interface: create, update, delete events; manage pending admin approvals
* File upload support for event images
* RESTful API backend + React frontend
* Uses MySQL database for persistence

---

## Technologies Used

| Layer                     | Technologies / Libraries                                   |
| ------------------------- | ---------------------------------------------------------- |
| Backend                   | Java, Spring Boot (v3.x), Spring Security, Spring Data JPA |
| Authentication / Security | JWT (using JJWT), role management, stateless security      |
| Mapping / DTOs            | MapStruct, DTOs for request/response separation            |
| Database                  | MySQL                                                      |
| Frontend                  | React (React 18), React Router, Axios                      |
| Build / Tooling           | Maven, Lombok                                              |

---

## Installation

> These are steps to set up the project locally. Adjust environment variables and configuration as needed.

1. **Clone the repository**

   ```bash
   git clone https://github.com/roaa46/event-booking-system.git
   cd event-booking-system
   ```

2. **Setup the backend**

   * Ensure you have Java (JDK 17+), Maven, and MySQL installed.
   * Create a MySQL database (e.g. `event_booking_db`).
   * Copy or create an application configuration file (e.g. `application.properties` or `application.yml`) in the backend module. Set up your DB URL, username, password, JWT secret, and other needed properties.

   Example (in `src/main/resources/application.properties`):

   ```
   spring.datasource.url=jdbc:mysql://localhost:3306/event_booking_db
   spring.datasource.username=your_db_user
   spring.datasource.password=your_db_password

   jwt.secret=your_jwt_secret
   # other JWT / security / file-upload settings
   ```

   * Build and run backend:

   ```bash
   cd backend
   mvn clean install
   mvn spring-boot:run
   ```

3. **Setup the frontend**

   * Ensure Node.js (v16+ or whatever version is used) and npm or yarn are installed.
   * Navigate to the frontend folder (e.g. `frontend` or `client`).
   * Install dependencies:

     ```bash
     npm install
     ```
   * Configure environment variables (if any), such as the backend API URL, etc.
   * Run the dev server:

     ```bash
     npm run dev
     ```

4. **Accessing the application**

   * Frontend UI will be available at `http://localhost:5173` (or configured port).
   * Backend API will be available (by default) at `http://localhost:8080` (or whichever port is configured).
---

# Demo
![Event Booking System Demo](https://drive.google.com/uc?export=view&id=1j9JJcNP-QQOpWU4Wf2ZBf-6NdkYmzY1y)

---

# API Endpoints
- Base URL: `http://localhost:8080`
## Authentication Endpoints

Base path: `/api/auth`

| Method | Endpoint    | Description                             |
| ------ | ----------- | --------------------------------------- |
| POST   | `/register` | Register a new user                     |
| POST   | `/login`    | Authenticate user and receive JWT token |
| POST   | `/logout`   | Logout user and invalidate token        |
| GET    | `/me`       | Get current user's profile              |
| DELETE | `/me`       | Delete current user's account           |

---

## Admin Management Endpoints

Base path: `/api/pending/admins`

| Method | Endpoint                | Description                        |                                         |
| ------ | ----------------------- | ---------------------------------- | --------------------------------------- |
| GET    | `/`                     | Get list of pending admin requests |                                         |
| PUT    | /{id}?action={approve \| reject}                       | Approve or reject pending admin request |

---

## Event Management Endpoints

Base path: `/api/events`

| Method | Endpoint | Description                                                                 |
| ------ | -------- | --------------------------------------------------------------------------- |
| POST   | `/`      | Create a new event (supports multipart/form-data with optional image)       |
| PUT    | `/{id}`  | Update an existing event (supports multipart/form-data with optional image) |
| GET    | `/{id}`  | Get event details by ID                                                     |
| DELETE | `/{id}`  | Delete an event                                                             |
| GET    | `/`      | Get paginated list of events (params: page, size)                           |

---

## Booking Management Endpoints

Base path: `/api/users`

| Method | Endpoint               | Description                                                |
| ------ | ---------------------- | ---------------------------------------------------------- |
| POST   | `/{personId}/bookings` | Book an event for a user                                   |
| GET    | `/{personId}/bookings` | Get paginated list of user's bookings (params: page, size) |

---

## Usage Instructions

* Register as a **regular user** to browse events and make bookings.
* If you wish to act as an admin, there is a flow for admin registration / pending admin approval (depending on how the project is set up).
* Once you’re an admin, you can access event management interfaces: create new events, edit them, delete them, upload event images.
* View your existing bookings under *My Bookings*.
* API endpoints are exposed for login, registration, event CRUD, booking, etc.

