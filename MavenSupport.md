IN PROGRESS

# Introduction #
This example demonstrates the configuration of the matheclipse parser for a Maven project:

## Include release version ##
```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
        <modelVersion>4.0.0</modelVersion>
        <groupId>test</groupId>
        <artifactId>test-matheclipse</artifactId>
        <packaging>jar</packaging>
        <version>0.0.1</version>

	<dependencies>
		<dependency>
			<groupId>org.matheclipse</groupId>
			<artifactId>matheclipse-parser</artifactId>
			<version>0.0.7</version>
		</dependency>
	</dependencies>

...

...
        <repositories>
                <repository>
                        <id>org-matheclipse-repository</id>
                        <url>http://symja.googlecode.com/svn/maven-repository/</url>
                        <releases>
                                <enabled>true</enabled>
                        </releases>
                        <snapshots>
                                <enabled>false</enabled>
                        </snapshots>
                </repository>
        </repositories>
</project>
```

## Include snapshot version ##
```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
        <modelVersion>4.0.0</modelVersion>
        <groupId>test</groupId>
        <artifactId>test-matheclipse</artifactId>
        <packaging>jar</packaging>
        <version>0.0.1-SNAPSHOT</version>

	<dependencies>
		<dependency>
			<groupId>org.matheclipse</groupId>
			<artifactId>matheclipse-parser</artifactId>
			<version>0.0.8-SNAPSHOT</version>
		</dependency>
	</dependencies>

...

...
        <repositories>
                <repository>
                        <id>org-matheclipse-repository</id>
                        <url>http://symja.googlecode.com/svn/maven-snapshot-repository/</url>
                        <releases>
                                <enabled>false</enabled>
                        </releases>
                        <snapshots>
                                <enabled>true</enabled>
                        </snapshots>
                </repository>
        </repositories>
</project>
```