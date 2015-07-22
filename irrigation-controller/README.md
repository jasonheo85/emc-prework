Irrigation Controller
========

This application simulates Irrigation Controller. In reality, mobile OS operated and WiFi enabled irrigation controller will send requests to Weather Cache Service when it is supposed to water and get Weather object for the zip code which is already set in the controller. Irrigation Controller will or not water according to this weather information.

__Architecture__

This application is Spring MVC Web application and provides front end UI using HTML and jQuery. A jQuery function sends an asynchronous (AJAX) request to a Spring controller method using "/weather" URL with a zip code which is entered by user in a input box in a browser.

The Spring MVC controller method calls getWeatherInfo method in WeatherService class. This method sends requests to Weather Cache Service application URL using RestTemplate for a zip code and then return the Weather object to the controller method. The controller method, in turn, returns a WeatherInfo object to jQuery function and it displays the information into the browser.

The jQuery function could send a request directly to the Weather Cache Service application using CORS (Cross-origin resource sharing), but it violates SOP (Same Origin Policy).

Although it is possible to package this service as a traditional WAR file for deployment to an external application server, the simpler approach demonstrated below creates a standalone application using Spring Boot It packages everything in a single, executable JAR file, driven by a good old Java main() method. Along the way, you use Spring¡¯s support for embedding the Tomcat servlet container as the HTTP runtime, instead of deploying to an external instance.

Continous Integration is achieved via the CloudBees-CloudFoundry Integration service wherein a git push to the repository. results in a build being triggered on CloudBees and a deployment to CloudFoundry if the build is stable.

> _Application Link on Pivotal Web Services_
http://irrigation-controller.cfapps.io/

__Plan__
 1. Create a gradle build to build a jar file
 2. Create a domain object Weather which contains zip code and precipitation
 3. Create a domain object WeatherInfo for view which contains zip code, precipitation, waterOrNot (boolean) and message
 4. Create Application class to make the Irrigation Controller application executable using Spring Boot
 5. Create IrrigationController class as a Spring MVC Controller
 6. Create WeatherService class to connect to Weather Cache Service application using RestTemplate
 7. Create home.html and irrigation.js using jQuery
 8. Configure a Continuous Integration using CloudBees Jenkins https://jasonheo85.ci.cloudbees.com/?cloudbees.platform
    - CloudBees Jenkins builds the jar, runs tests and tried to push to http://weather-fetch-service.cfapps.io/, but it threw java.lang.NoClassDefFoundError: Could not initialize class org.apache.tomcat.websocket.WsWebSocketContainer
 9. Create Application Manifest manifest.yml to push Weather Fetch Service jar to http://weather-cache-service.cfapps.io using Cloud Foundry CLI