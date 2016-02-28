# publicfield-xjc

publicfield-xjc is a JAXB 2.0 XJC plugin for making generated classes simple and boilerplate-free:
* removes all getter & setter methods
* makes all fields public

See sample project [publicfield-xjc-sample](https://github.com/brbrt/publicfield-xjc-sample)

This plugin was inspired by [immutable-xjc](https://github.com/sabomichal/immutable-xjc)

## Usage

The plugin can be enabled with the '-publicfield' option.

### Maven

This plugin can be added to your maven based project with [JitPack](https://jitpack.io/)

You need to add your following snippet to your pom.xml
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

JitPack will build the project and download the required jar for you.

Now you are ready to add this plugin as a dependency to a JAXB plugin of choice!

The following example demonstrates the use of the publicfield-xjc plugin with the mojo jaxb2-maven-plugin.
```xml
<plugin>
<groupId>org.jvnet.jaxb2.maven2</groupId>
<artifactId>maven-jaxb2-plugin</artifactId>
<version>0.13.1</version>
<executions>
    <execution>
        <goals>
            <goal>generate</goal>
        </goals>
    </execution>
</executions>
<configuration>
    <schemaDirectory>src/main/resources</schemaDirectory>
    <schemaIncludes>
        <include>*.xsd</include>
    </schemaIncludes>
    <episode>false</episode>
    <extension>true</extension>
    <args>
        <arg>-publicfield</arg>
    </args>
    <plugins>
        <plugin>
            <groupId>com.github.brbrt</groupId>
            <artifactId>publicfield-xjc</artifactId>
            <version>v0.1</version>
        </plugin>
    </plugins>
</configuration>
</plugin>
```
