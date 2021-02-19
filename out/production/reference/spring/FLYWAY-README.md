# Flyway Migration
___

Add this to pom.xml
```
<dependency>
 <groupId>org.flywaydb</groupId>
 <artifactId>flyway-core</artifactId>
</dependency>
```
Spring Boot will then automatically autowire Flyway with its DataSource and invoke it on startup. 
You can then configure a good number of Flyway properties directly from your application.properties 
or application.yml file. Spring Boot also lets you configure Flyway using environment variables. 
Just be aware that the names of these environment variables differ from Flyway’s native environment 
variables. Refer: [https://flywaydb.org/documentation/usage/plugins/springboot]()

Also refer the spring boot doc: [https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto-execute-flyway-database-migrations-on-startup]()