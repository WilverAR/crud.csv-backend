# Backend of the project CRUD

This is the repository for the backend of the CRUD project, responsible for handling logic and managing application data


## Requirements of the project

- [Git](https://git-scm.com/)
- [Java Development Kit (JDk)](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
- [Apache Maven](https://maven.apache.org/)
- [MySQL Database](https://www.mysql.com/)


## Instructions of configuration - Local

1. **Clone the repository**
   ```bash
   git clone https://github.com/WilverAR/crud.csv-backend.git
   ```
   
2. **Configure the Database Connection**
   ```yaml
   spring:
       datasource:
        url: jdbc:mysql://localhost:3306/resale?useSSL=false
        username: [YOUR_USERNAME]
        password: [YOUR_PASSWORD]
        driver-class-name: com.mysql.cj.jdbc.Driver
    ```
   
3. **Navigate to the project folder**
   ```bash
   cd crud.csv-backend
   ```
   
4. **Compile the project**
   ```bash
   mvn clean install
   ```
   
5. **Run the project**
   ```bash
   mvn spring-boot:run
   ```
   
6. **Access Swagger UI**
   ```bash
   http://localhost:8080/swagger-ui.html
   ```