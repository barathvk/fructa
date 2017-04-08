# Fructa

**Fructa** is a simple slot machine simulator. A demo is available at [fructa.ga](http://fructa.ga) and teh API is available at [api.fructa.ga](http://api.fructa.ga)

## Rules
  * The machine consists of 3 reels
  * Each reel will reveal one of 3 fruits (Apple, Banana or Citrus)
  * Each spin costs the player 1 token
  * If all 3 reels reveal the same fruit, the player receives a prize of 9 tokens
  * The player may play as many times as he/she chooses
## Odds
  * The odds of winning is 1 in 9 (3 permutations out of 27)
  * The house edge is 1 in 9 (the house charges 1 token per spin and provides a prize of 9 tokens, netting the user 8 tokens)
## Technical Details
  The server is written in **Java 8** with the **Spring Boot framework**. The API is exposed on port 3000
### Building the Application
  * The application can be built by running ```mvn clean package``` in the root directory of the source code. This will provide a single jar named **fructa.jar** in the **build** directory.
  * There is also a **Dockerfile** included with the source code.
  * The application is available on **Dockerhub** at *lennonsaves/fructa*
  * Also included is a **travis.yml** file to provide CI/CD capabilities
### Running the application
  The application can be executed in one of several ways:
   * Running ```java -jar fructa.jar```
   * Running the docker container ```docker run -d -p 3000:3000 lennonsaves/fructa```
   * Monitoring and UI require running ```docker-compose up -d```
   *  3 users have been created (player1, player2, player3) all have a password of `password`
### Monitoring
  The application exposes a **Prometheus** metrics exporter. Running the docker-compose file will start up the following:
  * Fructa API (api.fructa.ga)
  * Fructa UI (fructa.ga)
  * Prometheus push gateway (gateway.fructa.ga)
  * Grafana for dashboards (metrcs.fructa.ga)
  * Prometheus for collecting metrics (prom.fructa.ga)
  * Traefik stats exporter (not exposed)
  * Traefik proxy (traefik.fructa.ga)
#### Metrics
  The following metrics are reported to the Prometheus server in real time:
  * Default metrics for JVM performance and use
  * user_balance: the number of tokens each user possess at any given time
  * house_balance: the number of tokens the house holds at any given time
  * spin_counter: tracks the total number of spins and number of wins per user
### API
The API consists of 3 services:
  * POST - Login service [api.fructa.ga/login](http://api.fructa.ga/login): Retrieves a JSON Web Token from the server for the user. The data is `application/json` and is of the format `{ id: String, password: String }`
    The response is a string with the JWT. This token is required as the `Authorization` header for all subsequent requests
  * GET - Balance service [api.fructa.ga/account/balance](http://api.fructa.ga/account/balance): Retrieves the user balance
  * GET - Transaction service [api.fructa.ga/account/transactions](http://api.fructa.ga/account/transactions): Retrieves the user transactions
  * GET - Spin service [api.fructa.ga/spin](http://api.fructa.ga/spin): Executes a spin and returns the results