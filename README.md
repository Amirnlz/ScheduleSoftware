# ScheduleSoftware
**Final project of Design Algorithm**

## Diagram
![Diagram](https://user-images.githubusercontent.com/64724370/122634411-5e244e00-d0f3-11eb-8532-643ef8d4fab9.jpg)


## Application.properties Setting
- ### Behzad
spring.datasource.username=root
spring.datasource.password=BEHIWZAD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform = org.hibernate.dialect.MySQL5InnoDBDialect
spring.thymeleaf.cache=false
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=update
server.port=8085
spring.servlet.multipart.max-file-size=100MB
spring.servlet.multipart.max-request-size=100MB

- ### Hossein
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/schadule
spring.datasource.username=postgres
spring.datasource.password=45686543
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties..hibernate.jdbc.lob.non_contexual_creation=true
spring.servlet.multipart.max-file-size=100MB
spring.servlet.multipart.max-request-size=100MB

- ### Amir
spring.datasource.url=jdbc:mysql://localhost:3306/schedule
spring.datasource.username=root
spring.datasource.password=[Amirnlz28]
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform = org.hibernate.dialect.MySQL5InnoDBDialect
spring.thymeleaf.cache=false
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=update
server.port=8045
spring.servlet.multipart.max-file-size=100MB
spring.servlet.multipart.max-request-size=100MB