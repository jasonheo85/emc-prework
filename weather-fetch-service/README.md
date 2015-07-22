Weather Fetch Service
========

It fetches and then caches weather information into GemFire through the Weather Cache Service application by posting Weather object using Spring RestTemplate. In this project, it randomly and periodically generates precipitation for zip codes instead of fetching weather information through a third party weather API.

__Architecture__

This application randomly and periodically generates precipitation for each zip code. The Spring Boot's Application class enables Scheduling using @EnableScheduling and an annotated class (ScheduledTasks) with @Scheduled calls WeatherFetchService which randomly generates precipitation for zip codes and post Weather objects to WeatherCacheService using RestTemplate. Then, the Weather Cache Service application and in turn these are cached into GemFire.

This application gets System properties for server name and port number of the Weather Cache Service application in order to get environment specific configuration variables. It can be set using "cf set-env' or "env" section in manifest.yml when pushing application to Cloud Foundry.

Although it is possible to package this service as a traditional WAR file for deployment to an external application server, the simpler approach demonstrated below creates a standalone application using Spring Boot It packages everything in a single, executable JAR file, driven by a good old Java main() method. Along the way, you use Spring¡¯s support for embedding the Tomcat servlet container as the HTTP runtime, instead of deploying to an external instance.

Continous Integration is achieved via the CloudBees-CloudFoundry Integration service wherein a git push to the repository. results in a build being triggered on CloudBees and a deployment to CloudFoundry if the build is stable.

> _Application Link on Pivotal Web Services_
http://weather-fetch-service.cfapps.io/
It responds with 404 Not Found because it is a standalone application and it doesn't accept web request

__Plan__
 1. Create a gradle build to build a jar file
 2. Create a domain object Weather which contains zip code and precipitation
 3. Create Application class to make the Weather Cache Service application executable using Spring Boot
 4. Configure a Continuous Integration using CloudBees Jenkins https://jasonheo85.ci.cloudbees.com/?cloudbees.platform
    - CloudBees Jenkins builds the jar, runs tests and tried to push to http://weather-fetch-service.cfapps.io/, but it threw java.lang.NoClassDefFoundError: Could not initialize class org.apache.tomcat.websocket.WsWebSocketContainer
 7. Create Application Manifest manifest.yml to push Weather Fetch Service jar to http://weather-cache-service.cfapps.io using Cloud Foundry CLI