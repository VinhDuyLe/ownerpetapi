# Spring Boot RESTful API with MongoDB

This project implements a simple RESTful API using Spring Boot and Spring Data MongoDB to manage `Owner` and `Pet` information. It demonstrates basic CRUD (Create, Read, Update, Delete) operations with a MongoDB Atlas backend.

## Table of Contents

* [Features](#features)
* [Technologies Used](#technologies-used)
* [Prerequisites](#prerequisites)
* [Project Setup](#project-setup)
* [MongoDB Atlas Configuration](#mongodb-atlas-configuration)
* [Running the Application](#running-the-application)
* [API Endpoints](#api-endpoints)
* [Testing with Postman](#testing-with-postman)
* [Project Structure](#project-structure)
* [Notes](#notes)

## Features

* **Owner Management:** Create, retrieve, update, and delete owner records.
* **Pet Management:** Add pets to existing owners.
* **MongoDB Integration:** Utilizes Spring Data MongoDB for seamless interaction with a MongoDB database.
* **RESTful Endpoints:** Exposes standard RESTful API endpoints for resource manipulation.

## Technologies Used

* **Java:** 1.8
* **Spring Boot:** 2.7.0
* **Spring Data MongoDB:** For database interaction
* **Lombok:** For reducing boilerplate code (getters, setters, constructors)
* **Maven:** For project build automation
* **MongoDB Atlas:** Cloud-hosted MongoDB database

## Prerequisites

Before you begin, ensure you have the following installed:

* **Java Development Kit (JDK) 1.8:**
    * Download from Oracle: [JDK 8](https://www.oracle.com/java/technologies/javase/javase8-downloads.html)
* **Apache Maven 3.x:**
    * Download from Apache: [Maven](https://maven.apache.org/download.cgi)
* **MongoDB Atlas Account:**
    * Sign up: [MongoDB Atlas](https://cloud.mongodb.com/)
    * A cluster (e.g., M0 Free Tier) should be set up and running.
* **Postman (or similar API client):** For testing the endpoints.
    * Download: [Postman](https://www.postman.com/downloads/)

## Project Setup

1.  **Clone the repository:** (Assuming you have your project in a Git repository)
    ```bash
    git clone <your-repository-url>
    cd ownerpetapi
    ```
    If you've been working locally, just navigate to your project's root directory.

2.  **Build the project:**
    ```bash
    mvn clean install
    ```
    This command compiles the code, runs tests, and packages the application into a JAR file.

## MongoDB Atlas Configuration

You need to configure your Spring Boot application to connect to your MongoDB Atlas cluster.

1.  **Get your MongoDB Atlas Connection String:**
    * Log in to your MongoDB Atlas account.
    * Navigate to your cluster (e.g., `Cluster0`).
    * Click "Connect" -> "Connect your application".
    * Choose "Java" and your driver version (e.g., `4.3 or later`).
    * Copy the connection string. It will look similar to this:
      `mongodb+srv://<User>:<password>@cluster0.zuxlmuq.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0`

2.  **Update `application.properties`:**
    * Open `src/main/resources/application.properties`.
    * **Replace `<password>` in the connection string with the actual password for your `User` MongoDB user.**
    * Update the `spring.data.mongodb.uri` property:
        ```properties
        spring.application.name=ownerpetapi
        server.port=8080

        # MongoDB Atlas Connection String
        # IMPORTANT: Replace YOUR_ACTUAL_PASSWORD_HERE with your MongoDB Atlas user's password
        spring.data.mongodb.uri=mongodb+srv://YOUR_ACTUAL_USER:YOUR_ACTUAL_PASSWORD_HERE@cluster0.zuxlmuq.mongodb.net/ownerpetdb?retryWrites=true&w=majority&appName=Cluster0
        ```
      *Note: `ownerpetdb` is the database name within your Atlas cluster where data will be stored.*

3.  **Configure Network Access in MongoDB Atlas:**in
    * In MongoDB Atlas, go to **Security > Network Access**.
    * Click **ADD IP ADDRESS**.
    * Add your current IP address (if running locally) or choose "Allow Access from Anywhere" (for testing, not recommended for production).
    * Click **CONFIRM**.

## Running the Application

Once MongoDB Atlas is configured and the project is built, you can run the Spring Boot application:

```bash
mvn spring-boot:run
