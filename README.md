# StageLog
Annotated logging of the code

###

![build](https://github.com/0x100/stagelog/workflows/build/badge.svg?branch=master)
[![](https://jitpack.io/v/0x100/stagelog.svg)](https://jitpack.io/#0x100/stagelog)

## Features

- Logging of the start and/or end of the method
- Tracking time spent by the method

## Using

Just add the `@Stagelog` annotation above a method with required parameters:

```java
@Stage(startMessage = "Initialization...", finishMessage = "Initialization finished", trackTime = true)
private void init() {
    loadConfig();
    loadData();
    applyConfig();
}

@Stage(value = "Load config")
private void loadConfig() {
    // ...
    sleep(100);
}

@Stage(trackTime = true)
private void loadData() {
    // ...
    sleep(200);
}

@Stage("Apply config")
private void applyConfig() {
    // ...
    sleep(300);
}
```

Then build your app.

Result output:

```
Initialization...
Load config
Example.loadData()...
Example.loadData() finished in 00:00:00.206
Apply config
Initialization finished in 00:00:00.617
```

## How to add it in your project

### Maven

1. Add the `jitpack` repository in your `pom.xml`

    ```xml
    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>
    ```

2. Add the `stagelog` dependency

    ```xml
    <dependency>
        <groupId>com.github.0x100</groupId>
        <artifactId>stagelog</artifactId>
        <version>master-SNAPSHOT</version>
    </dependency>
    ```

### Gradle

1. Add the `jitpack` repository in your root `build.gradle` at the end of repositories:
    ```groovy
    allprojects {
        repositories {
            // ...
            maven { url 'https://jitpack.io' }
        }
    }
    ```

2. Add the dependency
    ```groovy
    dependencies {
        implementation 'com.github.0x100:stagelog:master-SNAPSHOT'
    }
    ```
    
## Examples

See examples in the code.

## How to contribute
Fork the repository, make changes, write a test for your code, send me a pull request. 
I will review your changes and apply them to the master branch shortly, provided they don't violate quality standards. 
To avoid frustration, before sending a pull request please run the Maven build:
```
$ mvn clean package
```

##

Good luck and have fun!