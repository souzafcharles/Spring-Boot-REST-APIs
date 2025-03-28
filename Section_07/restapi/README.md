# Project Requirements and Configurations:

## Requirements Identified from pom.xml:

- **Project Metadata:**
    - Build Tool: `Maven`.
    - Java Version: `21`.
    - Spring Boot Version: `3.4.4`.
    - Group ID: `com.github.souzafcharles`.
    - Artifact Name: `restapi`.
    - Project URL: `https://github.com/souzafcharles/Spring-Boot-REST-APIs/Section07/restapi`.
    - Project Description: `Section 07 project for Spring Boot`.

- **Dependencies:**
    - **Spring Web:**
        - Add `spring-boot-starter-web` for web application development.
    - **Spring Data JPA:**
        - Include `spring-boot-starter-data-jpa` for working with data persistence using JPA.
    - **Dotenv Java:**
        - Incorporate `dotenv-java` (version `3.2.0`) for managing environment variables.
    - **MySQL Connector:**
        - Add `mysql-connector-j` as the database driver for MySQL (runtime scope).
    - **Spring Boot Devtools:**
        - Use `spring-boot-devtools` to provide development-time features (runtime scope, optional).
    - **Spring Boot Starter Test:**
        - Include `spring-boot-starter-test` for testing purposes.

---

## Requirements for `application.yml` Configuration File

### Spring Application Configuration

- Set the Spring application name to `restapi` using `spring.application.name`.

### Datasource Configuration

- Specify the database driver class name as `com.mysql.cj.jdbc.Driver` using `spring.datasource.driver-class-name`.
- Configure the database URL using the placeholder `${DATASOURCE_URL}` with `spring.datasource.url`.
- Set the database username using the placeholder `${DATASOURCE_USERNAME}` with `spring.datasource.username`.
- Set the database password using the placeholder `${DATASOURCE_PASSWORD}` with `spring.datasource.password`.

### JPA and Hibernate Configuration

- Enable automatic schema updates by setting `spring.jpa.hibernate.ddl-auto` to `update`.
- Configure Hibernate properties under `spring.jpa.properties.hibernate`:
    - Disable SQL logging by setting `spring.jpa.show-sql` to `false`.
- Disable Open Session in View by setting `spring.jpa.open-in-view` to `false`.

### Logging Configuration

- Define logging levels for specific packages:
    - Set the logging level for `com.github.souzafcharles.restapi` to `DEBUG` using
      `spring.logging.level.com.github.souzafcharles.restapi`.

### Documentation and Conventions

- Use placeholders (`${}`) for sensitive values such as database credentials to support environment-based configuration.
- Ensure meaningful comments and documentation within the configuration file for clarity and maintainability.

---

## Creation of the `.env` File:

- At the root of the project, create a file named `.env` to declare the environment variables required for the
  `MySQL` database connection.

---

## Requirements for LoadEnvironment Class:

- **Class Purpose:**

    - Create the `LoadEnvironment` class to load environment variables from a `.env` file and set them as system
      properties.

- **Load Environment Method:**

    - **Method:** `loadEnv`
    - **Purpose:** Loads environment variables from a `.env` file and sets them as system properties.
    - **Implementation Details:**
        - Use the `Dotenv.configure().load()` method from the `io.github.cdimascio.dotenv` library to load the
          environment variables.
        - Iterate over the entries using
          `dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()))` to set each
          environment variable as a system property.

- **External Library:**

    - **Library:** `io.github.cdimascio.dotenv`
    - **Purpose:** Used to load environment variables from a `.env` file. Ensure this library is included as a
      dependency in your project's build configuration.

- **Purpose:**
    - Ensure that environment variables defined in a `.env` file are loaded and accessible as system properties
      throughout the application.

---

# Backend Requirements Specification:

## Requirements for `Startup` Class

### Class Design

- **Purpose:** The `Startup` class is the entry point for the application and is responsible for initialising and
  running the Spring Boot application.

### Methods

- **`main(String[] args)` Method:**
    - **Purpose:** Bootstraps the Spring Boot application.
    - Invokes the `LoadEnvironment.loadEnv()` method to load environment-specific settings.
    - Calls `SpringApplication.run()` with the `Startup` class and command-line arguments to start the application.

