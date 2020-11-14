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

### Log configuration

If we want to change log file path or log level we must create an `application.properties` file nex to `prices-rest-api-1.0.0-SNAPSHOT.jar` and configure next `properties`:

* `logging.file.name`: Directory and file name for the Log.
* `logging.level.ga.ferpinan`: Log level

Example:

```
logging.file.name=/home/logs/lim-ais-aidx-transform.log
logging.level.ga.ferpinan=INFO
```

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