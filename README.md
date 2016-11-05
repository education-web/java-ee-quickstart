# Java EE Quickstart Samples

Project goal is to observe core Java EE technologies in the sample CRUD application.

## How to set up the environment?

### Set up Wildfly

1. Download Wildfly at http://wildfly.org/downloads/Sample. This samples use version 10.1.0.Final.
2. Unpack the Wildfly archive.
3. Set the `WILDFLY_HOME` environment variable to point to the Wildfly installation directory.

### Set up PostgreSQL (only for production mode)

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

### Register PostgreSQL as datasource in Wildfly (only for production mode)

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

### Development mode

1. Launch Wildfly

For Windows execute following command:
```dos
start %WILDFLY_HOME%\bin\standalone.bat
```

For Unix-based execute following command:
```shell
%WILDFLY_HOME%/bin/standalone.sh &
```

2. From the command shell navigate to the latest sample, fro example: `jee-4-jsf` and execute command:
```shell
mvn wildfly:deploy
```

### Production mode

1. Launch Postgres

For Windows start the Service with name `PostgreSQL 9.6 Server` or similar for other PostgreSQL versions.

2. Launch Wildfly

For Windows execute following command:
```dos
start %WILDFLY_HOME%\bin\standalone.bat
```

For Unix-based execute following command:
```shell
%WILDFLY_HOME%/bin/standalone.sh &
```

3. From the command shell navigate to the latest sample, fro example: `jee-4-jsf` and execute command:
```shell
mvn wildfly:deploy
```