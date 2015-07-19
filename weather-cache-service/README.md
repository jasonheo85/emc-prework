Weather Cache Service
========

It caches weather information into GemFire. This application provides accesses GemFire data through a hypermedia-based RESTful front end.

__Architecture__

This application creates and retrieves Weather objects stored into GemFire using Spring Data REST. Spring Data REST takes the features of Spring HATEOAS and Spring Data Gemfire and combines them together automatically.

The Weather class is annotated @Region("weather"). When GemFire stores the class, a new entry is created inside that specific region. This class also has name marked with @Id. This is for internal usage to help GemFire track the data.

Although it is possible to package this service as a traditional WAR file for deployment to an external application server, the simpler approach demonstrated below creates a standalone application using Spring Boot It packages everything in a single, executable JAR file, driven by a good old Java main() method. Along the way, you use Spring¡¯s support for embedding the Tomcat servlet container as the HTTP runtime, instead of deploying to an external instance.

Continous Integration is achieved via the CloudBees-CloudFoundry Integration service wherein a git push to the repository. results in a build being triggered on CloudBees and a deployment to CloudFoundry if the build is stable.

> _Application Link on Pivotal Web Services_
http://......

__Plan__
 1. Create a gradle build to build a jar file
 2. Create a domain object Weather which contains zip code and precipitation
 3. Create Junit Tests to test Weather Cache Service using RestTemplate to post a Weather object to WeatherRepository and get a Weather object by zip
 4. Create WeatherRepository. This repository is an interface and allow to perform various operations involving Weather objects. It gets these operations by extending CrudRepository. At runtime, Spring Data REST will create an implementation of this interface automatically. Then it will use the @RepositoryRestResource annotation to direct Spring MVC to create RESTful endpoints at /weathers.
 5. Create Application class to make the Weather Cache Service application executable using Spring Boot
 6. Configure a Cpntinuous Integration using CloudBees Jenkins https://....
    - CloudBees Jenkins as a service builds the jar, runs tests and pushes to http://....