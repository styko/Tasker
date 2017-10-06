Examples of sending request
---

1. Login request
`curl -H "Content-Type: application/json" -X POST -d "{"""username""":"""user""","""password""":"""123"""}" http://localhost:9000/auth/login`

2. Get All Tasks of User
`curl "http://localhost:9000/task-list" -X GET -H "X-auth: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkYXRhNGoiOiJ7XCJpZFwiOjEsXCJ1c2VybmFtZVwiOlwidXNlcm5hbWUgZnJvbSB1c2VyXCIsXCJwcml2aWxlZ2VcIjpcIlVTRVJcIixcInVzZXJDcmVkZW50aWFsc1wiOntcImlkXCI6MSxcInVzZXJuYW1lXCI6XCJ1c2VyXCIsXCJwYXNzd29yZFwiOlwiMTIzXCJ9fSJ9.v6LHj/Df2EDpfFkrnw6nOjkCCzY6o6y06Wa0G4c8z2s=" --compressed`

3. Get Specific task of user
`curl "http://localhost:9000/task-list/task?id=2" -X GET -H "X-auth: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkYXRhNGoiOiJ7XCJpZFwiOjEsXCJ1c2VybmFtZVwiOlwidXNlcm5hbWUgZnJvbSB1c2VyXCIsXCJwcml2aWxlZ2VcIjpcIlVTRVJcIixcInVzZXJDcmVkZW50aWFsc1wiOntcImlkXCI6MSxcInVzZXJuYW1lXCI6XCJ1c2VyXCIsXCJwYXNzd29yZFwiOlwiMTIzXCJ9fSJ9.v6LHj/Df2EDpfFkrnw6nOjkCCzY6o6y06Wa0G4c8z2s=" --compressed`


4. Delete specific task of user
`curl "http://localhost:9000/task-list/task?id=2" -X DELETE -H "X-auth: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkYXRhNGoiOiJ7XCJpZFwiOjEsXCJ1c2VybmFtZVwiOlwidXNlcm5hbWUgZnJvbSB1c2VyXCIsXCJwcml2aWxlZ2VcIjpcIlVTRVJcIixcInVzZXJDcmVkZW50aWFsc1wiOntcImlkXCI6MSxcInVzZXJuYW1lXCI6XCJ1c2VyXCIsXCJwYXNzd29yZFwiOlwiMTIzXCJ9fSJ9.v6LHj/Df2EDpfFkrnw6nOjkCCzY6o6y06Wa0G4c8z2s=" --compressed`

5. Add new task of user
`curl "http://localhost:9000/task-list/task" -X PUT -H "Content-Type: application/json" -X PUT -d "{"""info""":"""Mackaman""","""begin""":"""2012-04-23T18:25:43.511Z""","""end""":"""2012-04-23T18:25:43.511Z""","""slackTime""":"""02:00:00"""}"  -H "X-auth: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkYXRhNGoiOiJ7XCJpZFwiOjEsXCJ1c2VybmFtZVwiOlwidXNlcm5hbWUgZnJvbSB1c2VyXCIsXCJwcml2aWxlZ2VcIjpcIlVTRVJcIixcInVzZXJDcmVkZW50aWFsc1wiOntcImlkXCI6MSxcInVzZXJuYW1lXCI6XCJ1c2VyXCIsXCJwYXNzd29yZFwiOlwiMTIzXCJ9fSJ9.v6LHj/Df2EDpfFkrnw6nOjkCCzY6o6y06Wa0G4c8z2s=" --compressed`

6. Edit existing task of user
`curl "http://localhost:9000/task-list/task" -X PUT -H "Content-Type: application/json" -X PUT -d "{"""id""":"""4""","""info""":"""Mackavity""","""begin""":"""2012-04-23T18:25:43.511Z""","""end""":"""2012-04-23T18:25:43.511Z""","""slackTime""":"""02:00:00"""}"  -H "X-auth: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkYXRhNGoiOiJ7XCJpZFwiOjEsXCJ1c2VybmFtZVwiOlwidXNlcm5hbWUgZnJvbSB1c2VyXCIsXCJwcml2aWxlZ2VcIjpcIlVTRVJcIixcInVzZXJDcmVkZW50aWFsc1wiOntcImlkXCI6MSxcInVzZXJuYW1lXCI6XCJ1c2VyXCIsXCJwYXNzd29yZFwiOlwiMTIzXCJ9fSJ9.v6LHj/Df2EDpfFkrnw6nOjkCCzY6o6y06Wa0G4c8z2s=" --compressed`