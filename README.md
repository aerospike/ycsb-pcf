# YCSB for Pivotal Cloud Foundry

This is a [YCSB](https://github.com/brianfrankcooper/YCSB) web application that can be deployed into a Pivotal Cloud Foundry environment. It includes the core YCSB project as a submodule and wraps a web application around it. 

## Dependencies

* `gradle`

## Installation

### Building

    $ git submodule update --init
    $ gradle configureYCSB
    $ gradle assemble

### Deploy to PCF

    $ cf push ycsb -p build/libs/ycsb-web-0.0.9-SNAPSHOT.jar

**Note:** Use current build version for ycsb-web jar file.

## Extending

Adding a new database client can be done by implementing a new com.aerospike.config.IServiceConfiguration class and modifying com.aerospike.config.AppConfiguration to map from the "label" of the Pivotal service to your new IServiceConfiguration implementation.
