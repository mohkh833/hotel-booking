# Hotel Booking App

## Overview

The **Hotel Booking App** is a web application designed to streamline the hotel reservation process for users and provide an efficient management system for administrators. The application is built using **React** for the frontend and **Spring Boot** for the backend.

### Key Features:
- **User Registration & Login**: Users can sign up, verify their email addresses, and log in to access the app.
- **Email Verification**: After registration, users receive an email with a verification link to activate their account.
- **Booking System**: Users can select check-in and check-out dates and make bookings.
- **Admin Dashboard**: Admins can upload files, add rooms, and manage/edit bookings.

## Tech Stack

### Frontend:
- **React**: A JavaScript library for building user interfaces.
- **Axios**: Used for making HTTP requests from the frontend to the backend.
- **React Router**: For navigation within the application.

### Backend:
- **Spring Boot**: A Java framework for building the backend services.
- **Spring Security**: Used for securing the application, including JWT-based authentication.
- **Spring Data JPA**: For database interactions.
- **MySQL/PostgreSQL**: Relational database management system for storing user, booking, and room data.

## Installation and Setup

### Prerequisites:
- **Java 17** or higher
- **Node.js** and **npm**
- **MySQL** or **PostgreSQL** database

### Backend Setup:
1. Clone the repository:
    ```bash
    git clone https://github.com/yourusername/hotel-booking-app.git
    ```
2. Navigate to the backend directory:
    ```bash
    cd hotel-booking-app/backend
    ```
3. Configure the database in the `application.properties` or `application.yml` file:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/hotel_booking
    spring.datasource.username=root
    spring.datasource.password=yourpassword
    ```
4. Run the Spring Boot application:
    ```bash
    mvn spring-boot:run
    ```

### Frontend Setup:
1. Navigate to the frontend directory:
    ```bash
    cd ../frontend/hotel-react/hotel-react
    ```
2. Install dependencies:
    ```bash
    npm install
    ```
3. Start the React application:
    ```bash
    npm start
    ```

## Usage

### User Features:
- **Register & Verify Email**: Users must sign up with a valid email address and verify their account by clicking on a link sent to their email.
- **Login**: After verification, users can log in using their credentials.
- **Booking**: Users can choose their check-in and check-out dates to book a room.
  
### Admin Features:
- **Room Management**: Admins can add new rooms, edit existing room details, and manage room availability.
- **Booking Management**: Admins can view and edit user bookings.
- **File Uploads**: Admins can upload files (e.g., images of rooms, promotional materials).


## Contributing

Contributions are welcome! Please follow these steps:
1. Fork the repository.
2. Create a new branch (`git checkout -b feature-name`).
3. Make your changes.
4. Commit your changes (`git commit -m 'Add new feature'`).
5. Push to the branch (`git push origin feature-name`).
6. Open a pull request.
