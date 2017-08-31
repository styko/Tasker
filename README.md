# tasker
About
---

Project goal is to provide example implementation of simple Work task BE based on DropWizzard framework.

Usefull links
---

http://jarbytes.com/jwt-on-dropwizard.html
http://jonatan.nilsson.is/stateless-tokens-with-jwt/

How to start the tasker application
---

1. Run `mvn clean install` to build your application
2. Run `java -jar target/tasker-0.1.jar db migrate config.yml` to prepare DB schema
2. Start application with `java -jar target/tasker-0.1.jar server config.yml`
3. To check that your application is running enter url `http://localhost:8080`
4. To see your applications health enter url `http://localhost:8081/healthcheck`

Examples of sending request
---

1. Login request
`curl -H "Content-Type: application/json" -X POST -d "{"""username""":"""user""","""password""":"""123"""}" http://localhost:9000/auth/login`

2. Get All Task of User
`curl "http://localhost:9000/task-list" -X GET -H "Origin: http://localhost:9000" -H "Accept-Encoding: gzip, deflate, br" -H "Accept-Language: sk-SK,sk;q=0.8,cs;q=0.6,en-US;q=0.4,en;q=0.2" -H "X-Requested-With: XMLHttpRequest" -H "Accept: */*" -H "Referer: http://localhost:9000/" -H "X-auth: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkYXRhNGoiOiJ7XCJpZFwiOjEsXCJ1c2VybmFtZVwiOlwidXNlcm5hbWUgZnJvbSB1c2VyXCIsXCJwcml2aWxlZ2VcIjpcIlVTRVJcIixcInVzZXJDcmVkZW50aWFsc1wiOntcImlkXCI6MSxcInVzZXJuYW1lXCI6XCJ1c2VyXCIsXCJwYXNzd29yZFwiOlwiMTIzXCJ9fSJ9.v6LHj/Df2EDpfFkrnw6nOjkCCzY6o6y06Wa0G4c8z2s=" -H "Connection: keep-alive" -H "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36" -H "Content-Length: 0" --compressed`

3.Other requests TODO

curl "http://localhost:9000/secured/user" -X POST -H "Origin: http://localhost:8080" -H "Accept-Encoding: gzip, deflate, br" -H "Accept-Language: sk-SK,sk;q=0.8,cs;q=0.6,en-US;q=0.4,en;q=0.2" -H "X-Requested-With: XMLHttpRequest" -H "Accept: */*" -H "Referer: http://localhost:8080/" -H "X-auth: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkYXRhNGoiOiJ7XCJ1c2VybmFtZVwiOlwianVzdC11c2VyXCIsXCJwcml2aWxlZ2VzXCI6W1wiVVNFUlwiXX0ifQ==.01gBXLp9mz6HRn4rJX+5J4mrygbSBfi2QDDC6oPmLhI=" -H "Connection: keep-alive" -H "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36" -H "Content-Length: 0" --compressed


http://localhost:9000/task-list
curl -H "Content-Type: application/json" -X POST -d "{"""info""":"""Mackaman""","""begin""":"""2012-04-23T18:25:43.511Z""","""end""":"""2012-04-23T18:25:43.511Z""","""slackTime""":"""02:00:00"""}" http://localhost:9000/task-list

curl -H "Content-Type: application/json" -X POST -d "{"""id""":"""4""","""info""":"""Macka""","""begin""":"""2012-04-23T18:25:43.511Z""","""end""":"""2012-04-23T18:25:43.511Z""","""slackTime""":"""02:00:00"""}" http://localhost:9000/task-list

http://localhost:9000/task-list/delete-task?id=1

http://localhost:9000/task-list/task?id=4

How to start appliaction in exploded state/debug in Eclipse
---
Right click TaskerApplication.Java and run Run as Java Application/ Debug as Java application

