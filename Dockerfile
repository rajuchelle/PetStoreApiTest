FROM maven:3.6.0-jdk-8
WORKDIR petstore/apitest
COPY pom.xml pom.xml
COPY testng.xml testng.xml
COPY src/test/ExtentReports src/test/ExtentReports
COPY src/test/java src/test/java
COPY src/test/Resources src/test/Resources

# CMD ["mvn","clean", "test"]
ENTRYPOINT mvn clean test