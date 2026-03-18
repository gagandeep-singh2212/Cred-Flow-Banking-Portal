# 💳 CredFlow Banking Portal

<div align="center">

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)

### 🚀 A Secure, Scalable Banking Portal built using Microservices Architecture

</div>

---

## 🌟 Overview

**CredFlow** is a modern **banking web application** built using **Spring Boot Microservices**.  
It demonstrates real-world backend architecture including **service discovery, API gateway, security, and transaction handling**.

This project simulates a complete **digital banking experience**:
- User registration & login  
- Account management  
- Fund transfers  
- Transaction tracking  

---

## ✨ Key Features

### 🔐 User Management
- Secure **User Registration & Login**
- Session-based authentication
- Profile management

### 🏦 Account Services
- Create and manage bank accounts
- View account balance
- Support for multiple accounts

### 💸 Transaction System
- Transfer money between accounts
- External bank transfers (simulation)
- View detailed transaction history
- Real-time balance updates

### 🎨 UI/UX
- Clean and responsive design
- Simple and intuitive workflow
- Built using Thymeleaf templates

---

## 🧩 Microservices Architecture

| Service | Port | Description |
|--------|------|------------|
| 🧭 Eureka Server | 8761 | Service Discovery |
| 🌐 API Gateway | 8081 | Routing & Load Balancing |
| 👤 User Service | 8082 | Authentication & User Management |
| 🏦 Account Service | 8083 | Account Handling |
| 💸 Transaction Service | 8084 | Transactions Processing |
| 🖥️ UI Service | 8080 | Frontend (Thymeleaf) |

---

## 🛠️ Tech Stack

### 🔙 Backend
- Java 11+
- Spring Boot
- Spring Cloud (Eureka, Gateway)
- Spring Security
- Spring Data JPA
- Circuit Breaker (Resilience)

### 🎨 Frontend
- Thymeleaf
- HTML5 / CSS3
- JavaScript
- Bootstrap

### 🗄️ Database
- MySQL

---