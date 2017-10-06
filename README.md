# tasker
About
---

Project goal is to provide example implementation of simple work task manager based on DropWizzard framework using JWT for authentication and Vue.js as frontend.

How to start the tasker application
---

1. Run `mvn clean install` to build your application
2. Run `java -jar target/tasker-0.1.jar db migrate config.yml` to prepare DB schema
3. Start application with `java -jar target/tasker-0.1.jar server config.yml`
4. To run GUI enter url `http://localhost:9000/gui/index.html`

How to start application in exploded state/debug in Eclipse
---
Right click TaskerApplication.Java and run Run as Java Application/ Debug as Java application

TODO
---
* Make FE modular, introduce some vue FE dev stack
* Create integration tests
* Create more health checks
* Experiment in scaling the application

Usefull links
---
[Example requests and response in CURL format](examplerequest_response.md)

http://jarbytes.com/jwt-on-dropwizard.html

http://jonatan.nilsson.is/stateless-tokens-with-jwt/
