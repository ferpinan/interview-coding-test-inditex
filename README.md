# Prices Rest Api

## Requirements

* Java 8
* Maven 3

## How to compile app

In a `command prompt` or a `shell prompt`:

1. `cd` to project directory
2. run `mvn clean package`

## How to run the app

1. `cd` to project directory
2. `cd target`
3. `java -jar prices-rest-api-1.0.0-SNAPSHOT.jar`

### Examples URL-s

* http://localhost:8080/api/price/1/35455?date=2020-06-14T13:00:00Z
* http://localhost:8080/api/price/1/35455?date=2019-06-14T13:00:00Z
* http://localhost:8080/api/price/2/35455?date=2020-06-14T13:00:00Z
* http://localhost:8080/api/price/1/355?date=2020-06-14T13:00:00Z
* http://localhost:8080/api/price/1aa/355?date=2020-06-14T13:00:00Z

## How to run tests

1. `cd` to project directory
2. run `mvn test`

After running this command in target directory will be available `jacoco-ut/index.html` where we will be available to see code coverage.