### Annotations

- **`@SpringBootApplication`:**
    - Marks the class as the entry point for the Spring Boot framework.

### Documentation and Conventions

- Follow Java best practices for the `main()` method implementation and class documentation.

---

## Requirements for `Person` Entity Class

### Entity Class Design

- **Purpose:** The `Person` entity class is designed to represent personal data for persistence in a relational
  database. It is serializable and annotated with JPA for object-relational mapping.

### Attributes and Annotations

- Define the following attributes:
    - **`id`:**
        - Represents the unique identifier for the `Person` entity.
        - Annotated with `@Id` to mark it as the primary key.
        - Annotated with `@GeneratedValue(strategy = GenerationType.IDENTITY)` to enable auto-generation.
    - **`firstName`:**
        - Represents the first name of the person.
        - Annotated with `@Column(name = "first_name", nullable = false, length = 80)` to define database column
          properties.
    - **`lastName`:**
        - Represents the last name of the person.
        - Annotated with `@Column(name = "last_name", nullable = false, length = 80)` to define database column
          properties.
    - **`address`:**
        - Represents the address of the person.
        - Annotated with `@Column(nullable = false, length = 100)` to define database column properties.
    - **`gender`:**
        - Represents the gender of the person.
        - Annotated with `@Column(nullable = false, length = 6)` to define database column properties.

### Constructors

- Implement the following constructors:
    - A no-argument constructor to satisfy the requirements for JavaBeans.
    - A parameterised constructor may be added for convenience (e.g.,
      `public Person(Long id, String firstName, String lastName, String address, String gender)`), though it is not
      required.

### Accessors and Mutators

- Implement `getters` and `setters` for all attributes to allow controlled access and updates to entity data:
    - `public Long getId()` and `public void setId(Long id)`
    - `public String getFirstName()` and `public void setFirstName(String firstName)`
    - `public String getLastName()` and `public void setLastName(String lastName)`
    - `public String getAddress()` and `public void setAddress(String address)`
    - `public String getGender()` and `public void setGender(String gender)`

### Equals and HashCode

- **`equals()` Method:**
    - Override the `equals()` method to compare instances of the `Person` class based on the `id` attribute:
        - Example: `return Objects.equals(id, person.id);`
- **`hashCode()` Method:**
    - Override the `hashCode()` method to generate a hash code for a `Person` object using the `id` attribute:
        - Example: `return Objects.hashCode(id);`

### JPA Annotations

- **`@Entity`:**
    - Marks the class as a JPA entity to map it to a database table.
- **`@Table(name = "tb_person")`:**
    - Specifies the name of the database table as `tb_person`.

### Serializable Interface

- **Implement the Serializable Interface:**
    - The `Person` class implements `Serializable` to support object serialisation for scenarios such as transferring
      objects between systems.

### Documentation and Conventions

- Follow best practices in naming conventions, and ensure that the serial version UID (`serialVersionUID`) is included:
    - Example: `private static final long serialVersionUID = 1L;`
- Ensure meaningful JPA annotations for attributes to properly define database schema constraints.

---

## Requirements for `PersonDTO` Class

### Class Design

- **Purpose:** The `PersonDTO` class is a Data Transfer Object designed to represent `Person` data in scenarios where
  entities are mapped to lightweight objects for external communication or processing.

### Attributes

- Define the following attributes:
    - `id`: Represents the unique identifier for the `PersonDTO` object.
    - `firstName`: Represents the first name of the person.
    - `lastName`: Represents the last name of the person.
    - `address`: Represents the address of the person.
    - `gender`: Represents the gender of the person.

### Constructors

- Implement the following constructors:
    - A no-argument constructor to satisfy JavaBeans conventions and allow object creation without initialisation.
    - A parameterised constructor may be added for convenience if required (e.g.,
      `public PersonDTO(Long id, String firstName, String lastName, String address, String gender)`).

### Accessors and Mutators

- Implement `getters` and `setters` for all attributes to allow controlled access and updates to object data:
    - `public Long getId()` and `public void setId(Long id)`
    - `public String getFirstName()` and `public void setFirstName(String firstName)`
    - `public String getLastName()` and `public void setLastName(String lastName)`
    - `public String getAddress()` and `public void setAddress(String address)`
    - `public String getGender()` and `public void setGender(String gender)`

