# Each of these Actuator endpoints and their purpose.

### 1. **`health`**
   - **Purpose**: Provides the health status of the application.
   - **Details**: Returns a JSON object with information like `UP` or `DOWN` status. It can include additional health indicators (e.g., database, disk space, external services) depending on what health checks are configured.
   - **Usage**: Helps monitor the application's status, especially in production, to ensure it’s running smoothly.

### 2. **`info`**
   - **Purpose**: Displays application-specific information.
   - **Details**: You can define any custom information here, like application version, build details, or team contact information, by adding properties in `application.properties` (e.g., `info.app.name=MyApp`).
   - **Usage**: Useful for displaying application metadata or environment details, typically for debugging or tracking deployments.

### 3. **`loggers`**
   - **Purpose**: Provides access to the application's logging configuration and allows dynamic logging level changes.
   - **Details**: Returns a list of loggers and their current levels. You can modify the logging level of specific packages or classes in real-time by making `POST` requests to this endpoint.
   - **Usage**: Ideal for on-the-fly debugging without restarting the application. Changing logging levels dynamically can help in tracking down issues without deploying a new version.

### 4. **`metrics`**
   - **Purpose**: Offers various metrics about the application's performance.
   - **Details**: Returns information such as memory usage, CPU usage, active thread count, and request metrics. You can query specific metrics like `jvm.memory.used` or `http.server.requests`.
   - **Usage**: Important for performance monitoring, especially in production, to understand resource consumption, bottlenecks, and to identify optimizations.

### 5. **`env`**
   - **Purpose**: Shows environment properties and configurations.
   - **Details**: Lists environment variables, application properties, and configuration profiles. This includes information set in `application.properties`, `application.yml`, and system environment variables.
   - **Usage**: Helps to debug configuration issues by viewing the effective environment variables and properties in the application.

### 6. **`beans`**
   - **Purpose**: Lists all the beans loaded in the application context.
   - **Details**: Returns a detailed list of all Spring beans in the application, including their names, classes, and dependencies.
   - **Usage**: Useful for debugging dependency injection issues and verifying that all beans are correctly initialized and wired in the application context.

### 7. **`mappings`**
   - **Purpose**: Shows all available HTTP request mappings in the application.
   - **Details**: Lists all endpoints available in the application along with their respective URLs, HTTP methods, and controller classes.
   - **Usage**: Helps to verify all routes are correctly defined, especially useful in applications with multiple controllers and complex routing logic.

### 8. **`threaddump`**
   - **Purpose**: Provides a snapshot of all threads currently running in the JVM.
   - **Details**: Returns the state and stack trace of each thread, which helps diagnose issues like deadlocks or high CPU usage.
   - **Usage**: Valuable for debugging thread-related issues, especially in applications experiencing performance problems due to threading issues.

### 9. **`custom`** (Custom Endpoint)
   - **Purpose**: A custom endpoint created to expose `PersonService` data.
   - **Details**: Defined by us in the previous examples, this endpoint retrieves or modifies data within the `PersonService`.
   - **Usage**: Demonstrates how to expose application-specific data or functionalities, tailored to the needs of the application.

---

These endpoints offer a robust toolset for managing and monitoring a Spring Boot application. 