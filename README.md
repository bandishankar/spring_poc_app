Build project 
===================
run this command in root directory 

./gradle clean build

run project
=================
run this command in root directory

./gradle clean bootrun

once system is up , use curl to intract with it on 8080 default port


run test cases
=================

to run test case run this in root directory
./gradle clean test



curls
=================


1) Please create a Spring Boot application with a "Hello World" REST endpoint. This project will contain all the following REST endpoints also.

curl -X GET http://localhost:8080/hello



2) Please add a REST endpoint that accepts a JSON object containing a paragraph of text and returns a JSON array of objects. The returned objects represent the unique words from the supplied paragraph along with a count of the number of occurrences. The array of objects must be sorted alphabetically.

curl -X POST http://localhost:8080/count -H 'Content-Type: application/json' -d '{"words":"there is no life without is no air air"}'
 
 

3) Please add a REST endpoint that accepts a number, N, and returns a JSON array with the first N Fibonacci numbers. Please use the recursive form of Fibonacci. Be prepared to answer O() complexity questions when we review the submission by phone.

curl -X GET http://localhost:8080/fibonacci/9
 
 

4) Please add a REST endpoint that creates two threads that become deadlocked with each other. Use the REST function to monitor the two threads and detect the deadlock, i.e. neither thread exits after a timeout of your choosing. Hint: We want to see you use the synchronized keyword.

curl -X GET http://localhost:8080/create/deadlock

curl -X GET http://localhost:8080/check/deadlock
 


5) Please add three REST endpoints that add, query, and delete rows in a database. Please use the HyperSQL (hsqldb.org) database dependency for an in-process database. Hint: Initialize the database when your Spring Boot application starts. Hint:http://www.programmingforfuture.com/2011/02/in-process-mode-of-hsqldb-in-web.html

// add data 
 curl -X GET 'http://localhost:8080/db/add/?id=6&name=sbandi&age=30'

// fetch all data from DB 
curl -X GET http://localhost:8080/db/all/

// delete by id
curl -X GET http://localhost:8080/db/delete/6 
 
// fetch by id
curl -X GET http://localhost:8080/db/find/6 



6) Finally, please create a REST endpoint that queries an external REST service using Spring RestTemplate. The response should be returned to the caller. We suggest using this external service: https://jsonplaceholder.typicode.com/posts

curl -X GET http://localhost:8080/consumeapi