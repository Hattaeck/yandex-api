# Yandex Disk API Automation (Retrofit Architecture)

This repository contains automated tests for the Yandex Disk REST API implemented using Retrofit 2. The project demonstrates an approach to API testing by using an interface-based HTTP client and automated logging.

---

## 🏗 Project Structure
The project is organized to keep the code clean and easy to maintain:

* **`BaseTest.java`** — The main setup file. It starts the Retrofit client, turns on logging (to see requests in the console), and handles the Auth token.
* **`YandexDiskService.java`** — A simple interface where all Yandex API endpoints are listed. This is the only place where we define URLs and HTTP methods.
* **`DiskPositiveTests.java`** — Regular tests for successful actions: creating folders, copying, moving, and publishing.
* **`DiskNegativeTests.java`** — Tests for errors: what happens if the token is wrong or a folder doesn't exist.
* **`HttpStatusCode.java`** — A list of all status codes (like 200, 401, 404) so we don't have to remember the numbers by heart.
---

## 🛠 Tech Stack
* **Java 17** (Temurin)
* **Retrofit 2** — Type-safe HTTP client for API definitions.
* **OkHttp 4** — Includes `HttpLoggingInterceptor` for real-time traffic monitoring.
* **JUnit 5** — Modern testing framework for assertions and execution.
* **Gson** — JSON serialization and deserialization.

---

## 📋 Test Coverage
The suite covers the primary lifecycle of Yandex Disk resources:
* **Disk Info**: Verification of account limits and space.
* **Resource Management**: Creating folders, moving, and copying files.
* **Public Access**: Publishing and unpublishing resources for external access.
* **Trash Operations**: Viewing trash content and performing a full permanent cleanup.
* **Security**: Validating response behavior with incorrect or missing OAuth tokens.

---

## 💡 Why Retrofit over RestAssured?
While most automation testers use **RestAssured**, I chose **Retrofit 2** to demonstrate a developer-centric architecture:

1. **Strict Separation**: By separating API definitions from test logic, the framework is much easier to maintain and read.
2. **Type Safety**: Using Java interfaces reduces runtime errors caused by typos in URLs or parameters. The compiler catches mistakes early.
3. **Industry Standard**: Retrofit is the tool of choice for actual Java and Android developers. Learning it bridges the gap between QA and Engineering.
4. **Awesome Debugging**: The `HttpLoggingInterceptor` provides full transparency in the console.
---

## 🚀 Setup & Execution

### 1. Authentication
The suite requires a **Yandex OAuth Token**.
Ensure your token is correctly set in `BaseTest.java` (prefixed with `OAuth `) or configured as an environment variable `YANDEX_TOKEN`.

### 2. Run Tests
Execute the entire suite via Maven:
```bash
mvn clean test