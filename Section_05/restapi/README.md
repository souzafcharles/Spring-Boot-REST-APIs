# Project Requirements and Configurations:

## Requirements Identified from pom.xml:

- **Project Metadata:**
    - Build Tool: `Maven`.
    - Java Version: `21`.
    - Spring Boot Version: `3.4.4`.
    - Group ID: `com.github.souzafcharles`.
    - Artifact Name: `restapi`.
    - Project URL: `https://github.com/souzafcharles/Spring-Boot-REST-APIs/Section05/restapi`.
    - Project Description: `Section 05 project for Spring Boot`.

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

# Requirements for `Person` Entity Class

## **Entity Class Design**

- **Purpose:** The `Person` entity class is designed to represent personal data and is serializable for use in a
  distributed system context.

## **Attributes and Annotations**

- Define the following attributes:
    - `id`: Represents the unique identifier for the `Person` entity.
    - `firstName`: Represents the first name of the person.
    - `lastName`: Represents the last name of the person.
    - `address`: Represents the address of the person.
    - `gender`: Represents the gender of the person.

## **Constructors**

- Implement the following constructors:
    - A **no-argument constructor** to satisfy the requirements for JavaBeans.
    - A parameterised constructor may be added for convenience (e.g.,
      `public Person(Long id, String firstName, String lastName)`), though it is not required.

## **Accessors and Mutators**

- Implement `getters` and `setters` for all attributes to allow controlled access and updates to entity data:
    - `public Long getId()` and `public void setId(Long id)`
    - `public String getFirstName()` and `public void setFirstName(String firstName)`
    - `public String getLastName()` and `public void setLastName(String lastName)`
    - `public String getAddress()` and `public void setAddress(String address)`
    - `public String getGender()` and `public void setGender(String gender)`

## **Equals and HashCode**

- **`equals()` Method:**
    - Override the `equals()` method to compare instances of the `Person` class based on the `id` attribute:
        - Example: `return Objects.equals(id, person.id);`
- **`hashCode()` Method:**
    - Override the `hashCode()` method to generate a hash code for a `Person` object using the `id` attribute:
        - Example: `return Objects.hashCode(id);`

## **Serializable Interface**

- **Implement the Serializable Interface:**
    - The `Person` class implements `Serializable` to support object serialisation for scenarios such as transferring
      objects between systems.

## **Documentation and Convention**

- Follow best practices in naming conventions, and ensure that the serial version UID (`serialVersionUID`) is included:
    - Example: `private static final long serialVersionUID = 1L;`

---

# Requirements for `PersonService` Class

## **Purpose**

- The `PersonService` class is a service layer component designed to encapsulate the business logic for managing
  `Person` entities. It facilitates the retrieval, creation, updating, and deletion of `Person` records.

## **Service Layer Responsibilities**

- Serve as an intermediary between the controller layer and data layer.
- Implement methods to manage and manipulate `Person` data.

## **Attributes**

- `AtomicLong counter`: Used for generating unique IDs for `Person` instances in the absence of a persistent database.
- `Logger logger`: Utilised for logging actions performed by the service.

## **Methods**

1. **`findAll()`**
    - **Purpose:** Retrieve a list of all `Person` records.
    - **Return Type:** `List<Person>`
    - **Implementation:** Create mock `Person` objects for demonstration purposes.

2. **`findById(String id)`**
    - **Purpose:** Retrieve a single `Person` record by its identifier.
    - **Return Type:** `Person`
    - **Implementation:** Create and return a mock `Person` object.

3. **`create(Person person)`**
    - **Purpose:** Add a new `Person` record to the system.
    - **Return Type:** `Person`
    - **Implementation:** Log the action and return the received `Person` instance.

4. **`update(Person person)`**
    - **Purpose:** Update an existing `Person` record.
    - **Return Type:** `Person`
    - **Implementation:** Log the action and return the updated `Person` instance.

5. **`delete(String id)`**
    - **Purpose:** Remove a `Person` record by its identifier.
    - **Return Type:** `void`
    - **Implementation:** Log the deletion operation.

6. **`mockPerson(int i)` (Private Helper Method)**
    - **Purpose:** Generate mock `Person` instances for testing or demonstration purposes.
    - **Return Type:** `Person`
    - **Implementation:** Populate and return a `Person` object with predefined attributes.

