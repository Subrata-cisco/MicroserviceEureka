# MicroserviceEureka
Microservice using Netflix Eureka, Hystrix , Zuul

Run in the following order to test it.
1) Run first config server.  (http://localhost:5000/pig-dev.properties  should work)
2) Run Eureka server. (http://localhost:8761 should work)
3) Run MIG application. (http://localhost:9099/msg : )
4) Run PIG application. 
(http://localhost:9098/msg  //result from PIG
 http://localhost:9099/migip?time=400   //result from MIG
 http://localhost:9099/migip?time=2000 // MIG is taking >500 time so default message i.e Circuit breaking.
 http://localhost:9099/migmsg  should work) //result from MIG
5) http://localhost:8761 should show 2 microservice MIG and PIG deployed.


Thanks Subrata.
