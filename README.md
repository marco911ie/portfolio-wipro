# portfolio-wipro

Run with mvn spring-boot:run
then surf to http://localhost:8080/swagger-ui.html

There are 5 rest service.
- POST http://localhost:8080/opi/api/portfolio/v1.0/add 
   - symbol and number are mandatory  values for json
- GET  http://localhost:8080/opi/api/portfolio/v1.0/get
- GET  http://localhost:8080/opi/api/portfolio/v1.0/get/{id}
- PUT  http://localhost:8080/opi/api/portfolio/v1.0/update
  - id and number are mandatory  values for json
- DELETE http://localhost:8080/opi/api/portfolio/v1.0/delete/{id}
