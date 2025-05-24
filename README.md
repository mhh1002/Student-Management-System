# Student-Management-System

A simple Java-based command-line application for managing student records and user accounts. This project demonstrates basic CRUD (Create, Read, Update, Delete) operations and user authentication without persistent storage.

## Table of Contents

* [Features](#features)
* [Prerequisites](#prerequisites)
* [Installation](#installation)
* [Usage](#usage)
* [Project Structure](#project-structure)
* [Limitations](#limitations)
* [Future Improvements](#future-improvements)

## Features

* **User Registration & Login**: Create and authenticate users (App.java).
* **Password Recovery**: Simple mechanism for handling forgotten passwords.
* **Student Record Management**: Add, delete, modify, and inquire student details (StudentManagementSystem.java).
* **Command-Line Interface**: Interactive menu-based navigation.

## Prerequisites

* Java Development Kit (JDK) 8 or higher
* Command-line interface (Terminal, PowerShell, etc.)

## Installation

1. Clone or download the repository to your local machine.
2. Open a terminal and navigate to the project directory.
3. Compile the Java source files:

   ```bash
   javac *.java
   ```

## Usage

1. Run the application:

   ```bash
   java App
   ```

2. Follow the on-screen prompts:

   * **Register**: Create a new user account.
   * **Sign In**: Log in with an existing account.
   * **Forget password**: Recover a lost password.
   * **Exit**: Quit the application.

3. After signing in, use the Student Management System menu:

   * **Add Student**: Enter student ID and name to create a record.
   * **Delete Student's Detail**: Remove a student by ID.
   * **Modify Student's Info**: Update an existing student’s information.
   * **Inquire Student's Info**: Search for a student by ID.
   * **Exit**: Return to the main menu or quit.

## Project Structure

```
├── App.java                    # Entry point; handles user accounts and navigates to SMS
├── StudentManagementSystem.java# Manages students (add, delete, modify, inquire)
├── User.java                   # Represents user credentials and data
└── Student.java                # Represents a student record
```

## Limitations

* **No Data Persistence**: Currently, all data is stored in memory and lost when the application exits. The save/load functionality for persisting student and user data is not implemented.
* **Single-Key Uniqueness**: Student IDs and Usernames must be unique; duplicates will overwrite existing records.

## Future Improvements

* Implement data persistence (e.g., using file I/O, JSON, CSV, or a database).
* Enhance security (e.g., password hashing).
* Add more student attributes (email, GPA, etc.).
* Improve error handling and input validation.
* Develop a graphical user interface (GUI) or web interface.

---

*Powered by a simple Java CLI application.*
