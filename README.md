# Java EE Quickstart Samples

Project goal is to observe core Java EE technologies in the sample CRUD application.

## Set up the environment

### Download Wildfly 

Sample uses version 10.1.0.Final.

Link: http://wildfly.org/downloads/

### Setting `WILDFLY_HOME`

Unpack the Wildfly archive. 

Set the enviromental variable `WILDFLY_HOME` environment variable to point to the Wildfly installation directory.

### Registering the Postgres datasource in Wildfly

#### For Windows

Using cmd navigate to current folder.

These commands should be executed in cmd.exe
```dos
start %WILDFLY_HOME%\bin\standalone.bat
%WILDFLY_HOME%\bin\jboss-cli.bat --connect
```

These commands should be executed in jboss-cli
```
module add --name=org.postgres --resources=.\lib\postgresql-9.4.1211.jar --dependencies=javax.api,javax.transaction.api
/subsystem=datasources/jdbc-driver=postgres:add(driver-name="postgres",driver-module-name="org.postgres",driver-class-name=org.postgresql.Driver)
data-source add --name=JavaEEDS --jndi-name=java:jboss/datasources/JavaEEDS --driver-name=postgres --connection-url=jdbc:postgresql://localhost:5432/jee --user-name=jee --password=jee
```
