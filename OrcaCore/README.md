# OrcaAdminApplication

This application will form the Admin UI portion for the Orca environment.

# OpenAPI
The application uses the OpenAPI library to generate the client stubs files needed to communicate with the APi's made available. Generation of these stub files does not 
happen when the project gets compiled or packaged, but is done on-demand.<br>

## Required files
A JSON files is required as input parameter as reference for the API spec. 
This file is located in the src/main/resources/api-spec folder and is called orca-inline.json. The file name and location is configured in the pom, but please keep as is. 
When the API has changed, extract this file from the OrcaPublicApi.orcaAPI-aws/target folder.

## Generating the stubs
To generate the stubs, run the following mvn command:
```aidl
 mvn -P generate-api-stubs -DskipTests -Dsupport-files=false
```
NOTE: The maven process above will complain about duplicate classes as well as some problems with "symbol not found". Safely ignore this.
This will execute maven and call the profile "generate-api-stubs". An option is the "support-files" parameter as true/false.<br>
The support files are a lot of Doc files as well as other files required to make the generated sources a complete maven/gradle
project. This is not required, thus is switched of by default.
Running without the support files enabled will generate the following packages
* generated-sources --docs
* generated-sources --src..api.client and src..api.model
* generated-sources --src..api.invoker <br>
The api.invoker package gets generated when running with support-files as true.

## Transfer generated code to application

Copy the docs folder over to the src/main/resources/docs folder for api documentation.
The Java code generated include the client and model packages, but not the invoker package(as default). The invoker
package is pretty static after the first generation, so should not be needed again unless something pretty big happens.
So when there is a change in the data that gets returned from the API, regenerate without the support files.
Copy over the client and model package to the src/main/java/com/ktk/orca/core/api package.

Now remove the src/main/generated-sources folder as it is not needed anymore.


# Database requirements.
The application uses MySQL as datastore. This will be changed to Aurora for production.
There are three databases defined, nl:
* development
* production
* unit-testing
<br>For unit testing H2 will be used.
The databases are defined in the appropriate yaml file in src/main/resources/config folder.

## Liquibase
Liquibase is used to generate the database changes to the database upon startup. To enable this we need a "changelog" in the Liquibase configuration.<br>
To add a new table:<br>
* Create new entity class in the model package
* Generate the changelog 
```aidl
mvn liquibase:diff
```
* Add the new changelog file into the master.xml config file for Liquibase.

# Application properties
The application properties are defined in the yml configuration files.
Each maven profile determines the yml file in use.
dev profile will use application-dev.yml, prod profile will use application-prod.yml and so forth.
Common properties are defined in the application.yml file. The profile specific files can also have the same
property defined. The property in the profile specific file will override the one in the common file.
The default profile is set to "dev", be specified on the maven path as/when required.

# Actuator
SpingBoot actuator is configured to provide insight and some admin functions. Goto the following url:
[http://localhost:8080/management](http://localhost:8080/management)

# Maven options
The following needs to be taken into account when running the build/test process with maven:
The project is setup to split the test between:<br>
* unit tests
* integration tests
<br>
## Unit tests
These tests are using the SureFire maven plugin to run short / concise tests. This typically tests single methods and not long running tests.
## Integration tests
These tests are using the Failsafe maven plugin to run full tests with actual integration to third party tersts systems.
#### Setting up tests
Annotating the test class with @Category(IntegrationTest.class) will mark them as an Integration tests.<br>
Tests without this annotation will be deemed short unit tests.
#### mvn testing
To run only the unit tests run mvn as follows:
```aidl
$ mvn clean test
```
To run the full set with integration tests:
```aidl
$ mvn clean install 
```
Mvn install runs the following steps:

    validate
    initialize
    generate-sources
    process-sources
    generate-resources
    process-resources
    compile
    process-classes
    generate-test-sources
    process-test-sources
    generate-test-resources
    process-test-resources
    test-compile
    process-test-classes
    test
    prepare-package
    package
    pre-integration-test
    integration-test
    post-integration-test
    verify
    install

So this will execute "verify" which is the actual profile step that will execute the integration tests.
### Mvn using S3 for internal libraries
S3 has been added with the S3Wagon plugin to enable S3 to be used as storage for the internal library files. Mvn will pull it from there by default.
To publish/push a locally build jar file run the following command:
```
mvn clean deploy
```


