# Galavant

App for galavant.io

## Build and run

1.  Set environment variables.
    ```bash
    cp .env.example .env
    source .env
    ```

1.  Build app.
    ```bash
    ./gradlew clean build
    ```

1.  Run app.
    ```bash
    java -jar applications/galavant-app/build/libs/galavant-app.jar
    ```

## Deply

1.  Login to Heroku.
    ```bash
    heroku login
    ```

1.  Install the Java plugin.
    ```bash
    heroku plugins:install java
    ```

1.  Push the jar to Heroku.
    ```bash
    heroku deploy:jar applications/galavant-app/build/libs/galavant-app.jar --app galavant
    ```
