# TodoList

[![Java](https://img.shields.io/badge/Java-21-blue?logo=java&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-brightgreen?logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?logo=mysql&logoColor=white)](https://www.mysql.com/)
[![Bootstrap](https://img.shields.io/badge/Bootstrap-5.3-purple?logo=bootstrap&logoColor=white)](https://getbootstrap.com/)
[![Build](https://img.shields.io/badge/build-passing-brightgreen?logo=githubactions&logoColor=white)](https://github.com/roskonenov/Todo-List-With-Spring-and-Thymeleaf/actions)
[![Tests](https://img.shields.io/badge/tests-passing-brightgreen?logo=github&logoColor=white)](https://github.com/roskonenov/Todo-List-With-Spring-and-Thymeleaf/actions)

---

Welcome to **TodoList**, a modern, responsive **Todo List web application** built with **Spring Boot**, **Thymeleaf**, **MySQL**, and **Bootstrap**. This project demonstrates full-stack development, clean code architecture, and professional testing practices.

**Features:**
- ✅ Create, view, toggle, and delete tasks
- ✅ Persist tasks in **MySQL**
- ✅ Fully tested with **unit & integration tests**
- ✅ Responsive UI powered by **Bootstrap**

**Tech Stack:**
- **Backend:** Java 21, Spring Boot, Spring Data JPA, Hibernate
- **Frontend:** Thymeleaf, HTML, CSS, Bootstrap 5
- **Database:** MySQL
- **Testing:** JUnit 5, Mockito
- **Build Tool:** Maven

**Run Locally:**
1. Clone the repository:
```
git clone https://github.com/roskonenov/Todo-List-With-Spring-and-Thymeleaf.git
cd Todo-List-With-Spring-and-Thymeleaf
```

2. Configure MySQL in application.properties:
```
   spring.datasource.url=jdbc:mysql://localhost:3306/todo_app_db?createDatabaseIfNotExist=true
   spring.datasource.username=root
   spring.datasource.password=YOUR_PASSWORD
   spring.jpa.hibernate.ddl-auto=update
```
3. Build and run the application:
```
mvn clean install
mvn spring-boot:run
```
4. Open your browser at: http://localhost:8080

Author: 
Rosen Nenov |
roskonenov@gmail.com