# Smart-Contact-Manager-Backend

Welcome to the **Smart Contact Manager Backend** project! This Spring Boot application is designed to efficiently handle user management and contacts, integrating features like authentication and role management with JWT-based security.

## 🚀 Features

- **User Authentication & Authorization**: Secure user login and registration with JWT tokens.
- **User Profile Management**: Manage user details, including profile pictures and contact information.
- **Role-Based Access Control**: Define and manage user roles to control access to various resources.
- **Provider Integration**: Support for multiple authentication providers such as Google, GitHub, and Facebook.

## 📦 Getting Started

To get a local copy up and running follow these simple steps:

### Prerequisites

- Java 21 or later
- Maven
- MySQL (or your preferred database)
- An IDE like IntelliJ IDEA or Eclipse or VsCode

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
    spring.datasource.url=jdbc:mysql://localhost:3306/your_database
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    ```

4. **Build the project:**

    ```bash
    mvn clean install
    ```

5. **Run the application:**

    ```bash
    mvn spring-boot:run
    ```

## 🎛️ API Documentation

The backend exposes several RESTful APIs for interacting with the application. Check the `src/main/resources/static/swagger-ui.html` for detailed API documentation using Swagger.

## ⚙️ Configuration

To customize your application, edit the `application.properties` file located in `src/main/resources`. Here you can configure various aspects such as:

- Server port
- Database settings
- JWT secret key and expiration

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

---

Thank you for checking out the Smart Contact Manager Backend project!
