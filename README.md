# Smart Contact Manager Backend

Welcome to the **Smart Contact Manager Backend** project! This Spring Boot application is designed to efficiently handle user management and contacts, integrating features like authentication, role management, and contact storage with JWT-based security.

## 🚀 Features

- **User Authentication & Authorization**: Secure user login and registration using JWT tokens for token-based authentication.
- **User Profile Management**: Manage user details, including profile pictures and contact information.
- **Contact Management**: Store and manage personal or business contacts, with the ability to categorize and filter contacts.
- **Role-Based Access Control**: Define and manage user roles (Admin, User, etc.) to control access to various resources.
- **OAuth Provider Integration**: Login with multiple authentication providers such as Google, GitHub, and Facebook.
- **Secure Password Handling**: Use Spring Security for password encryption and secure password storage.
- **RESTful APIs**: Expose RESTful endpoints for user management and contacts.
- **Swagger API Documentation**: Detailed API documentation generated with Springdoc OpenAPI.
- **DevTools**: Enable fast development with hot reloading using Spring Boot DevTools.

## 📦 Getting Started

To get a local copy up and running, follow these simple steps:

### Prerequisites

- Java 21 or later
- Maven
- MySQL (or your preferred database)
- An IDE like IntelliJ IDEA, Eclipse, or VSCode

### Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/SuhagSundarSwain/Smart-Contact-Manager-Backend.git
   ```

2. **Navigate to the project directory:**

   ```bash
   cd Smart-Contact-Manager-Backend
   ```

3. **Configure your database:**

   Update the `application.properties` file with your database details. Example:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/<your_database>
   spring.datasource.username=<your_username>
   spring.datasource.password=<your_password>
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   ```

4. **Build the project:**

   ```bash
   mvn clean install
   ```

5. **Run the application:**

   ```bash
   mvn spring-boot:run
   ```

6. **Access the application:**

   Once the application is running, you can access the backend API through:

   ```bash
   http://localhost:8080
   ```

### Frontend Setup

To access the frontend repository and learn about its setup:

- **Frontend Repo**: [Smart Contact Manager frontend](https://github.com/SuhagSundarSwain/Smart-Contact-Manager-Frontend)


## 🎛️ API Documentation

The backend exposes several RESTful APIs for interacting with the application. Check the `src/main/resources/static/swagger-ui.html` for detailed API documentation using Swagger.API documentation is available via Swagger UI at:

```bash
http://localhost:8080/api-docs
```

You can explore and test all the available APIs from the Swagger UI interface.

## ⚙️ Configuration

To customize your application, edit the `application.properties` file located in `src/main/resources`. You can configure:

- **Server Port**: Default is `8080`
- **Database Configuration**: Set up your database URL, username, and password.
- **JWT Settings**: Modify the JWT secret key, expiration time, and other token settings.
- **OAuth Settings**: Configure third-party login providers such as Google, GitHub, or Facebook.

Example of JWT configuration:

```properties
jwt.secret=my_jwt_secret
jwt.expiration=3600000
```

## 🤝 Contributing

We welcome contributions to improve the Smart Contact Manager Backend! To contribute:

1. Fork the repository
2. Create a new branch (`git checkout -b feature/your-feature`)
3. Make your changes
4. Commit your changes (`git commit -am 'Add new feature'`)
5. Push to the branch (`git push origin feature/your-feature`)
6. Create a new Pull Request

## 📜 License

Distributed under the MIT License. See `LICENSE` for more information.

## 📞 Contact

For any queries or support, please reach out to:

- **Suhag Sundar Swain** - [suhagsundarswain1@gmail.com](mailto:suhagsundarswain1@gmail.com)

## 🌟 Acknowledgements

- [Spring Boot](https://spring.io/projects/spring-boot)
- [JWT](https://jwt.io/)
- [Swagger](https://swagger.io/)
- [MySQL](https://www.mysql.com/)
- [Spring Security](https://spring.io/projects/spring-security)

---

Thank you for checking out the Smart Contact Manager Backend project!
