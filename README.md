# kie-sample-project

This is an example project using Kie Drools rule engine. Project uses Kie 6.1.0.Final.
This sample project shows how to use drools using kie compiler and kie spring.

# Project descriptions
* kie-sample-project ................................. Parent Project
* kie-rules-module1 .................................. Maven artifact which contiains pre-compiled rules
* kie-rules-module2 .................................. Maven artifact which contiains pre-compiled rules and depends * on kie-rules-module1 rules
* kie-rules-module3 .................................. Maven artifact which contiains pre-compiled rules
* kie-webapp-with-rule-module ........................ Web Application which uses kie-rules-modules[1|2|3]
* kie-webapp-with-no-rule-module ..................... Web Application which uses kie-spring API (does not use pre-compiled rules)

# Build

```
 cd <project directory> 
 mvn clean package
```

# Run Web App

1. Install
  ```
   cd <project directory>
   mvn clean install
  ```
2. Go to the directory of the web application you want to run
  ```
   # runs kie-webapp-with-rule-module   
   cd <project directory>/kie-webapp-with-rule-module
   # runs kie-webapp-with-no-rule-module
   cd <project directory>/kie-webapp-with-no-rule-module
   mvn jetty:run
  ```
3. Open web browser using url http://localhost:8080/<web app artifact name>. Example:
   * http://localhost:8080/kie-webapp-with-rule-module
   * http://localhost:8080/kie-webapp-with-no-rule-module
    
  
 
