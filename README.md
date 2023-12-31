# Welcome

This project was created to contain resources to support my notes on unit tests in java.

---

**PLEASE NOTE:**

- **No proprietary source code has been used in this repository**.
- The test object classes are abstract in their purpose, purely for illustrative purposes, and in no way connected to
  any production code.
- The test libraries used - as well as the frameworks used as examples - are public domain.

---

This project consists of several modules, each of which represents a specific 'step' in testing.

The libraries used fall into two categories:

- testing
  libraries:
    - [JUnit 4](https://junit.org/junit4/)
    - [JUnit 5](https://junit.org/junit5/)
    - [Assertj](https://assertj.github.io/doc/)
    - [Mockito](https://site.mockito.org/)
    - [WireMock](https://wiremock.org/)
- application frameworks, to illustrate testing using these frameworks
    - [OSGI](https://docs.osgi.org/specification/)
    - [Quarkus](https://quarkus.io/)
    - [Spring Boot](https://spring.io/projects/spring-boot)

# Step 01

Simple class testing, a very simple test of a **single class**, without any framework.

You will see modules `step01-<xxx>`, please refer to each module's `README.md` for information.

---

# Step 02

Testing a service class, with collaborators.

This kind of test is very important, and shows how and why we need to use
Mockito to create 'mock' collaborators and stubbing them to simulate their desired behaviour for the test cases.

You will see modules `step02-<xxx>`, please refer to each module's `README.md` for information.

---

# Step 03

Testing a service class, with collaborators, and interaction with an external REST service.

This kind of test is very important, it shows how we can use WireMock to test our REST-client based classes.

You will see modules `step03-<xxx>`, please refer to each module's `README.md` for information.

---

# Local setup

You need Java 17 and Apache Maven to build this project.

Feel free to install them as you like best, a suggestion is to use [Homebrew](https://brew.sh/)
and [SDKMAN](https://sdkman.io/)

## Install Homebrew

Open a terminal and run

```shell
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

## Install SDKMAN

Open a terminal and run

```shell
curl -s "https://get.sdkman.io" | bash
```

I also suggest you edit your ` ~/.sdkman/etc/config` file, and add/update the entry`sdkman_auto_env=true`

## Install and use Java 17

Open a terminal and run

```shell
sdk install java 17.0.7-tem
sdk default java 17.0.7-tem
```

## Install Apache Maven

Open a terminal and run

```shell
brew install maven
```

## Build the project

You can build the project by running the following command in a terminal, from the project's root folder:

```shell
mvn clean install
```

(if you want to build the Javadoc as well)

### Build and generate JavdDoc

```shell
mvn clean install -Pjavadoc
```

After the build is completed, in each module's `target/site/apidocs` folder you can find the generated javadoc.

### Test coverage report

After the build is completed, in each module's `target/site/jacoco` folder you can find the test coverage report.
