# MovieApp

MovieApp is a native Android application designed to showcase a clean architecture approach. The app allows users to explore movies using a well-structured codebase split into three layers: Presentation, Domain, and Data.

## Table of Contents

Architecture Overview
Presentation Layer
Domain Layer
Data Layer
Features
Getting Started
Dependencies
Contributing

### Architecture Overview
This project follows the principles of Clean Architecture, which separates the code into different layers, each with a specific responsibility. This approach increases the maintainability, scalability, and testability of the application.

### Presentation Layer**
The Presentation Layer is responsible for the UI and how the app's components interact with each other. This layer depends only on the Domain Layer and is unaware of the Data Layer.

Key points:

*ViewModels*: Handles the UI-related data in a lifecycle-conscious way.
*UI Components*: Activities, Fragments, and other UI elements interact with ViewModels.
*No direct dependency on the Data Layer*: All data requests are funneled through the Domain Layer.

### Domain Layer
The Domain Layer acts as a middleman between the Presentation and Data Layers. It contains all the business logic required to fulfill the app’s features.

Key points:

*Use Cases*: Encapsulates specific business logic, making it reusable and independent of UI concerns.
*Entities*: Pure Kotlin data classes that represent core data models.
*Repositories (Interfaces)*: Abstracts the data sources and defines the contract for data operations, which are implemented in the Data Layer.

### Data Layer
The Data Layer is responsible for handling data operations, including fetching data from different sources (e.g., remote APIs, local databases).

Key points:

*Repository Pattern*: The Data Layer implements the repositories defined in the Domain Layer, fetching data from various sources.
*Data Source*s: Could include local storage (SQLite, Room), remote APIs, or any other data source.
*Mappers*: Converts raw data into domain entities.

### Features
Explore a list of movies with details like title, description, and rating.
Clean Architecture ensuring high maintainability and scalability.
Modular codebase following SOLID principles.
Getting Started
To get a local copy of the project up and running, follow these steps.

Prerequisites
Android Studio Arctic Fox or later
Kotlin 1.5+
Gradle 7.0+
Installation
Clone the repository:



Dependencies
Kotlin: For writing concise, safe, and modern Android code.
AndroidX: For backward-compatible Android components.
Hilt/Dagger: For dependency injection.
Retrofit: For network requests.
Contributing
Contributions are welcome! Please fork this repository and submit a pull request with your changes. Make sure to follow the project's coding style and include tests where appropriate.