## **Logging**

- Use the `Logger` instance to log all operations performed by the service for debugging and monitoring purposes.

---

# Requirements for `PersonController` Class

## **Purpose**

- The `PersonController` class is a REST controller responsible for handling HTTP requests related to `Person` entities.
  It provides endpoints for CRUD operations.

## **Controller Layer Responsibilities**

- Map incoming HTTP requests to appropriate service layer methods.
- Handle RESTful operations for managing `Person` data.

## **Annotations**

- Annotate the class with `@RestController` to define it as a RESTful web service.
- Use `@RequestMapping("/person")` at the class level to map requests to the base URL `/person`.

## **Endpoints**

1. **`GET /person`**
    - **Method:** `findAll()`
    - **Purpose:** Retrieve a list of all `Person` records.
    - **Consumes:** None
    - **Produces:** `application/json`
    - **Mapped Annotation:** `@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)`

2. **`GET /person/{id}`**
    - **Method:** `findById(String id)`
    - **Purpose:** Retrieve a single `Person` record by its identifier.
    - **Consumes:** None
    - **Produces:** `application/json`
    - **Mapped Annotation:**
      `@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)`

3. **`POST /person`**
    - **Method:** `create(Person person)`
    - **Purpose:** Add a new `Person` record to the system.
    - **Consumes:** `application/json`
    - **Produces:** `application/json`
    - **Mapped Annotation:**
      `@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)`

4. **`PUT /person`**
    - **Method:** `update(Person person)`
    - **Purpose:** Update an existing `Person` record.
    - **Consumes:** `application/json`
    - **Produces:** `application/json`
    - **Mapped Annotation:**
      `@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)`

5. **`DELETE /person/{id}`**
    - **Method:** `delete(String id)`
    - **Purpose:** Remove a `Person` record by its identifier.
    - **Consumes:** None
    - **Produces:** None
    - **Mapped Annotation:** `@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)`

## **Dependency Injection**

- Autowire an instance of `PersonService` to delegate business logic.

## **HTTP Media Types**

- Handle both input and output data using `application/json` media type.

## **RESTful Conventions**

- Use appropriate HTTP status codes for each operation to ensure compliance with RESTful standards.

---

## Requirements for Exception Handling Classes:

### Requirements for `ExceptionResponse` Record:

- **Record Definition:**
    - Define the `ExceptionResponse` class as a `record` to encapsulate immutable error details.

- **Attributes:**
    - Include the following attributes with appropriate types:
        - `timestamp`: A `Date` object to log the time of the error.
        - `message`: A `String` to store the error message.
        - `details`: A `String` to include specific details about the error.

- **Automatic Methods:**
    - Leverage the `record` feature to generate automatic getters for attributes.

---

### Requirements for `UnsupportedMathOperationException` Class:

- **Custom Exception Implementation:**
    - Extend the `RuntimeException` class to create a custom exception.

- **Annotation:**
    - Annotate the class with `@ResponseStatus(HttpStatus.BAD_REQUEST)` to set the HTTP response status to `BAD_REQUEST`
      for this exception.

- **Constructor:**
    - Define a constructor that accepts a `String message` to initialise the exception message.

---

### Requirements for `CustomEntityResponseHandler` Class:

- **Controller Advice Definition:**
    - Annotate the class with `@ControllerAdvice` to enable centralised exception handling across controllers.
    - Use `@RestController` for RESTful handling.

- **Method for Generic Exceptions:**
    - **Method Name:** `handleAllExceptions(Exception e, WebRequest request)`.
    - **Functionality:**
        - Handle generic exceptions.
        - Create an `ExceptionResponse` instance with a timestamp, error message, and request details.
        - Return a `ResponseEntity` with the `ExceptionResponse` and HTTP status `INTERNAL_SERVER_ERROR`.

- **Method for `UnsupportedMathOperationException`:**
    - **Method Name:** `handleBadRequestExceptions(Exception e, WebRequest request)`.
    - **Functionality:**
        - Handle exceptions of type `UnsupportedMathOperationException`.
        - Create an `ExceptionResponse` instance with a timestamp, error message, and request details.
        - Return a `ResponseEntity` with the `ExceptionResponse` and HTTP status `BAD_REQUEST`.

- **Attributes and Return Types:**
    - Include proper error handling and informative responses to aid debugging.