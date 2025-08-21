# TaskBoard.Authenticator.Boot
[![Build](https://github.com/niolikon/TaskBoard.Authenticator.Boot/actions/workflows/build-and-test.yml/badge.svg)](https://github.com/niolikon/TaskBoard.Authenticator.Boot/actions)
[![Package](https://github.com/niolikon/TaskBoard.Authenticator.Boot/actions/workflows/publish-release.yml/badge.svg)](https://github.com/niolikon/TaskBoard.Authenticator.Boot/actions)
[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](https://opensource.org/licenses/MIT)

Task Board Authenticator (Spring Case Study)

# Overview

📚 **TaskBoard.Authenticator.Boot** is a simple Web API project designed to authenticate users of the TaskBoard ecosystem.

It handles user login and token generation via Keycloak, enforcing secure access across the platform.
The service demonstrates clean integration of Spring Boot with Keycloak for centralized identity management.

---

## 🚀 Features

- **Keycloak Integration**: OAuth2/OpenID Connect authentication via Keycloak.
- **Dependency Injection**: Decouple components for better testability and maintainability.

---

## 🛠️ Getting Started

### Prerequisites

- **Java 17+**
- **Maven 3+**
- **Docker** (optional, to deploy the service on container)


### Quickstart Guide

1. Clone the repository:
   ```bash
   git clone https://github.com/niolikon/TaskBoard.Authenticator.Boot.git
   cd TaskBoard.Authenticator.Boot
   ```

2. Compile the project:
   ```bash
   mvn clean install
   ```

3. Execute the project:
   ```bash
   mvn spring-boot:run
   ```

### Deploy on container

1. Configure credentials on a .env file as follows
   ```
    DB_NAME=todolist
    DB_USER=appuser
    DB_PASSWORD=apppassword
    KEYCLOAK_DB_PASSWORD=supersecretkeycloak
    KEYCLOAK_ADMIN_PASSWORD=adminpassword
   ```

2. Compile the project:
   ```bash
   mvn clean package
   ```

3. Create project image
   ```bash
   docker build -t taskboard-authenticator-boot:latest .
   ```

4. Compose docker container
   ```bash
   docker-compose up -d
   ```

---

## 📬 Feedback

If you have suggestions or improvements, feel free to open an issue or create a pull request. Contributions are welcome!

---

## 📝 License

This project is licensed under the MIT License.

---
🚀 **Developed by Simone Andrea Muscas | Niolikon**

