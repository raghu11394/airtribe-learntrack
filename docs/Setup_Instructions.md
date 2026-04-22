# Setup Instructions

## JDK Version Used

- **JDK 17** (or later)

## Installation Steps

1. Download and install the JDK from [Oracle](https://www.oracle.com/java/technologies/downloads/) or use [OpenJDK](https://openjdk.org/).
2. Set the `JAVA_HOME` environment variable to your JDK installation directory.
3. Add `JAVA_HOME/bin` to your system `PATH`.

## Verify Installation

Open a terminal and run:

```bash
java -version
javac -version
```

You should see output similar to:

```
java version "17.0.x" ...
javac 17.0.x
```

## Hello World Program

Create a file `HelloWorld.java`:

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

Compile and run:

```bash
javac HelloWorld.java
java HelloWorld
```

**Expected output:**

```
Hello, World!
```

## Running LearnTrack

```bash
# From the project root directory
javac -d out src/com/airtribe/learntrack/**/*.java src/com/airtribe/learntrack/Main.java
java -cp out com.airtribe.learntrack.Main
```
