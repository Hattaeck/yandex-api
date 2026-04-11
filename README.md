# Yandex Disk API Automation (Retrofit Version)

This repository contains automated tests for the **Yandex Disk REST API** implemented using **Retrofit 2**. The project demonstrates an approach to API testing by using an interface-based HTTP client and automated logging.

---

## 🚀 Features
* **Interface-Driven**: Uses `YandexDiskService` interface for clean and readable API definitions.
* **Full CRUD Coverage**: Tests include getting disk info, creating folders, copying resources, and deleting them.
* **Network Logging**: Integrated `HttpLoggingInterceptor` to monitor requests and responses in the console.
* **Dynamic Data**: Generates unique folder names using timestamps to avoid naming conflicts during test execution.

---

## 🛠 Tech Stack
* **Java 17** (Temurin)
* **Retrofit 2** — Type-safe HTTP client
* **OkHttp 4** — HTTP client for logging and network management
* **JUnit 5** — Testing framework
* **Gson** — JSON serialization/deserialization

---

### 🧠 Why Retrofit?
While **RestAssured** is a popular choice for API testing, I chose **Retrofit 2** for this project to demonstrate a more structured, developer-aligned approach:

* **Type-Safety**: Retrofit uses Java interfaces to define API endpoints, which significantly reduces typos in URLs and parameters compared to the "given-when-then" DSL used in RestAssured.
* **Separation of Concerns**: By keeping API definitions in `YandexDiskService.java` and test logic in `DiskRetrofitTest.java`, the project remains clean, organized, and easy to maintain.
* **Production-Ready Skills**: Retrofit is the industry standard for Java and Android development. Mastering this tool demonstrates a deep understanding of how real-world applications handle network layers.
* **Detailed Logging**: The integration of `HttpLoggingInterceptor` provides a clear and comprehensive overview of request/response cycles in the console, making debugging much more efficient.

---

## 📋 Prerequisites
Before running the tests, ensure you have:
1. A valid **Yandex OAuth Token**.
2. The token added to your environment variables.

---

## ⚙️ Configuration
The tests retrieve the authorization token from an environment variable named `YANDEX_TOKEN`.

### Set Environment Variable
**Windows (PowerShell):**
```powershell
$env:YANDEX_TOKEN="your_token_here"