### Serializable Interface

- **Implement the Serializable Interface:**
    - The `PersonDTO` class implements `Serializable` to facilitate object serialisation and deserialisation for
      scenarios such as network communication or file storage.

### Documentation and Conventions

- Follow best practices in naming conventions and ensure clarity in method definitions.
- Include the serial version UID (`serialVersionUID`) for compatibility during serialisation and deserialisation:
    - Example: `private static final long serialVersionUID = 1L;`.
- Document the purpose and use of the class as part of the application's data transfer layer.

---

## Requirements for `PersonRepository` Interface

### Interface Design

- **Purpose:** The `PersonRepository` interface provides database access functionality for the `Person` entity. It
  extends `JpaRepository` to leverage Spring Data JPA features.

### Superclass

- **Extends `JpaRepository`:**
    - Allows the interface to inherit methods for performing CRUD operations, paging, and sorting on `Person` entities.

### Methods

- By extending `JpaRepository`, the following methods are available:
    - **CRUD Methods:**
        - `List<Person> findAll()` – Retrieves all `Person` records.
        - `Optional<Person> findById(Long id)` – Retrieves a `Person` record by its unique ID.
        - `Person save(Person person)` – Saves or updates a `Person` entity.
        - `void deleteById(Long id)` – Deletes a `Person` record by its unique ID.

- Additional methods can be added if custom query requirements arise, leveraging Spring Data JPA's query derivation
  feature (e.g., `List<Person> findByLastName(String lastName)`).

### Annotations

- **`@Repository`:**
    - The `PersonRepository` does not explicitly require this annotation as it is automatically recognised by Spring
      Boot, but it may be added for clarity and convention.

### Documentation and Conventions

- Adhere to Spring Data JPA conventions and naming standards for method declarations.
- Ensure that any custom methods are well-documented with meaningful comments describing their purpose.

---

## Requirements for `PersonService` Class

### Entity Class Design

- **Purpose:** The `PersonService` class provides the business logic layer for managing `Person` entities. It interacts
  with the repository to perform CRUD operations and ensures data integrity.

### Attributes and Dependencies

- Define the following attributes:
    - `counter`: An `AtomicLong` instance for managing unique counters (not currently used in methods, but provided for
      scalability).
    - `logger`: A `Logger` instance for logging operations and activity.
    - `repository`: An instance of `PersonRepository`, auto-wired to interact with the database.

### Methods

- Implement the following methods:

    - **`findAll()` Method:**
        - **Purpose:** Retrieves all `Person` entities from the repository.
        - Logs the operation with a message: *"Finding all People!"*
        - Returns a `List<Person>` containing all records.

    - **`findById(Long id)` Method:**
        - **Purpose:** Retrieves a `Person` entity by its unique ID.
        - Logs the operation with a message: *"Finding one Person!"*
        - Returns a `Person` object if found, otherwise throws a `ResourceNotFoundException`.

    - **`create(Person person)` Method:**
        - **Purpose:** Adds a new `Person` entity to the repository.
        - Logs the operation with a message: *"Creating one Person!"*
        - Returns the saved `Person` object.

    - **`update(Person person)` Method:**
        - **Purpose:** Updates an existing `Person` entity.
        - Logs the operation with a message: *"Updating one Person!"*
        - Validates the `id` of the input `Person` object and ensures the entity exists in the repository.
        - Updates all fields (`firstName`, `lastName`, `address`, and `gender`).
        - Returns the updated `Person` object.

    - **`delete(Long id)` Method:**
        - **Purpose:** Deletes a `Person` entity by its unique ID.
        - Logs the operation with a message: *"Deleting one Person!"*
        - Validates the ID and ensures the entity exists, otherwise throws a `ResourceNotFoundException`.

### Logging

- Use the `Logger` instance to log meaningful information for all operations (`findAll`, `findById`, `create`, `update`,
  `delete`).

### Documentation and Conventions

- Follow best practices in method naming, class design, and dependency injection.
- Ensure meaningful exception messages in `ResourceNotFoundException` to assist with debugging.

