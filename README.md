#  Quantity Measurement App

A Java-based application designed using Object-Oriented Principles and N-Tier Architecture to handle unit conversions, comparisons, and arithmetic operations across different measurement types like Length, Volume, Weight, and Temperature.

---

##  Project Overview

This project demonstrates clean coding practices, layered architecture, and incremental development using multiple Use Cases (UCs). Each UC is implemented in a separate branch, while the `main` branch contains the final integrated version.

---

##  Tech Stack

* Java 8+
* JUnit (Testing)
* Maven (Build Tool)
* IntelliJ IDEA
* Spring Boot (Advanced UCs)
* JDBC (Database Integration)
* Google OAuth (Authentication)

---

---

## Use Cases (UCs) Overview

Each Use Case is implemented in a separate branch:

---

### 🔹 UC1 – Basic Equality Check

* Compare two quantities of same unit
* Example: `1 feet == 1 feet`

---

### 🔹 UC2 – Type Safety Refactor

* Introduced enums/types for units
* Prevents invalid comparisons

---

### 🔹 UC3 – Unit Conversion

* Conversion between units
* Example: `1 feet == 12 inches`

---

### 🔹 UC4 – Addition of Quantities

* Addition after conversion
* Example: `1 feet + 2 inches`

---

### 🔹 UC5 – Unit Conversion Enhancement

* Improved conversion handling
* Supports more unit combinations

---

### 🔹 UC6 – Unit Addition

* Adds quantities with conversion support

---

### 🔹 UC7 – Target Unit Addition

* Convert result into a specific target unit

---

### 🔹 UC8 – Standalone Unit Handling

* Modular and reusable unit logic

---

### 🔹 UC9 – Weight Measurement

* Added support for weight units
* Example: kg, grams

---

### 🔹 UC10 – Generic Quantity Class

* Introduced generic class for all measurements
* Improved scalability

---

### 🔹 UC11 – Volume Measurement Equality

* Added volume unit comparisons
* Example: liters, milliliters

---

### 🔹 UC12 – Subtraction & Division Operations

* Supports subtraction and division
* Extends arithmetic capabilities

---

### 🔹 UC13 – Centralized Arithmetic Logic

* Common logic for all operations
* Reduces redundancy

---

### 🔹 UC14 – Temperature Measurement with Selective Arithmetic

* Supports temperature conversions
* Restricts invalid operations
* Example: Celsius ↔ Fahrenheit

---

### 🔹 UC15 – N-Tier Architecture

* Introduced layered architecture:

  * Controller
  * Service
  * Repository

---

### 🔹 UC16 – Database Integration with JDBC

* Connected application to database
* CRUD operations implemented

---

### 🔹 UC17 – Spring Boot Backend

* Converted project into Spring Boot application
* REST APIs introduced

---

### 🔹 UC18 – Google Authentication

* Integrated Google OAuth login
* Secure authentication mechanism

---

##  Branch Strategy

| Branch Name                                                       | Description                    |
| ----------------------------------------------------------------- | ------------------------------ |
| feature/UC1-FeetEquality                                          | Equality check for feet        |
| feature/UC2-InchEquality                                          | Equality check for inches      |
| feature/UC3-GenericLength                                         | Generic length handling        |
| feature/UC4-YardEquality                                          | Yard unit equality             |
| feature/UC5-UnitConversion                                        | Unit conversion                |
| feature/UC6-UnitAddition                                          | Addition                       |
| feature/UC7-TargetUnitAddition                                    | Target unit                    |
| feature/UC8-StandaloneUnit                                        | Modular units                  |
| feature/UC9-WeightMeasurement                                     | Weight                         |
| feature/UC10-GenericQuantityClass                                 | Generic design                 |
| feature/UC11-VolumeMeasurementEquality                            | Volume                         |
| feature/UC12-SubtractAndDivisionOperations                        | Arithmetic                     |
| feature/UC13-CentralizedArithmeticLogic                           | Optimization                   |
| feature/UC14-TemperatureMeasurementwithSelectiveArithmeticSupport | Temperature                    |
| feature/UC15-N-Tier                                               | Architecture                   |
| feature/UC16-Database-Integration-With-JDBC                       | JDBC                           |
| feature/UC17-SpringBackend                                        | Spring Boot                    |
| feature/UC18-GoogleAuthentication                                 | OAuth                          |
| dev                                                               | Development integration branch |
| main                                                              | Final integrated               |

---

##  How to Run

### 1. Clone Repository

```bash
git clone <your-repo-url>
cd QuantityMeasurement
```

### 2. Build Project

```bash
mvn clean install
```

### 3. Run Application

```bash
mvn spring-boot:run
```

---

##  Features

* Unit Conversion (Length, Volume, Weight, Temperature)
* Arithmetic Operations (Add, Subtract, Divide)
* Generic Quantity Handling
* Layered Architecture
* REST API Support
* Database Integration
* Google Authentication

---

##  Key Concepts Covered

* OOP & SOLID Principles
* N-Tier Architecture
* REST APIs (Spring Boot)
* JDBC & Database Design
* Authentication (OAuth)
* Clean Code Practices

---

##  Author

**Govind Upadhyay**

---

##  License

This project is for educational purposes.
