### Setup wildfly ###

```shell
$ wget https://download.jboss.org/wildfly/19.0.0.Final/wildfly-19.0.0.Final.zip

$ unzip wildfly-19.0.0.Final.zip

# Download driver
$ wget https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java-5.1.49.zip

$ unzip mysql-connector-java-5.1.49.zip

$ mkdir -p wildfly-19.0.0.Final/modules/system/layers/base/com/mysql/main

$ cp mysql-connector-java-5.1.49/mysql-connector-java-5.1.49-bin.jar wildfly-19.0.0.Final/modules/system/layers/base/com/mysql/main
```

##### Driver module ####
wildfly-19.0.0.Final/modules/system/layers/base/com/mysql/main/module.xml 
```xml
<module xmlns="urn:jboss:module:1.5" name="com.mysql">
    <resources>
        <resource-root path="mysql-connector-java-5.1.49-bin.jar"/>
    </resources>
    <dependencies>
        <module name="javax.api"/>
        <module name="javax.transaction.api"/>
    </dependencies>
</module>
```

#### Config datasource ####
wildfly-19.0.0.Final/standalone/configuration/standalone.xml
```xml
<drivers>
    <driver name="mysql" module="com.mysql">
        <driver-class>com.mysql.jdbc.Driver</driver-class>
        <xa-datasource-class>com.mysql.jdbc.jdbc2.optional.MysqlXADataSource</xa-datasource-class>
    </driver>
</drivers>

<datasources>
    <datasource jndi-name="java:jboss/datasources/MySqlDS" pool-name="MySqlDS">
        <connection-url>jdbc:mysql://localhost/ejb_application</connection-url>
        <driver-class>com.mysql.jdbc.Driver</driver-class>
        <driver>mysql</driver>
        <security>
            <user-name>user_here</user-name>
            <password>password_here</password>
        </security>
    </datasource>
<datasources>
```

### Create user ###  

```shell
# Application User: app-user1 - groups app-users 
$ bash wildfly-19.0.0.Final/bin/add-user.sh
```

### Start server ###

```shell
$ bash wildfly-19.0.0.Final/bin/standalone.sh
```

### Deploy application ###

```shell
$ bash wildfly-19.0.0.Final/bin/jboss-cli.sh --connect --command="deploy --force ejb-application/ear-application/target/ear-application.ear"
```
