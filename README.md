# tasker
About
---

Project goal is to provide example implementation of simple work task manager based on DropWizzard framework using JWT for authentication and Vue.js as frontend.

How to start the tasker application
---

1. Run `mvn clean install` to build your application
2. Run `java -jar target/tasker-0.1.jar db migrate config.yml` to prepare DB schema
2. Start application with `java -jar target/tasker-0.1.jar server config.yml`
3. To check that your application is running enter url `http://localhost:8080`
4. To see your applications health enter url `http://localhost:8081/healthcheck`



How to start application in exploded state/debug in Eclipse
---
Right click TaskerApplication.Java and run Run as Java Application/ Debug as Java application

Usefull links
---

http://jarbytes.com/jwt-on-dropwizard.html
http://jonatan.nilsson.is/stateless-tokens-with-jwt/

[a relative link](examplerequest_response.md)