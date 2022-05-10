# Income-Tax-Software-Choices-Performance-Tests

Performance test suite for the `Income-Tax-Software-Choices-Performance`, using [performance-test-runner](https://github.com/hmrc/performance-test-runner) under the hood.

* [Quick start](#Quick-start)
  - [Prerequisites](#Prerequisites)
  - [How to start](#How-to-start)
  - [How to use](#How-to-use)
* [Persistence](#Persistence)

# Quick start

## Prerequisites

* [sbt](http://www.scala-sbt.org/)
* MongoDB (*[See Persistence](#Persistence)*)
* HMRC Service manager (*[Install Service-Manager](https://github.com/hmrc/service-manager/wiki/Install#install-service-manager)*)

## How to start

### Before running any tests

Start the services with `./scripts/run_services.sh`

Params:
* Additional sm parameters such as `--offline` can be added if desired

### Running the tests

#### Smoke test

Run performance tests with one user.  

*Against services running locally:*
```
sbt -Dperftest.runSmokeTest=true -DrunLocal=true gatling:test
```

*Against staging:*
```
sbt -Dperftest.runSmokeTest=true gatling:test
```

#### Running the performance test

*Against services running locally:*
```
sbt -DrunLocal=true gatling:test
```

*Against staging:*
```
sbt gatling:test
```

## How to use

### Journeys

Example:
```
journeys {

  # Example
  # Give a name to the journey.
  example-journey = {

    # The description will appear in the test report. Use something meaningful
    description = "Example journey"

    # The load is in journeys per second. Put here the load you are going to have at the peak.
    # There is no need to put a higher value at this point. Use prerftest.loadPercentage in application.conf instead
    load = 9.1

    # This points to a csv file with the data you need to inject in the journey. [More here](https://github.com/hmrc/performance-test-runner#step-4-configure-the-user-feeder)
    feeder = data/example.csv

    # The parts your journey is made of. A part is made one or more requests.
    parts = [
      home-page,
      post-vat-return-period,
      get-turnover-page
    ]
  }

}
```

### Logging

The template uses [logback.xml](src/test/resources) to configure log levels. The default log level is *WARN*. This can be updated to use a lower level for example *TRACE* to view the requests sent and responses received during the test.

### Scalafmt

 This repository uses [Scalafmt](https://scalameta.org/scalafmt/), a code formatter for Scala. The formatting rules configured for this repository are defined within [.scalafmt.conf](.scalafmt.conf).

 To apply formatting to this repository using the configured rules in [.scalafmt.conf](.scalafmt.conf) execute:

 ```
 sbt scalafmtAll
 ```

 To check files have been formatted as expected execute:

 ```
 sbt scalafmtCheckAll scalafmtSbtCheck
 ```

[Visit the official Scalafmt documentation to view a complete list of tasks which can be run.](https://scalameta.org/scalafmt/docs/installation.html#task-keys)

# Persistence

Data is stored as key/value in Mongo DB. See json reads/writes implementations (especially tests) for details.

To connect to the mongo db provided by docker (recommended) please use

```
docker exec -it mongo-db mongosh
```

Various commands are available.  Start with `show dbs` to see which databases are populated.