---

## Requirements for `PersonController` Class

### Class Design

- **Purpose:** The `PersonController` class provides a RESTful API for managing `Person` entities. It delegates the
  business logic to the `PersonService` class.

### Endpoints

- Define the following endpoints:

    - **GET `/person` Endpoint:**
        - **Purpose:** Fetches all `Person` entities.
        - **Produces:** `application/json`
        - Returns a `List<Person>`.

    - **GET `/person/{id}` Endpoint:**
        - **Purpose:** Fetches a `Person` entity by its unique ID.
        - **Produces:** `application/json`
        - Returns a `Person` object.

    - **POST `/person` Endpoint:**
        - **Purpose:** Adds a new `Person` entity.
        - **Consumes/Produces:** `application/json`
        - Returns the saved `Person` object.

    - **PUT `/person` Endpoint:**
        - **Purpose:** Updates an existing `Person` entity.
        - **Consumes/Produces:** `application/json`
        - Returns the updated `Person` object.

    - **DELETE `/person/{id}` Endpoint:**
        - **Purpose:** Deletes a `Person` entity by its unique ID.
        - **Returns:** HTTP 204 No Content.

### Annotations

- Use Spring annotations for dependency injection and RESTful API specifications:
    - `@RestController` for marking the class as a REST controller.
    - `@RequestMapping("/person")` for base URL mapping.
    - `@Autowired` for injecting the `PersonService`.

### Validation and Error Handling

- Ensure meaningful exception handling:
    - Return appropriate HTTP status codes for each operation.
    - Throw `ResourceNotFoundException` when applicable.
    - Validate `@PathVariable` and `@RequestBody` input.

### Documentation and Conventions

- Ensure all endpoints are well-documented with meaningful comments for clarity.
- Adhere to RESTful API best practices, such as HTTP methods and status codes.

---

## Requirements for `ExceptionResponse` Record

### Design

- **Purpose:** The `ExceptionResponse` record is designed to encapsulate exception details in a structured format for
  response handling.

### Attributes

- Define the following attributes:
    - `timestamp`: Captures the date and time of the exception occurrence.
    - `message`: Describes the exception message.
    - `details`: Provides additional details about the exception, typically the request description.

### Documentation and Conventions

- Ensure all attributes are immutable and follow the record-specific conventions of Java.
- Document the purpose and usage of this record in exception handling processes.

---

## Requirements for `ResourceNotFoundException` Class

### Class Design

- **Purpose:** The `ResourceNotFoundException` class is a custom exception used to indicate that a requested resource
  could not be found.

### Constructor

- **`ResourceNotFoundException(String message)` Constructor:**
    - Accepts a `String` argument to specify the exception message.
    - Passes the message to the superclass constructor (`RuntimeException`).

### Annotations

- **`@ResponseStatus(HttpStatus.BAD_REQUEST)`**
    - Marks the exception with the `BAD_REQUEST` HTTP status, indicating a client error.

### Documentation and Conventions

- Include a clear description of the use case for this exception.
- Adhere to Java naming conventions for custom exceptions.

---

## Requirements for `CustomEntityResponseHandler` Class

### Class Design

- **Purpose:** The `CustomEntityResponseHandler` class is designed to provide a centralised exception handling mechanism
  for the application by extending `ResponseEntityExceptionHandler`.

### Annotations

- **`@ControllerAdvice`:**
    - Marks the class as a global exception handler.
- **`@RestController`:**
    - Combines with `@ControllerAdvice` to allow the class to return JSON responses.

### Methods

- **`handleAllExceptions(Exception ex, WebRequest request)` Method:**
    - **Purpose:** Handles generic exceptions not explicitly handled elsewhere.
    - Creates an `ExceptionResponse` object containing:
        - `timestamp`: The current date and time.
        - `message`: The exception message.
        - `details`: The request description.
    - Returns a `ResponseEntity` with the `ExceptionResponse` object and `HttpStatus.INTERNAL_SERVER_ERROR`.

