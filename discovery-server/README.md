The service discovery should be up and running simply by running the DiscoveryServerApplication. In dev mode, we don't need to register it with another Eureka Server. However in production, we need to register the second and later instance of this application to be registered with discovery server. So, eureka.client.register-with-eureka should be true. We also need to mention eureka.client.service-url.defaultZone=http://localhost:8761/eurekan in application.properties file.
