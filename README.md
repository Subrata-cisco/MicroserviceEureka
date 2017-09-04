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


Configuration Management :
Service Registry :
Service Discovery :
Fault Tolerance  :
Proxy :


Netflix OSS integration service:
Eureka : Hystrix : Zuul : Ribbon 
Cloud config : Netflix OSS Cloud Platform 
Spring cloud
Spring boot
Spring Framework


Microservice characteristics  :
A service
Service interact with other process
Data stored is related to Service
Stateless
Fault tolerant

Benefits : 
Compartmentalizes
Loosely coupled and moduler
Independent service management
Concurrent processing.
Smaller teams and increase TTM
Different technology.

Challenges :
Configuring multiple services as it grows.
Routing calls
Distrubuting traffic among multiple instance of same services. 
Monitoring health
Security across services.

Solutions :
Centralized configuration system
New service in Service registry
Route traffic using via service discovery.
Balance traffic using Load balancer.
Cross cutting concerns such as security and health centralised. 

Config server : (Configuration is exposed by config server, should be checked-in in GitHub)
Enable cloud server in the starter POM
Spring.cloud.config.server.git.uri = “path of the configuration file”
Server.port = 8001
In Spring boot main file add  @EnableConfigServer

Config client : (Client shall find the properties defined by server)
Enable cloud client in the starter POM
Create bootstrap.properties instead of application.properties which load fast before application starts.
Spring.profiles.active = development
Spring.application.name = client-config   (So it will look for client-config-development.properties file in exposed config server)
Spring.cloud.config.uri = “http://localhost:8888”  (the config server URI)
Expose rest api to fetch the property and it will display the property from config server.
Refresh the client configuration using @RefreshScope and actuator in the starter POM


OSS components  :
Eureka (Service registry and Discovery)  (like a Phone book)
Hystrix (Latency and Fault tolerance)  (Circuit breaker to remove the service,UI for health check)
Ribbon (Load balancing)
Zuul (Edge service and Routing)  (Gate to enter in to the system)

Going ahead with Eureka :
Bring a Eureka server for registration.  [Server]
Multiple instance to avoid single point of failure.  [Instances both for Server and Services]
Register clients (different services)  [Clients  : They can use other services but with out registering with Server]
Discover services. [Services means they have registered.]
Service discovery using Rest Template.

To create Eureka server :
Add a spring boot project and add Eureka Server Pom starter.
Add @ EnableEurekaServer
Add Server.port = 8761 , eureka.client.register-with-eureka = false , eureka.client.fetch-registry = false

To create Replica of Eureka server :
Add host in your machine as localhost peer1 and localhost peer2
Add the application property and name it application-peer1.properties 
And add eureka.instance.hostname = peer1 , eureka.client.serviceUrl.defaultZone = http://peer2:8762/eureka
Add another application property and name it application-peer2.properties 
And add eureka.instance.hostname = peer2 , eureka.client.serviceUrl.defaultZone = http://peer1:8761/eureka
When running the boot application add VM arguments –dSpring.profiles.active = peer1 and so on for next.

To create Eureka client :
Add a spring boot project and add Eureka Discovery Pom starter.
Add @ EnableEurekaClient
Add spring.application.name = PosterManagerService
We can now view this client in Eureka sever UI.

To create 2 Eureka client and fetching info from another client: 
Add 2 Eureka client in the above mentioned way.
Then Expose the second client as  RestController  and provide an rest API to get the info.
@Autowire EurekaClient to search another client using eurekaClient.getNextServiceFromEureka(“ServiceName” , “False”); [false means insecure way]
The above API will give you InstanceInfo object to get lot other information.


Discovery using RestTemplate :
Add a spring boot project and add Eureka Discovery Pom starter.
Add @ EnableEurekaClient
Add @Bean for getting a RestTemplate object. Add @LoadBalanced also for the same bean
Add  eureka.client.serviceUrl.defaultZone = http://peer2:8761/eureka
Use restTemplate.getForObject(“URI with application name” , “ return type")  API to get the another service.


Hystrix (Latency and Fault tolerance)  (Circuit breaker to remove the service,UI for health check) :

Ribbon (Load balancing) :

Zuul (Edge service and Routing)  (Gate to enter in to the system) : to be continues....