- **`handleNotFoundExceptions(ResourceNotFoundException ex, WebRequest request)` Method:**
    - **Purpose:** Handles `ResourceNotFoundException` specifically.
    - Creates an `ExceptionResponse` object similar to `handleAllExceptions`.
    - Returns a `ResponseEntity` with the `ExceptionResponse` object and `HttpStatus.NOT_FOUND`.

### Documentation and Conventions

- Document the purpose and usage of each handler method.
- Ensure meaningful and user-friendly exception messages.
- Adhere to best practices in exception handling, such as returning appropriate HTTP status codes and maintaining
  detailed logging where necessary.

---

## Requirements for `ObjectMapper` Class

### Class Design

- **Purpose:** The `ObjectMapper` class is a utility designed to facilitate object mapping between different types using
  the Dozer Mapper framework. It enables seamless transformation of objects and lists of objects.

### Attributes

- **`mapper`:**
    - A static `Mapper` instance created using `DozerBeanMapperBuilder.buildDefault()`.
    - Used to perform mappings between object types.

### Methods

- **`parseObject(O origin, Class<D> destination)` Method:**
    - **Purpose:** Maps an object of type `O` (origin) to type `D` (destination).
    - Accepts:
        - `origin`: The source object.
        - `destination`: The class type of the target object.
    - Returns:
        - A mapped instance of type `D`.

- **`parseListObjects(List<O> origin, Class<D> destination)` Method:**
    - **Purpose:** Maps a list of objects of type `O` (origin) to a list of objects of type `D` (destination).
    - Accepts:
        - `origin`: The source list of objects.
        - `destination`: The class type of the target objects.
    - Returns:
        - A `List<D>` containing mapped objects.

---

## Requirements for `PersonDTOv2` Class

### Class Design

- **Purpose:** The `PersonDTOv2` class is designed as a Data Transfer Object (DTO) for transmitting personal information
  between layers of the application. It is serialisable to support distributed system scenarios.

### Attributes and Annotations

- Define the following attributes:
    - `id`: Represents the unique identifier for the `PersonDTOv2` object.
    - `firstName`: Represents the first name of the person.
    - `lastName`: Represents the last name of the person.
    - `birthday`: Represents the date of birth of the person.
    - `address`: Represents the address of the person.
    - `gender`: Represents the gender of the person.

### Constructors

- Implement the following constructors:
    - A no-argument constructor for satisfying JavaBean conventions.
    - Additional parameterised constructors may be implemented for convenience, though they are not mandatory.

### Accessors and Mutators

- Implement `getters` and `setters` for all attributes to allow controlled access and updates:
    - Example: `public Long getId()` and `public void setId(Long id)`.

### Serializable Interface

- **Implement the Serializable Interface:**
    - The `PersonDTOv2` class implements `Serializable` to support object serialisation for scenarios such as remote
      communication or file storage.
    - Ensure that the serial version UID (`serialVersionUID`) is included:
        - Example: `private static final long serialVersionUID = 1L;`.

### Documentation and Conventions

- Follow best practices in naming conventions and class documentation.
- Ensure code readability and maintainability.

---

## Requirements for `PersonMapper` Class

### Class Design

- **Purpose:** The `PersonMapper` class provides custom methods to convert between `Person` entity objects and
  `PersonDTOv2` objects, ensuring compatibility and proper data handling between layers of the application.

### Methods

- **`convertEntityToDTO(Person person)` Method:**
    - **Purpose:** Converts a `Person` entity object into a `PersonDTOv2` object.
    - Maps attributes from the `Person` object to the corresponding attributes in the `PersonDTOv2` object.
    - Sets the `birthday` attribute to a new `Date` object, assuming current date as default (could be revised for
      accuracy).

- **`convertDTOtoEntity(PersonDTOv2 person)` Method:**
    - **Purpose:** Converts a `PersonDTOv2` object into a `Person` entity object.
    - Maps attributes from the `PersonDTOv2` object to the corresponding attributes in the `Person` entity object.
    - The `birthday` attribute is commented out in this implementation for potential revision.

### Annotations

- **`@Service`:**
    - Indicates that the class is a Spring-managed service and is part of the application's business logic layer.

### Documentation and Conventions

- Follow Java best practices for method implementation and class documentation.
- Ensure code readability, maintainability, and adherence to standard naming conventions.