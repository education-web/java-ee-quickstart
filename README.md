# Java EE Quickstart Samples

Project goal is to observe core Java EE technologies in the sample CRUD application.

## Set up the environment

### Set up Wildfly

1. Download Wildfly at http://wildfly.org/downloads/Sample. Sample uses version 10.1.0.Final.

2. Unpack the Wildfly archive.

3. Set the `WILDFLY_HOME` environment variable to point to the Wildfly installation directory.

### Set up PostgreSQL

1. Download PostgreSQL at https://www.postgresql.org/download/
2. Connect to PostgreSQL, i.e. using psql or pgAdmin.
3. Execute the following SQL commands:
```sql
DROP DATABASE IF EXISTS jee;
CREATE DATABASE jee;
DROP USER IF EXISTS jee;
CREATE USER jee WITH PASSWORD 'jee';
GRANT ALL PRIVILEGES ON DATABASE jee to jee;
```

### Register PostgreSQL as datasource in Wildfly

#### For Windows

Using command shell navigate to current folder.

Execute following commands:
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

## Run application

1. Launch Wildfly
2. Launch PostgreSQL
3. From the command shell navigate to `jee-4-jsf` and execute command:
```shell
mvn wildfly:deploy
```
