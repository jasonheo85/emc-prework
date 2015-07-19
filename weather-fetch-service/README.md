Weather Fetch Service
========

It fetches and then caches weather information into GemFire through the Weather Cache Service application by posting Weather object using Spring RestTemplate. In this project, it randomly and periodically generates precipitation for zip codes instead of fetching weather information through a third party weather API.

__Architecture__

This application randomly and periodically generates precipitation for each zip code. The Spring Boot's Application class implements CommandLineRunner interface and it randomly and periodically generates precipitationand creates Weather objects for zip codes in CommandLineRunner interface's run methods. After creating Weather objects, it posts the objects to the Weather Cache Service application and in turn these are cached into GemFire.

Although it is possible to package this service as a traditional WAR file for deployment to an external application server, the simpler approach demonstrated below creates a standalone application using Spring Boot It packages everything in a single, executable JAR file, driven by a good old Java main() method. Along the way, you use Spring¡¯s support for embedding the Tomcat servlet container as the HTTP runtime, instead of deploying to an external instance.

Continous Integration is achieved via the CloudBees-CloudFoundry Integration service wherein a git push to the repository. results in a build being triggered on CloudBees and a deployment to CloudFoundry if the build is stable.

> _Application Link on Pivotal Web Services_
http://......

__Plan__
 1. Create a gradle build to build a jar file
 2. Create a domain object Weather which contains zip code and precipitation
 3. Create Application class to make the Weather Cache Service application executable using Spring Boot
 4. Configure a Cpntinuous Integration using CloudBees Jenkins https://....
    - CloudBees Jenkins as a service builds the jar, runs tests and pushes to http://....