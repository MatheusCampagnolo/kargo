<div align="center">

<h1>Kargo</h1>

[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=flat&logo=openjdk&logoColor=white)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2-6DB33F?style=flat&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14+-316192?style=flat&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![License](https://img.shields.io/badge/license-MIT-green)](LICENSE)

**Kargo** — A robust Inventory Management System API.  
Designed for efficient e-commerce backend operations with product tracking, stock management, and statistical analysis.

</div>

## 🚀 Stack

*   **Java 17**: Core language.
*   **Spring Boot 3.2**: Application framework.
*   **PostgreSQL**: Relational database.
*   **Spring Data JPA**: Data persistence.
*   **Bean Validation**: Data integrity validation.
*   **OpenAPI (Swagger)**: API documentation.

## 🛠️ Setup

### Prerequisites
*   Java 17+
*   Maven 3.8+
*   PostgreSQL 14+

### Database Configuration
Ensure authentication details in `src/main/resources/application.properties` match your local PostgreSQL setup:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/kargo
spring.datasource.username=your_username
spring.datasource.password=your_password
server.port=8081
```

## 📦 Installation & Run

1.  **Clone the repository**
    ```bash
    git clone https://github.com/MatheusCampagnolo/kargo.git
    cd kargo
    ```

2.  **Clean and Build**
    ```bash
    mvn clean install
    ```

3.  **Run the Application**
    ```bash
    mvn spring-boot:run
    ```
    The API will be available at `http://localhost:8081`.

## 📑 API Documentation

Interactive documentation via Swagger UI is available at:
> **http://localhost:8081/swagger-ui.html**

### Key Endpoints

| Method | Endpoint | Description |
|:--- |:--- |:--- |
| `POST` | `/api/products` | Create a new product (Unique SKU required). |
| `GET` | `/api/products` | List all products (Pagination & Sorting support). |
| `GET` | `/api/products/{id}` | Retrieve specific product details. |
| `PUT` | `/api/products/{id}` | Update product information. |
| `PATCH` | `/api/products/{id}/stock` | Partial stock update (Add/Remove quantity). |
| `DELETE` | `/api/products/{id}` | Remove a product. |
| `GET` | `/api/products/search` | Filter by `name` and/or `category`. |
| `GET` | `/api/products/low-stock` | List products below minimum stock level. |
| `GET` | `/api/products/stats` | Inventory statistics (Total Value, Item Count, etc). |

## 🤝 Contribution & License

This project is licensed under the [MIT License](LICENSE).

Contributions are welcome! Feel free to open issues or submit pull requests to improve the codebase.
