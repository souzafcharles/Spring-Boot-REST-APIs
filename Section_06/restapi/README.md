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

## Requirements for `MathController` Class:

- **Controller Layer Definition:**
    - Annotate the class with `@RestController` to define it as a RESTful controller.
    - Use `@RequestMapping("/math")` at the class level to establish a base URL for all endpoints.

- **Service Dependency:**
    - Instantiate a `SimpleMath` object to perform mathematical operations.

- **Endpoint Definitions:**
    - **Sum Operation:**
        - Endpoint: `/math/sum/{numberOne}/{numberTwo}`.
        - Use `@RequestMapping` to map the `sum` method to handle HTTP requests.
        - Accept two path variables: `numberOne` and `numberTwo`.
        - Validate that both variables are numeric using `NumberConverter.isNumeric`.
        - Throw `UnsupportedMathOperationException` if validation fails.
        - Perform the sum operation with `math.sum`.

    - **Subtraction Operation:**
        - Endpoint: `/math/subtraction/{numberOne}/{numberTwo}`.
        - Similar setup and validation as the `sum` method.
        - Perform subtraction with `math.subtraction`.

    - **Multiplication Operation:**
        - Endpoint: `/math/multiplication/{numberOne}/{numberTwo}`.
        - Similar setup and validation as the `sum` method.
        - Perform multiplication with `math.multiplication`.

    - **Division Operation:**
        - Endpoint: `/math/division/{numberOne}/{numberTwo}`.
        - Similar setup and validation as the `sum` method.
        - Perform division with `math.division`.

    - **Mean Operation:**
        - Endpoint: `/math/mean/{numberOne}/{numberTwo}`.
        - Similar setup and validation as the `sum` method.
        - Perform mean calculation with `math.mean`.

    - **Square Root Operation:**
        - Endpoint: `/math/squareroot/{number}`.
        - Accept a single path variable `number`.
        - Validate the variable is numeric using `NumberConverter.isNumeric`.
        - Throw `UnsupportedMathOperationException` if validation fails.
        - Perform square root calculation with `math.squareRoot`.

- **Error Handling:**
    - Throw `UnsupportedMathOperationException` with a clear message ("Please set a numeric value!") for invalid inputs.

- **Method Return Type:**
    - Return the result of each operation as a `Double`.

---

## Requirements for `SimpleMath` Class:

- **Mathematical Operations:**
    - Implement methods to perform common mathematical calculations:
        - `sum(Double numberOne, Double numberTwo)`: Returns the sum of two numbers.
        - `subtraction(Double numberOne, Double numberTwo)`: Returns the difference between two numbers.
        - `multiplication(Double numberOne, Double numberTwo)`: Returns the product of two numbers.
        - `division(Double numberOne, Double numberTwo)`: Returns the quotient of two numbers.
        - `mean(Double numberOne, Double numberTwo)`: Returns the arithmetic mean of two numbers.
        - `squareRoot(Double number)`: Returns the square root of a number.

- **Parameter Types:**
    - Accept `Double` as the parameter type for all methods to handle decimal calculations.

- **Return Type:**
    - Ensure all methods return a `Double` value.

- **Usage of Built-In Methods:**
    - Leverage `Math.sqrt(number)` for computing square roots.

---

## Requirements for `NumberConverter` Class:

- **Conversion Method:**
    - **Method Name:** `convertToDouble(String strNumber)`.
    - **Functionality:**
        - Accepts a `String` parameter to convert numeric text into a `Double`.
        - Replace commas with dots (`","` to `"."`) for proper decimal formatting.
        - Validates that the input string is neither `null` nor empty; throws `UnsupportedMathOperationException` with
          the message `"Please set a numeric value!"` for invalid input.

- **Validation Method:**
    - **Method Name:** `isNumeric(String strNumber)`.
    - **Functionality:**
        - Accepts a `String` parameter to check if it contains a valid numeric value.
        - Replace commas with dots (`","` to `"."`) for decimal formatting.
        - Returns `true` if the input matches the numeric pattern `[-+]?[0-9]*\\.?[0-9]+`, otherwise returns `false`.
        - Treats `null` or empty inputs as non-numeric.

- **Error Handling:**
    - Throw `UnsupportedMathOperationException` with a descriptive message for invalid conversion attempts.

- **Parameter Types:**
    - Accept `String` as the parameter type for both methods.

- **Return Types:**
    - For `convertToDouble`: Returns the converted value as `Double`.
    - For `isNumeric`: Returns a `boolean` indicating whether the input is numeric.

- **Decimal Compatibility:**
    - Ensure support for both `R$` (Brazilian format) and `USD` (international format) by replacing localized
      delimiters.

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
    - Annotate the class with `@ResponseStatus(HttpStatus.BAD_REQUEST)` to set the HTTP response status to `BAD_REQUEST` for this exception.

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