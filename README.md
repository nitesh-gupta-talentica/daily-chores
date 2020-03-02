# spring-cloud-demos

# Overview
This project demonstrates how to create a discovery server, discovery client and config management in a system comprising of multiple microservices. I have created 2 sample microservices named hello-service and goodbye-service. Both these services get themselves registered with a discovery server. 

I have also added a configuration server and config-client-app to demonstrate how to do the configuration management using spring-cloud-config.


# Steps
1. Run the discovery server. 
2. It should be up and running at http://localhost:8761. Test it on the browser.
3. Run the config server.
4. It should be up and running at http://localhost:8888. Test it on the browser.
5. Run the hello-service. It is configured to run at http://localhost:1111
6. Run the goodbye-service. It is configured to run at http://localhost:2222
7. Run the config-client-app. It is configured to run at port 80.
