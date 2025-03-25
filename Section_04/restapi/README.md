# Project Requirements and Configurations:

## Requirements Identified from pom.xml:

- **Project Metadata:**
    - Build Tool: `Maven`.
    - Java Version: `21`.
    - Spring Boot Version: `3.4.4`.
    - Group ID: `com.github.souzafcharles`.
    - Artifact Name: `restapi`.
    - Project URL: `https://github.com/souzafcharles/Spring-Boot-REST-APIs/Section04/restapi`.
    - Project Description: `Section 04 project for Spring Boot`.

- **Dependencies:**
    - **Spring Web**: For web application development.
    - **Spring Boot Devtools**: For development-time support (runtime scope, optional).

---

# Backend Requirements Specification:

## Requirements for Spring Initializr Setup:

- **Spring Boot Application Configuration:**
    - Annotate the `Startup` class with `@SpringBootApplication` to enable auto-configuration and component scanning.

- **Main Method:**
    - Define the `main` method as the entry point of the application.
    - Use `SpringApplication.run(Startup.class, args)` to launch the application context.

---

## Requirements for `Greeting` Record Class:

- **Record Definition:**
    - Define the `Greeting` class as a record to encapsulate immutable data with two fields: `id` and `content`.

- **Attributes:**
    - The `id` attribute should be of type `Long` to represent a unique identifier.
    - The `content` attribute should be of type `String` to store message content.

- **Automatic Methods:**
    - Leverage the record feature to automatically generate `getters` for `id` and `content`.

---

## Requirements for `GreetingController` Class:

- **Controller Layer Definition:**
    - Annotate the class with `@RestController` to designate it as a controller that handles RESTful requests.

- **Endpoint Mapping:**
    - Define an endpoint `/greeting` to handle HTTP GET requests using the `@RequestMapping` annotation.

- **Request Parameters:**
    - Accept an optional query parameter `name` with a default value of `"World"` using `@RequestParam`.

- **Response Construction:**
    - Use an `AtomicLong` to generate a unique identifier for each `Greeting` instance.
    - Return a `Greeting` instance with the `id` and a formatted message: `"Hello, [name]!"`.

---