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

### 💡 Why I picked Retrofit
Most automation testers use **RestAssured**. It’s great, but for this project, I wanted to try something more "architectural." Here is why I switched to **Retrofit**:

* **Better Code Structure**: Instead of writing long chains of code for every request, I just define everything in one interface (`YandexDiskService.java`). It keeps the actual tests very clean and easy to read.
* **Less Typos**: Since Retrofit uses Java interfaces and annotations, I don't have to worry about making a mistake in a URL string or a query parameter name. The compiler catches many errors for me.
* **Real Dev Tools**: Retrofit is what actual Java/Android developers use to build apps. I wanted to learn how the "pros" handle network requests, not just use a testing-only tool.
* **Awesome Logs**: Setting up the `HttpLoggingInterceptor` was a game changer. I can see exactly what my test is sending and receiving in the console, which makes debugging way faster.

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

