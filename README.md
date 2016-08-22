#tango-notices-service Service
Sample base application, developed using Platform V1 that exposes some basic operations using the following modules:

    - bootloader
    - swagger-ui-module
    - jersey2-module
    - akka-system-module
    - security-module
    - cassandrasecurity-module
    - cassandra-module
    - dataprovider-module

The module must be built using the update-db profile that will run the DB groovy scripts. The properties defined in pom.xml are used:
  - seedAdresses
  - port
  - clusterName
  - keyspaceName

`mvn clean install -DskipTests -P update-db tomcat7:run`
    
This will start an instance of Tomcat v 7.0.47 with the application deployed under the context of Maven and the application can be accessed under: http://localhost:8080/

The services from `manifest.yml` file have to be created prior to pushing the application.
- tango-bs-identity
- tango-bs-cassandra
- log-drain-service

Configuration parameters are in `*.properties` files in `configuration` folder        

Add the following parameters: `service_oauth2_clientId`, `service_oauth2_clientSecret`, `service_oauth2_uaaUrl`
	
Add the following parameters: `cluster_name`, `cql_port`, `host`, `keyspace`, `read_consistency_level`, `write_consistency_level`

Casandra keyspace must be configured also on Admin Console. CQL port is 9042
    
TBD

	
`mvn clean compile war:war`

`cf push appName -b https://githubAccount:githubPass@github.com/wmgdsp/java-buildpack.git#1.12.1 --no-manifest -p target/tango-base-service.war -d domain`



- The service exposes the /test_entity resource that has the following two methods:
    - `GET  /test_entity/{id}`
    - `POST /test_entity`

- Calls to external services:
    - Using Jersey 2 (jersey2-module), AbstractExternalServices encapsulates some basic HTTP calls
    - In externalservices.properties are defined the external services connection details
    - User Token is set on the ThreadLocal (using UserTokenPostFilter and UserTokenPreFilter) so external calls can use it (see UserTokenHolder)
