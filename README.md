# 📝 Task IO

A simple **Spring Boot REST API** for managing tasks and task groups.  
Each task must belong to a task group, ensuring clear organization and grouping of work.  
A user can also register or login.  
Authentication is by Bearer Method 
---

## 🚀 Features
- Create, update, delete **Task Groups**
- Create, update, delete **Tasks**
- Enforce relationship:
    - A **Task** must always belong to a **Task Group**
- Retrieve all tasks by group
- RESTful API design with JSON responses
- Built with **Spring Boot**, **Spring Data JPA**, and **PostgreSQL**

---

## 🏗 Project Structure
 src/  
   ├── main/java/com/garbi/taskio  
│  ├── controller # REST Controllers  
│  ├── entity # JPA Entities (Task, TaskGroup)  
│  ├── repository # Spring Data JPA Repositories  
│  ├── service  #Business Logic Layer  
│  └── security  #Spring Security  
│  └── dto # Data Transfer Objects   
│  └── exceptions # Custom Exceptions  
│  └── constants  
│  └── Application.java  
└── main/resources  
└── ├── application.properties  
└──

---

## 📦 Tech Stack
- **Java 17+**
- **Spring Boot 3+**
- **Spring Data JPA**
- **Spring Security**
- **Spring Web**
- **Lombok**
- **Spring Validation**
- **JJWT**
- **PostgreSQL** 
- **Maven**

---

## ⚙️ Setup & Run

### 1. Clone Repository
```bash
https://github.com/Gabrielacode/task-io.git
cd task-manager-api

```
### 2. Configure Database

To use PostgreSQL, update application.properties:  
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/taskdb
spring.datasource.username=postgres
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update

# You can pass them as environmental variables or arguments
```
### 3. Run the Application
```bash
./mvnw spring-boot:run
```
The API will be available at:  
👉 http://localhost:8080/api

## 🔑 API Endpoints
### Task Groups
| Method  | Endpoint         | Description              |  
|---------|------------------|--------------------------|
| POST	   | /task-group      | 	Create a new task group |
| GET	    | /task-group      | 	List all task groups    |
| GET	    | /task-group/{id} | 	Get a task group by id  |
| DELETE	 | /task-group/{id} | 	Delete a task group     |
| PUT	    | /task-group/{id} | 	Update a task group     |

### Task 
| Method  | Endpoint                        | Description                       |  
|---------|---------------------------------|-----------------------------------|
| POST	   | /task-group/{id}/task           | 	Create a new task                |
| GET	    | /task-group/{id}/task/{task-id} | Get a task under a task group     |
| GET	    | /task-group/{id}/task           | 	Get all tasks under a task group |
| DELETE	 | /task-group/{id}/task/{task-id} | 	Delete a task                    |
| PUT	    | /task-group/{id}/task/{task-id} | 	Update a task                    |

### Auth
| Method  | Endpoint       | Description                          |  
|---------|----------------|--------------------------------------|
| POST	   | /auth/register | 	Register a new user                 |
| POST	   | /auth/login    | 	Login and get an access token (JWT) |


---

## ✅ Next Steps


- Add user ownership for task groups

- Pagination & filtering
