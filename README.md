# MicroserviceEureka
Microservice using Netflix Eureka, Hystrix , Zuul

Run in the following order to test it.
1) Run first config server.  (http://localhost:5000/pig-dev.properties  should work)
2) Run Eureka server. (http://localhost:8761 should work)
3) Run MIG application. (http://localhost:9098/msg : http://localhost:9098/pigip : http://localhost:9098/pigmsg  should work)
4) Run PIG application. (http://localhost:9098/msg should work)
5) http://localhost:8761 should show 2 microservice MIG and PIG deployed.


Thanks Subrata.
