# Welcome

This project was created to contain resources to support my notes on unit tests in java.

---

**PLEASE NOTE:**

- No proprietary source code has been used in this repository.
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

# Local setup

You need Java 17 and Apache Maven to build this project.

Feel free to install them as you like best, a suggestion is to use [Homebrew](https://brew.sh/) and [SDKMAN](https://sdkman.io/)

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
