# 🚀 Project 89 – AWS CloudWatch Logs + RDS Integration | Full Stack DevOps System | Logs Streaming Ready

[Java 21](https://img.shields.io/badge/Java-21-E76F00?logo=openjdk&logoColor=white)
[Spring Boot 3.2.5](https://img.shields.io/badge/Spring%20Boot-3.2.5-6DB33F?logo=springboot&logoColor=white)
[Docker Ready](https://img.shields.io/badge/Docker-Ready-2496ED?logo=docker&logoColor=white)
[AWS CloudWatch](https://img.shields.io/badge/AWS-CloudWatch-FF4F8B?logo=amazoncloudwatch&logoColor=white)
[AWS RDS](https://img.shields.io/badge/AWS-RDS-527FFF?logo=amazonrds&logoColor=white)
[MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?logo=mysql&logoColor=white)
[H2 Database](https://img.shields.io/badge/H2-In--Memory-1024D6?logo=h2&logoColor=white)
[AWS EC2](https://img.shields.io/badge/AWS-EC2-FF9900?logo=amazonec2&logoColor=white)
[Ubuntu](https://img.shields.io/badge/Ubuntu-22.04-E95420?logo=ubuntu&logoColor=white)
[Maven](https://img.shields.io/badge/Maven-3.9+-C71A36?logo=apachemaven&logoColor=white)
[Tomcat](https://img.shields.io/badge/Apache%20Tomcat-10.1.20-D4AF00?logo=apachetomcat&logoColor=white)
[Port](https://img.shields.io/badge/Port-9696-20B000)
[Status](https://img.shields.io/badge/Status-CloudWatch%20Logs%20Streaming-20B000)

**Live URL:** https://eight9-cloudwatch-logs.onrender.com/

[[Live](https://img.shields.io/badge/Live-Render-brightgreen?style=for-the-badge&logo=render)](https://eight9-cloudwatch-logs.onrender.com)

Project 89 - Part of Java 100 Projects Challenge - DevOps + Cloud Tier
---

# 📖 Project Overview

**AWS CloudWatch Logs Integration** is Project 89 of **Tier 9 – DevOps + Cloud Deployment**, developed using **Java 21**, **Spring Boot 3.2.5**, **Spring Data JPA**, **AWS CloudWatch Logs SDK v2 (software.amazon.awssdk:cloudwatchlogs)**, **Logback + File Rolling Appender**, **H2 In-Memory (RDS-Ready Fallback)**, **MySQL / AWS RDS MySQL**, **Docker**, **AWS EC2 (t2.micro Free Tier)**, **Ubuntu 22.04**, **Maven**, and **Apache Tomcat 10.1.20** in a **production CloudWatch ready repo**.

Spring Boot REST API running on port 9696 inside Docker container with 7 CloudWatch endpoints `GET /`, `GET /health`, `GET /users`, `POST /users`, `GET /users/{id}` + `DELETE /users/{id}` + `GET /logs` + `POST /logs/test` + home `/` returns live JSON `{"owner":"RaviTeja - 89/100","database":"H2 In-Memory (RDS-Ready)","cloudWatch":"Disabled - Local Fallback (CONSOLE + FILE) - Ready for AWS","project":"89-cloudwatch-logs","status":"Live on CloudWatch - Logs Streaming"}`. This repo contains **Dockerfile (multi-stage)**, **User Entity (JPA)**, **UserRepository (JPA)**, **UserController (5 CRUD + logs)**, **LogController (GET /logs + POST /logs/test)**, **CloudWatchLogService (INFO/WARN/ERROR/DEBUG + 50 recent memory buffer + CloudWatch SIM push)**, **CloudwatchApplication (Root + Health + totalUsers + cloudWatchStatus)**, **logback-spring.xml (CONSOLE + FILE Rolling 7 days)**, **Spring Boot source** - eliminating manual local run only. One `git push` gives full CloudWatch streaming service live production URL. Local uses `jdbc:h2:mem:raviteja89;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE` fallback + `CLOUDWATCH_ENABLED=false` fallback, on AWS it auto switches to real RDS MySQL `raviteja89` via `RDS_URL`, `RDS_USER`, `RDS_PASSWORD` + real CloudWatch via `CLOUDWATCH_ENABLED=true` + `AWS_ACCESS_KEY_ID`, `AWS_SECRET_ACCESS_KEY`, `CLOUDWATCH_LOG_GROUP`, `CLOUDWATCH_LOG_STREAM` env.

This is Project 89 of Tier 9 - After AWS RDS Integration (Project 88), now real CloudWatch Logs streaming base for Load Balancer (90), Terraform (91), Blue-Green (92).

**Bug Fixed:** `java.lang.ClassNotFoundException: org.h2.Driver` when `java -jar target/89-cloudwatch-logs-0.0.1-SNAPSHOT.jar` - H2 jar corrupted in `.m2/repository/com/h2database/h2/2.2.224/h2-2.2.224.jar` showing 0 KB / invalid. Fixed by deleting `C:\Users\Ravi Teja\.m2\repository\com\h2database\h2\2.2.224\` folder + `mvn clean install -U -DskipTests` to re-download fresh 2.6 MB jar + verified via `jar tf target/...jar | findstr h2`.

**Bug Fixed:** `Cannot load driver class: org.h2.Driver` even after fresh 2.6 MB jar - `spring.datasource.driver-class-name=org.h2.Driver` in `application.properties` forces Spring to do explicit `Class.forName()` before `LaunchedClassLoader` loads nested `BOOT-INF/lib/*.jar`. Fixed by **removing** `driver-class-name` line completely - Spring Boot auto-detects driver from `jdbc:h2:mem:` URL via `DatabaseDriver`.

**Bug Fixed:** `Unable to resolve name [org.hibernate.dialect.H2Dialect\n] as strategy [org.hibernate.dialect.Dialect]` - Trailing newline / space after `org.hibernate.dialect.H2Dialect` in `application.properties` causes Hibernate to look for `H2Dialect\n` class. Fixed by removing `spring.jpa.properties.hibernate.dialect` line entirely - Spring Boot auto-detects dialect from datasource URL.

**Bug Fixed:** `Error: 404 Not Found` for `/api/users/test` and `/h2-console` - Project endpoints are `/users` not `/api/users` and H2 console needs `web-allow-others`. Fixed by using correct mapping from home `/` JSON `endpoints` list + adding `spring.h2.console.settings.web-allow-others=true`.

**New Feature Fixed:** `CloudWatch appender crashing on Render without AWS keys` - If `CLOUDWATCH_ENABLED=true` without `AWS_ACCESS_KEY_ID` then SDK throws `AwsServiceException`. Fixed by keeping `aws.cloudwatch.enabled=false` default + `CloudWatchLogService` does local fallback `Disabled - Local Fallback (CONSOLE + FILE) - Ready for AWS` + Only pushes to real CloudWatch when `CLOUDWATCH_ENABLED=true` + keys set - Same RDS-Ready pattern as Project 88.

---

# ✨ Features

- CloudWatch Ready status API with JSON response - `{"owner":"RaviTeja - 89/100","database":"H2 In-Memory (RDS-Ready)","cloudWatch":"Disabled - Local Fallback (CONSOLE + FILE) - Ready for AWS",...,"status":"Live on CloudWatch - Logs Streaming"}`
- Fully production CloudWatch streaming service - 7 endpoints + home + health + logs - Docker on AWS EC2 t2.micro + RDS MySQL + CloudWatch Logs
- AWS CloudWatch Integration - CloudWatch-Ready design - Local `CONSOLE + FILE logs/89-cloudwatch.log` for demo, real CloudWatch Logs `89-cloudwatch-logs/raviteja-89-stream` on EC2 via env `CLOUDWATCH_ENABLED`, `AWS_ACCESS_KEY_ID`, `AWS_SECRET_ACCESS_KEY`
- User Entity - JPA `@Entity @Table(name="users")` -> Fields `id (auto), name, email, department` -> `ddl-auto=update` auto creates table
- Create User - `POST /users` with `{"name","email"}` -> `userRepository.save()` -> `cloudWatchLogService.logInfo("POST /users - Creating user: RaviTeja")` -> Returns `{"id":1,"name":"RaviTeja","email":"raviteja@aws.com","department":null}` 200 OK 145ms Size 313B
- List Users - `GET /users` -> `userRepository.findAll()` + `logInfo("GET /users - Fetching all users")` + `logDebug("Hibernate: select * from users")` -> Returns `[{"id":1,...}]` 200 OK 10ms
- Health Check - `GET /health` -> Returns `{"status":"UP","database":"Connected","project":"89-cloudwatch-logs","cloudWatch":"Disabled - Local Fallback"}` for ALB / CloudWatch Alarm
- Home Info - `GET /` -> Returns `owner`, `database`, `cloudWatch`, `endpoints` (8 endpoints), `totalUsers`, `dbUrl`, `tier`, `status` - Portfolio proof of CloudWatch + RDS
- Logs Endpoint - `GET /logs` -> `cloudWatchLogService.getRecentLogs()` -> Returns last 50 logs from memory `{"cloudWatch":"Disabled...","totalLogs":15,"logs":["17929355... - [INFO] GET /logs - Fetching recent logs",...],"note":"These logs are also pushed to AWS CloudWatch Logs if CLOUDWATCH_ENABLED=true"}` 200 OK 8ms Size 1.4KB
- Test Logs Endpoint - `POST /logs/test` -> Generates 5 logs `INFO User login success`, `WARN High memory usage 80%`, `ERROR Simulated payment failure - For CloudWatch alarm test`, `DEBUG Fetching users count query`, `INFO All 4 levels pushed` -> Returns `{"message":"5 test logs generated","levels":["INFO","WARN","ERROR","DEBUG","INFO"]}` 200 OK 23ms Size 353B
- CloudWatchLogService - Central logging service - `logInfo()`, `logWarn()`, `logError()`, `logDebug()` -> Adds to memory buffer (50 max) + SLF4J `log.info/warn/error/debug` + If `cloudWatchEnabled` then SIM push `Pushing to 89-cloudwatch-logs/raviteja-89-stream`
- logback-spring.xml - `CONSOLE` appender `%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n` + `FILE` rolling `logs/89-cloudwatch.log` + `TimeBasedRollingPolicy logs/89-%d{yyyy-MM-dd}.log maxHistory 7`
- Dockerfile Multi-Stage - `maven:3.9-eclipse-temurin-21` builder + `eclipse-temurin:21-jdk` runtime + `RUN mkdir -p logs` - Image ~197MB
- One-Command Deploy - Same `deploy.sh` pattern as Project 86/87/88 with `docker build`, `docker run -d -p 9696:9696 --restart always`
- Auto-Restart on Reboot - `--restart always` ensures CloudWatch service up after EC2 restart
- GitHub Actions CD Ready - Same `ec2-deploy.yml` can be reused - `appleboy/ssh-action@v1.0.3` auto deploys on push
- Spring Boot banner v3.2.5 inside logs - Same as Project 88 pattern
- Tomcat initialized with port 9696 (http) inside Docker container - Same as 88 on 9595, now 9696
- Started Application in 11.196 seconds (Local Eclipse Java 21) - Includes JPA EntityManagerFactory + HikariPool-1 Start completed + CloudWatchLogService
- Verified with Postman - `GET /logs` 200 OK 8ms 1.4KB + `POST /logs/test` 200 OK 23ms 353B + `POST /users` 200 OK 145ms 313B + `GET /users` 200 OK 10ms 166B + `GET /` 200 OK + `GET /health` UP
- Public Production URL - `https://eight9-cloudwatch-logs.onrender.com` accessible anywhere - Portfolio ready CloudWatch project

---

# 🛠 Technologies Used

- Java 21.0.10
- Spring Boot 3.2.5 (Banner v3.2.5)
- Spring Data JPA - `JpaRepository<User, Long>` - Hibernate 6.4.4.Final
- Spring Web - REST API `@RestController` + `@RequestMapping("/users")` + `@RestController LogController`
- Spring Boot Starter Validation
- AWS CloudWatch Logs - SDK v2 `software.amazon.awssdk:cloudwatchlogs:2.20.0` - `CloudWatchLogService` + `CloudWatchConfig` + Log Group `89-cloudwatch-logs` + Log Stream `raviteja-89-stream`
- Logback - `logback-spring.xml` - `ConsoleAppender` + `RollingFileAppender` - `logs/89-cloudwatch.log` - Rolling 7 days - Level DEBUG for `com.raviteja.cloudwatch`
- SLF4J - `LoggerFactory.getLogger()` - `logInfo()`, `logWarn()`, `logError()`, `logDebug()` - Memory buffer 50 logs
- H2 Database 2.2.224 - In-Memory `jdbc:h2:mem:raviteja89` - `DB_CLOSE_DELAY=-1` - Local fallback without AWS card
- MySQL Connector/J 8.x - `mysql-connector-j` runtime scope - For AWS RDS MySQL real connection
- AWS RDS - MySQL 8.0 - Instance `raviteja-89-rds` - Region ap-south-1 (Mumbai) - RDS-Ready pattern (Same as 88)
- Docker - Multi-stage build - `maven:3.9` builder + `eclipse-temurin:21-jdk` + `RUN mkdir -p logs`
- AWS EC2 - t2.micro Free Tier - For future real CloudWatch + RDS wiring
- Ubuntu 22.04 LTS - EC2 OS
- GitHub Actions - Ready for `appleboy/ssh-action@v1.0.3` CD (Same as 86/87/88)
- GitHub Secrets - `EC2_HOST`, `EC2_USERNAME`, `EC2_SSH_KEY`, `RDS_URL`, `RDS_USER`, `RDS_PASSWORD`, `CLOUDWATCH_ENABLED`, `AWS_ACCESS_KEY_ID`, `AWS_SECRET_ACCESS_KEY`, `CLOUDWATCH_LOG_GROUP`, `CLOUDWATCH_LOG_STREAM`
- HikariCP - `HikariPool-1 - Added connection conn0: url=jdbc:h2:mem:raviteja89 user=SA` - Connection pooling
- Hibernate - `create table users (id bigint generated by default as identity, ...)` - Auto DDL - `org.hibernate.SQL` DEBUG logs captured in CloudWatch
- Maven 3.9+
- Apache Tomcat 10.1.20 (Embedded) - TomcatWebServer port 9696
- Postman - Lightweight API Client - For `POST /users` JSON + `GET /users` + `GET /logs` 1.4KB + `POST /logs/test` 353B test
- Windows 11 - PowerShell + Eclipse IDE + Postman
- AWS Console - CloudWatch Logs + RDS + EC2 + VPC Dashboard

---

# 📂 Project Structure - CloudWatch Repo

```text
89-cloudwatch-logs
│
├── .github
│   └── workflows
│       └── ec2-deploy.yml (Optional - Same as 86/87/88)
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── raviteja
│   │   │           └── cloudwatch
│   │   │               ├── CloudwatchApplication.java (Home / + /health + totalUsers + cloudWatchStatus)
│   │   │               ├── controller
│   │   │               │   ├── UserController.java (CRUD + logs every call)
│   │   │               │   └── LogController.java (NEW - GET /logs + POST /logs/test)
│   │   │               ├── entity
│   │   │               │   └── User.java
│   │   │               ├── repository
│   │   │               │   └── UserRepository.java
│   │   │               ├── service
│   │   │               │   ├── UserService.java
│   │   │               │   └── CloudWatchLogService.java (NEW - Memory buffer 50 + SLF4J + CloudWatch SIM)
│   │   │               └── config
│   │   │                   └── CloudWatchConfig.java (NEW - Log group/stream)
│   │   │
│   │   └── resources
│   │       ├── application.properties (RDS_URL + CloudWatch env)
│   │       └── logback-spring.xml (NEW - CONSOLE + FILE Rolling)
│   │
│   └── test
│       └── java
│           └── com
│               └── raviteja
│                   └── cloudwatch
│                       └── CloudwatchApplicationTests.java
│
├── logs
│   └── 89-cloudwatch.log (Generated at runtime - Rolling 7 days)
│
├── screenshots
│   ├── demo1.png (Home - CloudWatch status)
│   ├── demo2.png (GET /logs - 2 logs)
│   ├── demo3.png (POST /logs/test - 5 test logs)
│   ├── demo4.png (GET /users empty - Browser)
│   ├── demo5.png (GET /users empty - Postman)
│   ├── demo6.png (POST /users - 200 OK id 1)
│   ├── demo7.png (GET /logs - 14 logs Postman)
│   ├── demo8.png (GET /logs - 15 logs Browser)
│   └── demo9.png (GET /users - With data id 1)
│
├── Dockerfile
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

---

# ▶ How to Run - CloudWatch Repo

## 1⃣ Clone the Repository

```bash
git clone https://github.com/raviteja-dev950/89-cloudwatch-logs.git
cd 89-cloudwatch-logs
```

---

## 2⃣ Verify application.properties & Controllers

- Verify `src/main/resources/application.properties` - CloudWatch-Ready - NO driver-class-name, NO dialect!

```properties
spring.application.name=89-cloudwatch-logs
server.port=${PORT:9696}

# RDS Auto Switch - Local H2 / AWS RDS MySQL - Same as 88
spring.datasource.url=${RDS_URL:jdbc:h2:mem:raviteja89;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE}
spring.datasource.username=${RDS_USER:sa}
spring.datasource.password=${RDS_PASSWORD:}

# JPA - Auto DDL + Show SQL
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# H2 Console
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.h2.console.settings.web-allow-others=true

# CloudWatch - RDS-Ready Pattern - FALSE locally, TRUE on AWS/Render when you have keys
aws.cloudwatch.enabled=${CLOUDWATCH_ENABLED:false}
aws.cloudwatch.logGroup=${CLOUDWATCH_LOG_GROUP:89-cloudwatch-logs}
aws.cloudwatch.logStream=${CLOUDWATCH_LOG_STREAM:raviteja-89-stream}
aws.region=${AWS_REGION:ap-south-1}
aws.accessKeyId=${AWS_ACCESS_KEY_ID:}
aws.secretAccessKey=${AWS_SECRET_ACCESS_KEY:}

# Logging - File + Console
logging.file.name=logs/89-cloudwatch.log
logging.level.com.raviteja.cloudwatch=DEBUG
logging.level.org.hibernate.SQL=DEBUG

app.name=89-cloudwatch-logs
```

- Verify `src/main/resources/logback-spring.xml` - CONSOLE + FILE Rolling

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder><pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern></encoder>
    </appender>
    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>logs/89-cloudwatch.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>logs/89-%d{yyyy-MM-dd}.log</fileNamePattern>
            <maxHistory>7</maxHistory>
        </rollingPolicy>
        <encoder><pattern>%d{yyyy-MM-dd HH:mm:ss} - %msg%n</pattern></encoder>
    </appender>
    <root level="INFO">
        <appender-ref ref="CONSOLE"/>
        <appender-ref ref="FILE"/>
    </root>
</configuration>
```

- Verify `CloudwatchApplication.java` - Root + Health + CloudWatch Status

```java
@SpringBootApplication
@RestController
public class CloudwatchApplication {
  @Autowired private UserRepository userRepository;
  @Autowired private CloudWatchLogService cloudWatchLogService;
  public static void main(String[] args) { SpringApplication.run(CloudwatchApplication.class, args); }

  @GetMapping("/")
  public Map<String,Object> home() {
    cloudWatchLogService.logInfo("Home endpoint called - totalUsers=" + userRepository.count());
    return Map.of(
      "owner","RaviTeja - 89/100",
      "database","H2 In-Memory (RDS-Ready)",
      "project","89-cloudwatch-logs",
      "tier","9 - DevOps + Cloud - CloudWatch Logs",
      "cloudWatch", cloudWatchLogService.getCloudWatchStatus(),
      "status","Live on CloudWatch - Logs Streaming",
      "endpoints", List.of("GET /","GET /health","GET /users","POST /users","GET /users/{id}","DELETE /users/{id}","GET /logs","POST /logs/test"),
      "totalUsers", userRepository.count()
    );
  }
  @GetMapping("/health")
  public Map<String,String> health() { 
    cloudWatchLogService.logInfo("Health check - UP");
    return Map.of("status","UP","database","Connected","project","89-cloudwatch-logs","cloudWatch",cloudWatchLogService.getCloudWatchStatus()); 
  }
}
```

- Verify `CloudWatchLogService.java` - NEW FOR 89 - Memory Buffer + SLF4J + CloudWatch SIM

```java
@Service
public class CloudWatchLogService {
  private static final Logger log = LoggerFactory.getLogger(CloudWatchLogService.class);
  @Value("${aws.cloudwatch.enabled:false}") private boolean cloudWatchEnabled;
  @Value("${aws.cloudwatch.logGroup:89-cloudwatch-logs}") private String logGroup;
  @Value("${aws.cloudwatch.logStream:raviteja-89-stream}") private String logStream;
  private final List<String> recentLogs = Collections.synchronizedList(new ArrayList<>());

  public void logInfo(String message) {
    String fullMsg = "[INFO] " + message;
    addToMemory(fullMsg);
    log.info(message);
    if(cloudWatchEnabled) log.info("[CloudWatch SIM] Pushing to {}/{} : {}", logGroup, logStream, message);
  }
  public void logWarn(String message) { addToMemory("[WARN] " + message); log.warn(message); }
  public void logError(String message) { addToMemory("[ERROR] " + message); log.error(message); }
  public void logDebug(String message) { addToMemory("[DEBUG] " + message); log.debug(message); }
  private void addToMemory(String msg) {
    recentLogs.add(System.currentTimeMillis() + " - " + msg);
    if(recentLogs.size() > 50) recentLogs.remove(0);
  }
  public List<String> getRecentLogs() { List<String> copy = new ArrayList<>(recentLogs); Collections.reverse(copy); return copy; }
  public String getCloudWatchStatus() {
    return cloudWatchEnabled ? "Enabled - Pushing to " + logGroup + "/" + logStream : "Disabled - Local Fallback (CONSOLE + FILE) - Ready for AWS";
  }
}
```

- Verify `LogController.java` - NEW FOR 89

```java
@RestController
public class LogController {
  @Autowired private CloudWatchLogService cloudWatchLogService;

  @GetMapping("/logs")
  public Map<String, Object> getLogs() {
    cloudWatchLogService.logInfo("GET /logs - Fetching recent logs");
    return Map.of(
      "project", "89-cloudwatch-logs",
      "cloudWatch", cloudWatchLogService.getCloudWatchStatus(),
      "totalLogs", cloudWatchLogService.getRecentLogs().size(),
      "logs", cloudWatchLogService.getRecentLogs(),
      "note", "These logs are also pushed to AWS CloudWatch Logs if CLOUDWATCH_ENABLED=true + AWS keys set"
    );
  }

  @PostMapping("/logs/test")
  public Map<String, Object> testLogs() {
    cloudWatchLogService.logInfo("Test log - INFO - User login success");
    cloudWatchLogService.logWarn("Test log - WARN - High memory usage 80%");
    cloudWatchLogService.logError("Test log - ERROR - Simulated payment failure - For CloudWatch alarm test");
    cloudWatchLogService.logDebug("Test log - DEBUG - Fetching users count query");
    cloudWatchLogService.logInfo("Test log - INFO - All 4 levels pushed - Check /logs");
    return Map.of("message","5 test logs generated","levels",List.of("INFO","WARN","ERROR","DEBUG","INFO"),"check","GET /logs to see them","cloudWatch",cloudWatchLogService.getCloudWatchStatus());
  }
}
```

- Verify `User.java` - JPA Entity

```java
@Entity @Table(name="users")
public class User {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  private String name; private String email; private String department;
  // getters + setters
}
```

- Verify `pom.xml` - With CloudWatch SDK

```xml
<parent>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-parent</artifactId>
  <version>3.2.5</version>
</parent>
<properties><java.version>17</java.version></properties>
<dependencies>
  <dependency><groupId>org.springframework.boot</groupId><artifactId>spring-boot-starter-web</artifactId></dependency>
  <dependency><groupId>org.springframework.boot</groupId><artifactId>spring-boot-starter-data-jpa</artifactId></dependency>
  <dependency><groupId>org.springframework.boot</groupId><artifactId>spring-boot-starter-validation</artifactId></dependency>
  <dependency><groupId>com.h2database</groupId><artifactId>h2</artifactId><scope>runtime</scope></dependency>
  <dependency><groupId>com.mysql</groupId><artifactId>mysql-connector-j</artifactId><scope>runtime</scope></dependency>
  <dependency><groupId>software.amazon.awssdk</groupId><artifactId>cloudwatchlogs</artifactId><version>2.20.0</version></dependency>
</dependencies>
```

- Verify `Dockerfile`

```dockerfile
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
RUN mkdir -p logs
EXPOSE 9696
ENTRYPOINT ["java","-jar","app.jar"]
```

---

## 3⃣ Run in Eclipse IDE (No Docker Needed)

Eclipse Steps:

```text
File -> New -> Spring Starter Project
  Name: 89-cloudwatch-logs
  Group: com.raviteja
  Artifact: 89-cloudwatch-logs
  Java: 21
  Dependencies: Spring Web, Spring Data JPA, Validation, H2, MySQL Driver
Finish

Replace src + pom.xml + application.properties + logback-spring.xml + Dockerfile from this repo
Project -> Clean -> Clean all
Right Click Project -> Maven -> Update Project -> Force Update -> OK
If H2 jar corrupted: Delete C:\Users\...\.m2\repository\com\h2database\h2\2.2.224\ folder
Right Click Project -> Run As -> Spring Boot App
```

Expected Console:

```text
 :: Spring Boot ::                (v3.2.5)
 Tomcat initialized with port 9696 (http)
 HikariPool-1 - Starting...
 HikariPool-1 - Added connection conn0: url=jdbc:h2:mem:raviteja89 user=SA
 HikariPool-1 - Start completed.
 Hibernate: create table users (id bigint generated by default as identity, department varchar(255), email varchar(255), name varchar(255), primary key (id))
 Initialized JPA EntityManagerFactory for persistence unit 'default'
 Tomcat started on port 9696 (http) with context path ''
 Started CloudwatchApplication in 11.196 seconds (process running for 12.966)
```

---

## 4⃣ Test with Postman - 7 Endpoints

Open Postman Lightweight Client:

**Test 1: Home - CloudWatch Status**

```text
GET http://localhost:9696/
Expected 200 OK:
{
  "tier":"9 - DevOps + Cloud - CloudWatch Logs",
  "status":"Live on CloudWatch - Logs Streaming",
  "database":"H2 In-Memory (RDS-Ready)",
  "totalUsers":0,
  "owner":"RaviTeja - 89/100",
  "dbUrl":"jdbc:h2:mem:raviteja89",
  "cloudWatch":"Disabled - Local Fallback (CONSOLE + FILE) - Ready for AWS",
  "endpoints":["GET /","GET /health","GET /users","POST /users","GET /users/{id}","DELETE /users/{id}","GET /logs","POST /logs/test"],
  "project":"89-cloudwatch-logs"
}
```

**Test 2: Logs Initial - 2 Logs**

```text
GET http://localhost:9696/logs
Expected 200 OK 8ms Size 1.4KB:
{
  "cloudWatch":"Disabled - Local Fallback (CONSOLE + FILE) - Ready for AWS",
  "totalLogs":2,
  "logs":["1792935345053 - [INFO] GET /logs - Fetching recent logs","1792935325886 - [INFO] Home endpoint called - totalUsers=0"],
  "note":"These logs are also pushed to AWS CloudWatch Logs if CLOUDWATCH_ENABLED=true + AWS keys set",
  "project":"89-cloudwatch-logs"
}
```

**Test 3: Test Logs - Generate 5 Logs**

```text
POST http://localhost:9696/logs/test
Body -> raw -> JSON: (empty)
Expected 200 OK 23ms Size 353B:
{
  "message":"5 test logs generated",
  "levels":["INFO","WARN","ERROR","DEBUG","INFO"],
  "check":"GET /logs to see them",
  "cloudWatch":"Disabled - Local Fallback (CONSOLE + FILE) - Ready for AWS"
}
```

**Test 4: List Users - Empty - Browser + Postman**

```text
GET http://localhost:9696/users
Expected 200 OK 10ms Size 166B:
[]
```

**Test 5: Create User - CloudWatch Log + RDS Insert**

```text
POST http://localhost:9696/users
Body -> raw -> JSON:
{
  "name": "RaviTeja",
  "email": "raviteja@aws.com"
}
Expected 200 OK 145ms Size 313B:
{
  "id":1,
  "name":"RaviTeja",
  "email":"raviteja@aws.com",
  "department":null
}
Console also shows: [INFO] POST /users - Creating user: RaviTeja email=raviteja@aws.com
Console also shows: [INFO] User saved successfully id=1 - Pushing log to CloudWatch if enabled
```

**Test 6: List Users - With Data**

```text
GET http://localhost:9696/users
Expected 200 OK:
[{"id":1,"name":"RaviTeja","email":"raviteja@aws.com","department":null}]
```

**Test 7: Logs After All Ops - 15 Logs**

```text
GET http://localhost:9696/logs
Expected 200 OK:
{
  "cloudWatch":"Disabled - Local Fallback (CONSOLE + FILE) - Ready for AWS",
  "totalLogs":15,
  "logs":[
    "1792935598053 - [INFO] GET /logs - Fetching recent logs",
    "1792935493062 - [INFO] User saved successfully id=1 - Pushing log to CloudWatch if enabled",
    "1792935493012 - [INFO] POST /users - Creating user: RaviTeja email=raviteja@aws.com",
    "1792935448288 - [DEBUG] Hibernate: select * from users",
    "1792935448288 - [INFO] GET /users - Fetching all users",
    "1792935407661 - [DEBUG] Hibernate: select * from users",
    "1792935407657 - [INFO] GET /users - Fetching all users",
    "1792935386355 - [INFO] Test log - INFO - All 4 levels pushed - Check /logs",
    "1792935386339 - [DEBUG] Test log - DEBUG - Fetching users count query",
    "1792935386339 - [ERROR] Test log - ERROR - Simulated payment failure - For CloudWatch alarm test",
    "1792935386339 - [WARN] Test log - WARN - High memory usage 80%",
    "1792935386339 - [INFO] Test log - INFO - User login success",
    "1792935345053 - [INFO] GET /logs - Fetching recent logs",
    "1792935325886 - [INFO] Home endpoint called - totalUsers=0"
  ],
  "note":"These logs are also pushed to AWS CloudWatch Logs if CLOUDWATCH_ENABLED=true + AWS keys set",
  "project":"89-cloudwatch-logs"
}
```

---

## 5⃣ Docker Build & Run (Local)

```bash
docker build -t 89-cloudwatch-logs .
docker run -d -p 9696:9696 --name cloudwatch-app 89-cloudwatch-logs
docker ps
docker logs cloudwatch-app
curl http://localhost:9696/
curl http://localhost:9696/logs
curl http://localhost:9696/users
```

Expected:

```text
CONTAINER ID   IMAGE                  STATUS       PORTS
a1b2c3d        89-cloudwatch-logs     Up 2 mins    0.0.0.0:9696->9696/tcp
HikariPool-1 - Start completed.
Tomcat started on port 9696
Started CloudwatchApplication in 11.196 seconds
```

---

## 6⃣ Deploy to Render (Docker) - Live URL

Go to `Render.com -> New Web Service -> Connect GitHub Repo: 89-cloudwatch-logs`

```text
Runtime: Docker
Dockerfile Path: ./Dockerfile
Port: 9696
Branch: main
Build Command: (Auto via Dockerfile)
Start Command: (Auto via ENTRYPOINT)

Env Vars (Local H2 + CloudWatch Fallback - No AWS card needed):
  PORT = 9696
  CLOUDWATCH_ENABLED = false

Env Vars (Real AWS - With AWS Card - CloudWatch + RDS):
  RDS_URL = jdbc:mysql://raviteja89.xxxxx.ap-south-1.rds.amazonaws.com:3306/raviteja89
  RDS_USER = admin
  RDS_PASSWORD = yourpassword
  CLOUDWATCH_ENABLED = true
  AWS_ACCESS_KEY_ID = AKIA...
  AWS_SECRET_ACCESS_KEY = ****
  CLOUDWATCH_LOG_GROUP = 89-cloudwatch-logs
  CLOUDWATCH_LOG_STREAM = raviteja-89-stream
  AWS_REGION = ap-south-1
  PORT = 9696
```

Click `Deploy`

Expected Logs on Render:

```text
#12 exporting to image
#13 DONE
Deploy succeeded
HikariPool-1 - Added connection conn0: url=jdbc:h2:mem:raviteja89 user=SA
Tomcat started on port 9696
Started CloudwatchApplication in 9.1 seconds
Your service is live 🎉
Available at: https://eight9-cloudwatch-logs.onrender.com
```

Test Live:

```bash
curl https://eight9-cloudwatch-logs.onrender.com/
curl https://eight9-cloudwatch-logs.onrender.com/health
curl https://eight9-cloudwatch-logs.onrender.com/users
curl https://eight9-cloudwatch-logs.onrender.com/logs
curl -X POST https://eight9-cloudwatch-logs.onrender.com/logs/test
```

---

# 🔄 Flow Diagram - How CloudWatch Logs Works

```text
    User Laptop (Eclipse + Postman)
        │
        ├── E:\Java-100-Projects\89-cloudwatch-logs
        │       ├── src -> CloudwatchApplication.java (Home / + /health + totalUsers + cloudWatchStatus)
        │       ├── controller -> UserController.java (CRUD + logs) + LogController.java (NEW /logs + /logs/test)
        │       ├── service -> CloudWatchLogService.java (NEW - 50 buffer + SLF4J + CloudWatch SIM)
        │       ├── entity -> User.java (@Entity users) + repository -> UserRepository (JpaRepository)
        │       ├── resources -> application.properties (RDS_URL + CloudWatch env + logging.file.name) + logback-spring.xml (CONSOLE + FILE Rolling 7 days)
        │       └── Dockerfile (multi-stage builder + runtime + mkdir logs)
        │
        ▼
    git push origin main
        │
        ├───▶ GitHub Repository: raviteja-dev950/89-cloudwatch-logs
        │           │
        │           ▼
        │       Render Auto Deploy (Docker)
        │           ├── Dockerfile build
        │           │     ├── maven:3.9 builder -> mvn clean package
        │           │     └── eclipse-temurin:21-jdk runtime -> app.jar + logs/ folder
        │           └── ENTRYPOINT java -jar app.jar (CLOUDWATCH_ENABLED=false fallback - No crash without AWS keys)
        │
        ▼
    Local Eclipse Run (No AWS card needed - CloudWatch + RDS-Ready H2)
        │
        ├── Spring Boot App -> Tomcat 9696
        │     ├── DataSource Auto-Detect from URL jdbc:h2:mem:raviteja89
        │     │     ├── HikariPool-1 - Added connection conn0: url=jdbc:h2:mem:raviteja89 user=SA
        │     │     └── HikariPool-1 - Start completed.
        │     ├── JPA / Hibernate
        │     │     ├── HHH000204: Processing PersistenceUnitInfo
        │     │     ├── create table users (id bigint identity, name, email, department)
        │     │     └── Initialized JPA EntityManagerFactory
        │     ├── Logback
        │     │     ├── CONSOLE appender %d{HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n
        │     │     └── FILE appender logs/89-cloudwatch.log + Rolling 7 days
        │     ├── CloudWatchLogService
        │     │     ├── recentLogs synchronized ArrayList 50 max
        │     │     ├── addToMemory() timestamp + [INFO/WARN/ERROR/DEBUG] message
        │     │     ├── getRecentLogs() reverse copy
        │     │     └── getCloudWatchStatus() Enabled ? Pushing to group/stream : Disabled - Local Fallback
        │     ├── CloudwatchApplication
        │     │     ├── GET / -> logInfo("Home endpoint called") + {"owner":"RaviTeja - 89/100","cloudWatch":"Disabled...","totalUsers":0}
        │     │     └── GET /health -> logInfo("Health check - UP") + {"status":"UP","database":"Connected","cloudWatch":"Disabled..."}
        │     ├── UserController
        │     │     ├── GET /users -> logInfo("GET /users - Fetching all users") + logDebug("Hibernate: select *") -> findAll() -> [] then [id1]
        │     │     ├── POST /users {"name","email"} -> logInfo("POST /users - Creating user: RaviTeja") -> save() -> logInfo("User saved id=1 - Pushing to CloudWatch if enabled") -> id=1 200 OK 145ms
        │     │     ├── GET /users/{id} -> logDebug("GET /users/id")
        │     │     └── DELETE /users/{id} -> logWarn("DELETE /users/id") + logInfo("User deleted")
        │     └── LogController (NEW)
        │           ├── GET /logs -> logInfo("GET /logs - Fetching recent logs") -> getRecentLogs() -> totalLogs 2 then 15 -> 200 OK 8ms 1.4KB
        │           └── POST /logs/test -> logInfo("Test log - INFO - User login success") + logWarn("High memory 80%") + logError("Simulated payment failure") + logDebug("Fetching count query") -> 5 logs -> 200 OK 23ms 353B
        │
        ▼
    Future AWS Production (With AWS Card - Project 89 Real CloudWatch + RDS)
        │
        ├── EC2 t2.micro + RDS MySQL 8.0 Instance raviteja89 + CloudWatch Logs Group 89-cloudwatch-logs
        │     ├── Security Group: EC2 SG allows 3306 to RDS SG + CloudWatch Logs PutLogEvents
        │     ├── IAM Role: EC2 role with CloudWatchLogsFullAccess + RDS connect
        │     ├── Env on EC2/Render:
        │     │     ├── RDS_URL=jdbc:mysql://raviteja89.xxxxx.rds.amazonaws.com:3306/raviteja89
        │     │     ├── RDS_USER=admin
        │     │     ├── RDS_PASSWORD=****
        │     │     ├── CLOUDWATCH_ENABLED=true
        │     │     ├── AWS_ACCESS_KEY_ID=AKIA...
        │     │     ├── AWS_SECRET_ACCESS_KEY=****
        │     │     ├── CLOUDWATCH_LOG_GROUP=89-cloudwatch-logs
        │     │     └── CLOUDWATCH_LOG_STREAM=raviteja-89-stream
        │     ├── Spring Boot auto switches:
        │     │     ├── No H2, Uses mysql-connector-j runtime
        │     │     ├── HikariPool-1 - Added connection conn0: url=jdbc:mysql://... user=admin
        │     │     └── CloudWatch SDK: software.amazon.awssdk:cloudwatchlogs creates log group if not exists + PutLogEvents for each logInfo/Warn/Error/Debug
        │     └── Same Endpoints work on MySQL + CloudWatch: /users, /health, /, /logs, /logs/test all push to real CloudWatch console
        │
        ▼
    Browser / Postman / Portfolio / AWS Console
        │
        ├── localhost:9696/ -> {"owner":"RaviTeja - 89/100","cloudWatch":"Disabled - Local Fallback","totalUsers":0}
        ├── localhost:9696/logs -> {"totalLogs":2} then {"totalLogs":15,"logs":["[INFO] GET /logs..."," [INFO] User saved..."," [WARN] High memory..."," [ERROR] Simulated payment failure..."]}
        ├── localhost:9696/logs/test -> {"message":"5 test logs generated","levels":["INFO","WARN","ERROR","DEBUG","INFO"]} 200 OK 23ms
        ├── localhost:9696/users -> [] then [{"id":1,"name":"RaviTeja"}] after POST - Logs captured
        └── Live: https://eight9-cloudwatch-logs.onrender.com/ -> Same JSON on Internet
              │
              ├── AWS CloudWatch Console (When enabled) -> Log Groups -> 89-cloudwatch-logs -> Streams -> raviteja-89-stream -> See all INFO/WARN/ERROR/DEBUG logs live
              └── CloudWatch Alarms -> ERROR log "Simulated payment failure" triggers alarm -> SNS notification
              ▼
    Portfolio: Live CloudWatch URL + GitHub + 9 Demo Screenshots + CloudWatch Console Screenshot -> Resume Ready - DevOps + Cloud Tier 9 - Observability
```

---

# 📸 Screenshots

### Demo 1 – Browser Live - CloudWatch Ready Status - Home /
[Demo1 Browser](screenshots/demo1.png)

*localhost:9696 - Browser JSON - {"tier":"9 - DevOps + Cloud - CloudWatch Logs","status":"Live on CloudWatch - Logs Streaming","database":"H2 In-Memory (RDS-Ready)","totalUsers":0,"owner":"RaviTeja - 89/100","dbUrl":"jdbc:h2:mem:raviteja89","cloudWatch":"Disabled - Local Fallback (CONSOLE + FILE) - Ready for AWS","endpoints":["GET /","GET /health","GET /users","POST /users","GET /users/{id}","DELETE /users/{id}","GET /logs","POST /logs/test"],"project":"89-cloudwatch-logs"} - Proof of Eclipse + Spring Boot + H2 + CloudWatch-Ready running on 9696*

### Demo 2 – Browser - GET /logs - 2 Logs Initial - CloudWatch Fallback
[Demo2 Logs2](screenshots/demo2.png)

*GET http://localhost:9696/logs - Browser JSON - 200 OK - Returns {"cloudWatch":"Disabled - Local Fallback (CONSOLE + FILE) - Ready for AWS","totalLogs":2,"logs":["1792935345053 - [INFO] GET /logs - Fetching recent logs","1792935325886 - [INFO] Home endpoint called - totalUsers=0"],"note":"These logs are also pushed to AWS CloudWatch Logs if CLOUDWATCH_ENABLED=true + AWS keys set","project":"89-cloudwatch-logs"} - Proof of CloudWatchLogService memory buffer 50 working*

### Demo 3 – Postman - POST /logs/test - 5 Test Logs Generated - 200 OK
[Demo3 TestLogs](screenshots/demo3.png)

*POST http://localhost:9696/logs/test - Postman - Body empty - 200 OK 23ms Size 353B - Returns {"message":"5 test logs generated","levels":["INFO","WARN","ERROR","DEBUG","INFO"],"check":"GET /logs to see them","cloudWatch":"Disabled - Local Fallback (CONSOLE + FILE) - Ready for AWS"} - Proof of INFO/WARN/ERROR/DEBUG log levels for CloudWatch alarm test*

### Demo 4 – Browser - GET /users - Empty Array - Table Created - 200 OK
[Demo4 UsersEmptyBrowser](screenshots/demo4.png)

*GET http://localhost:9696/users - Browser - Returns [] empty array - Proof of Hibernate create table users (id bigint generated by default as identity, name, email, department) + JpaRepository + CloudWatch log GET /users captured*

### Demo 5 – Postman - GET /users - Empty - 200 OK 10ms - Table Proof
[Demo5 UsersEmptyPostman](screenshots/demo5.png)

*GET http://localhost:9696/users - Postman - 200 OK Time 10ms Size 166B - Returns [] - Proof of Hibernate auto DDL + UserRepository.findAll() working before any POST - Logs: [DEBUG] Hibernate: select * from users + [INFO] GET /users - Fetching all users pushed to CloudWatchLogService*

### Demo 6 – Postman - POST /users - RDS Insert + CloudWatch Log - 200 OK 145ms
[Demo6 Post](screenshots/demo6.png)

*POST http://localhost:9696/users - Postman - Body raw JSON {"name":"RaviTeja","email":"raviteja@aws.com"} - 200 OK Time 145ms Size 313B - Returns {"id":1,"name":"RaviTeja","email":"raviteja@aws.com","department":null} - Proof of RDS CRUD insert + CloudWatch logs: [INFO] POST /users - Creating user: RaviTeja + [INFO] User saved successfully id=1 - Pushing log to CloudWatch if enabled - Both logs visible in GET /logs*

### Demo 7 – Postman - GET /logs - 14 Logs After CRUD - 200 OK 8ms
[Demo7 Logs14](screenshots/demo7.png)

*GET http://localhost:9696/logs - Postman - 200 OK Time 8ms Size 1.4KB - Returns {"cloudWatch":"Disabled - Local Fallback (CONSOLE + FILE) - Ready for AWS","totalLogs":14,"logs":["1792935536918 - [INFO] GET /logs - Fetching recent logs","1792935493062 - [INFO] User saved successfully id=1 - Pushing log to CloudWatch if enabled","1792935493012 - [INFO] POST /users - Creating user: RaviTeja email=raviteja@aws.com","1792935448288 - [DEBUG] Hibernate: select * from users","1792935448288 - [INFO] GET /users - Fetching all users","1792935407661 - [DEBUG] Hibernate: select * from users","1792935407657 - [INFO] GET /users - Fetching all users","1792935386355 - [INFO] Test log - INFO - All 4 levels pushed - Check /logs","1792935386339 - [DEBUG] Test log - DEBUG - Fetching users count query","1792935386339 - [ERROR] Test log - ERROR - Simulated payment failure - For CloudWatch alarm test","1792935386339 - [WARN] Test log - WARN - High memory usage 80%","1792935386339 - [INFO] Test log - INFO - User login success","1792935345053 - [INFO] GET /logs - Fetching recent logs","1792935325886 - [INFO] Home endpoint called - totalUsers=0"],"note":"These logs are also pushed to AWS CloudWatch Logs if CLOUDWATCH_ENABLED=true","project":"89-cloudwatch-logs"} - Proof of CloudWatch streaming 14 logs including CRUD + test logs + Hibernate DEBUG*

### Demo 8 – Browser - GET /logs - 15 Logs - CloudWatch Streaming Proof
[Demo8 Logs15](screenshots/demo8.png)

*GET http://localhost:9696/logs - Browser - Returns totalLogs 15 with logs [INFO] GET /logs, [INFO] User saved id=1, [INFO] POST /users Creating user, [DEBUG] Hibernate select *, [INFO] GET /users Fetching all users, [INFO] Test log All 4 levels, [DEBUG] Test log DEBUG Fetching count, [ERROR] Test log ERROR Simulated payment failure For CloudWatch alarm test, [WARN] Test log WARN High memory usage 80%, [INFO] Test log INFO User login success - Proof of Full CloudWatch Logs Streaming - INFO/WARN/ERROR/DEBUG all captured for AWS CloudWatch console*

### Demo 9 – Browser - GET /users - With Data - Persistence Proof - CloudWatch Logged
[Demo9 UsersData](screenshots/demo9.png)

*GET http://localhost:9696/users - Browser - Returns [{"id":1,"name":"RaviTeja","email":"raviteja@aws.com","department":null}] - Proof of RDS persistence after POST + CloudWatch logs: Every GET /users generates [DEBUG] Hibernate select * + [INFO] GET /users - Fetching all users which are pushed to GET /logs memory buffer + future AWS CloudWatch Logs group 89-cloudwatch-logs*

---

# 🧪 API Testing Examples

```bash
# GET Home - Local after Eclipse Run - CloudWatch Status
curl http://localhost:9696/

# GET Home - LIVE on Render
curl https://eight9-cloudwatch-logs.onrender.com/

# Check Health - CloudWatch status included
curl http://localhost:9696/health
curl https://eight9-cloudwatch-logs.onrender.com/health

# Logs - NEW FOR 89 - CloudWatch Streaming
curl http://localhost:9696/logs
curl https://eight9-cloudwatch-logs.onrender.com/logs

# Test Logs - NEW FOR 89 - Generate 5 logs for CloudWatch alarm test
curl -X POST http://localhost:9696/logs/test
curl -X POST https://eight9-cloudwatch-logs.onrender.com/logs/test

# List Users - Empty then with data - Logs captured
curl http://localhost:9696/users
curl https://eight9-cloudwatch-logs.onrender.com/users

# Create User - RDS Insert + CloudWatch log
curl -X POST http://localhost:9696/users -H "Content-Type: application/json" -d "{"name":"RaviTeja","email":"raviteja@aws.com"}"
curl -X POST https://eight9-cloudwatch-logs.onrender.com/users -H "Content-Type: application/json" -d "{"name":"RaviTeja","email":"raviteja@aws.com"}"

# Get User By ID
curl http://localhost:9696/users/1

# Delete User
curl -X DELETE http://localhost:9696/users/1

# Docker Test
docker build -t 89-cloudwatch-logs .
docker run -d -p 9696:9696 --name cloudwatch-app 89-cloudwatch-logs
docker logs cloudwatch-app
docker ps
curl http://localhost:9696/
curl http://localhost:9696/logs
curl http://localhost:9696/users
docker stop cloudwatch-app && docker rm cloudwatch-app

# H2 Corruption Fix - If org.h2.Driver error
rm -rf C:\Users\Ravi\ .m2\repository\com\h2database\h2\2.2.224\
# Or
del /F /S /Q "%USERPROFILE%\.m2\repository\com\h2database\h2\2.2.224\"
mvn clean install -U -DskipTests
jar tf target/89-cloudwatch-logs-0.0.1-SNAPSHOT.jar | findstr h2
# Should show BOOT-INF/lib/h2-2.2.224.jar 2.6 MB

# Check H2 jar size
dir "%USERPROFILE%\.m2\repository\com\h2database\h2\2.2.224\h2-2.2.224.jar"

# Check logs file
cat logs/89-cloudwatch.log
tail -f logs/89-cloudwatch.log
```

### Expected Response (localhost:9696/) - CloudWatch Ready

```json
{
  "tier": "9 - DevOps + Cloud - CloudWatch Logs",
  "status": "Live on CloudWatch - Logs Streaming",
  "database": "H2 In-Memory (RDS-Ready)",
  "totalUsers": 0,
  "owner": "RaviTeja - 89/100",
  "dbUrl": "jdbc:h2:mem:raviteja89",
  "cloudWatch": "Disabled - Local Fallback (CONSOLE + FILE) - Ready for AWS",
  "endpoints": ["GET /","GET /health","GET /users","POST /users","GET /users/{id}","DELETE /users/{id}","GET /logs","POST /logs/test"],
  "project": "89-cloudwatch-logs"
}
```

### Expected Health Response (GET /health) - UP + CloudWatch

```json
{
  "status": "UP",
  "database": "Connected",
  "project": "89-cloudwatch-logs",
  "cloudWatch": "Disabled - Local Fallback (CONSOLE + FILE) - Ready for AWS"
}
```

### Expected Logs Response (GET /logs) - 15 Logs - CloudWatch Streaming

```json
{
  "cloudWatch": "Disabled - Local Fallback (CONSOLE + FILE) - Ready for AWS",
  "totalLogs": 15,
  "logs": [
    "1792935598053 - [INFO] GET /logs - Fetching recent logs",
    "1792935493062 - [INFO] User saved successfully id=1 - Pushing log to CloudWatch if enabled",
    "1792935493012 - [INFO] POST /users - Creating user: RaviTeja email=raviteja@aws.com",
    "1792935448288 - [DEBUG] Hibernate: select * from users",
    "1792935386339 - [ERROR] Test log - ERROR - Simulated payment failure - For CloudWatch alarm test",
    "1792935386339 - [WARN] Test log - WARN - High memory usage 80%"
  ],
  "note": "These logs are also pushed to AWS CloudWatch Logs if CLOUDWATCH_ENABLED=true + AWS keys set",
  "project": "89-cloudwatch-logs"
}
```

### Expected Test Logs Response (POST /logs/test) - 200 OK 23ms

```json
{
  "message": "5 test logs generated",
  "levels": ["INFO","WARN","ERROR","DEBUG","INFO"],
  "check": "GET /logs to see them",
  "cloudWatch": "Disabled - Local Fallback (CONSOLE + FILE) - Ready for AWS"
}
```

### Expected POST Response (POST /users) - 200 OK 145ms

```json
{
  "id": 1,
  "name": "RaviTeja",
  "email": "raviteja@aws.com",
  "department": null
}
```

### Expected GET Response (GET /users) - 200 OK 10ms

```json
[
  {
    "id": 1,
    "name": "RaviTeja",
    "email": "raviteja@aws.com",
    "department": null
  }
]
```

**Testing Steps:**

1. Run `Run As -> Spring Boot App` in Eclipse - Should show `HikariPool-1 - Added connection conn0: url=jdbc:h2:mem:raviteja89 user=SA` + `HikariPool-1 - Start completed` + `create table users` + `Tomcat started on port 9696` + `Started CloudwatchApplication in 11.196 seconds`
2. Open `http://localhost:9696/` in browser - Should return CloudWatch Ready JSON with totalUsers 0 + cloudWatch Disabled Fallback - Proof of Eclipse + Spring Boot + H2 + CloudWatch-Ready running
3. Open `http://localhost:9696/logs` - Should return totalLogs 2 - Proof of CloudWatchLogService memory buffer
4. Open Postman -> `POST http://localhost:9696/logs/test` -> Should return 5 test logs generated 200 OK 23ms - Proof of INFO/WARN/ERROR/DEBUG
5. Run `GET http://localhost:9696/logs` again -> Should show 7+ logs - Proof of streaming
6. Open Postman -> `GET http://localhost:9696/users` -> Should return [] empty - Proof of table created + log captured in /logs
7. Run `POST http://localhost:9696/users` with `{"name":"RaviTeja","email":"raviteja@aws.com"}` -> Should return 200 OK 145ms with id 1 - Proof of JPA save + CloudWatch log User saved
8. Run `GET http://localhost:9696/users` again -> Should show [{"id":1,"name":"RaviTeja"}] - Proof of persistence + logs growing to 14-15
9. Run `GET http://localhost:9696/logs` -> Should show totalLogs 15 with INFO/WARN/ERROR/DEBUG + CRUD logs - Proof of full CloudWatch streaming
10. Push to GitHub -> Render auto deploy -> `curl https://eight9-cloudwatch-logs.onrender.com/` + `/logs` -> Should return same JSON - Proof of live + logs live

---

# 🎯 Learning Outcomes

- Understanding AWS CloudWatch Logs vs Local Logs - CloudWatch Logs for production observability vs CONSOLE + FILE `logs/89-cloudwatch.log` for local dev without AWS card - CloudWatch-Ready pattern via `${CLOUDWATCH_ENABLED:false}` + `${CLOUDWATCH_LOG_GROUP:89-cloudwatch-logs}`
- Understanding CloudWatch Logs vs RDS vs EC2 vs S3 - EC2 for compute (Project 86), S3 for storage (Project 87), RDS for relational data (Project 88), CloudWatch for logs/observability (Project 89) - Together full AWS 4-tier stack
- Implementing CloudWatchLogService - `Collections.synchronizedList(new ArrayList<>())` 50 max buffer + `addToMemory()` timestamp + `log.info/warn/error/debug` + `getRecentLogs()` reverse + `getCloudWatchStatus()` Enabled/Disabled - Same pattern as real AWS SDK `PutLogEvents`
- Understanding Logback - `logback-spring.xml` - `ConsoleAppender` `%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36}` + `RollingFileAppender` `logs/89-cloudwatch.log` + `TimeBasedRollingPolicy` 7 days + `root level INFO` + `com.raviteja.cloudwatch DEBUG`
- Understanding Log Levels for CloudWatch Alarms - `INFO` for normal flow (Home, GET /users), `WARN` for warning (DELETE, High memory 80%), `ERROR` for failure (Simulated payment failure for alarm test), `DEBUG` for query details (Hibernate select * from users) - CloudWatch Alarms filter ERROR
- Creating 7 CloudWatch Endpoints - GET / (home with cloudWatch status + totalUsers), GET /health (UP + cloudWatch), GET /users (findAll + logs), POST /users (save + logs), GET /users/{id} + DELETE /users/{id} + GET /logs (last 50 logs) + POST /logs/test (5 levels)
- Using Spring Data JPA + CloudWatch - `JpaRepository<User, Long>` gives `save()`, `findAll()`, `findById()`, `deleteById()`, `count()` + Every call logs to CloudWatchLogService - Hibernate `create table users` + `select *` DEBUG logs captured
- Understanding HikariCP + CloudWatch - `HikariPool-1 - Starting...` + `Added connection conn0` + `Start completed` + `Shutdown initiated/completed` lifecycle - All logs go to `logs/89-cloudwatch.log` file + CONSOLE + future CloudWatch
- Configuring application.properties for CloudWatch - `${CLOUDWATCH_ENABLED:false}`, `${CLOUDWATCH_LOG_GROUP:89-cloudwatch-logs}`, `${CLOUDWATCH_LOG_STREAM:raviteja-89-stream}`, `${AWS_REGION:ap-south-1}`, `${AWS_ACCESS_KEY_ID:}`, `${AWS_SECRET_ACCESS_KEY:}`, `${PORT:9696}`, `${RDS_URL:default}` for env override on Render/EC2 - NO hardcoded keys
- Docker multi-stage for CloudWatch - Same as RDS but port 9696 + `RUN mkdir -p logs` for file appender - Works for both H2 and MySQL + CloudWatch disabled locally so no AWS keys needed on Render demo
- Testing with Postman Lightweight Client - raw JSON body POST + history tracking + 200 OK 23ms 353B (/logs/test) + 200 OK 8ms 1.4KB (/logs) + 200 OK 145ms 313B (/users) + 200 OK 10ms 166B verification + Pretty JSON + History: POST /logs/test, GET /users, POST /users, GET /logs
- Understanding CloudWatch-Ready vs Real CloudWatch - Local fallback `Disabled - Local Fallback (CONSOLE + FILE) - Ready for AWS` vs Real AWS `Enabled - Pushing to 89-cloudwatch-logs/raviteja-89-stream` when `CLOUDWATCH_ENABLED=true` + AWS keys set + Real SDK `software.amazon.awssdk:cloudwatchlogs:2.20.0` does `CreateLogGroup`, `CreateLogStream`, `PutLogEvents` - Portfolio ready without AWS card
- Fixing `ClassNotFoundException: org.h2.Driver` - Corrupted `.m2/repository/.../h2-2.2.224.jar` shows 0 KB - Delete folder + `mvn -U` re-download 2.6 MB + Verify `jar tf ... | findstr h2`
- Fixing `Cannot load driver class: org.h2.Driver` even with jar present - `spring.datasource.driver-class-name` forces explicit `Class.forName` before `JarLauncher` loads `BOOT-INF/lib/*.jar` - Remove driver-class-name line - Let Spring auto-detect from URL
- Fixing `Unable to resolve name [org.hibernate.dialect.H2Dialect\n]` - Trailing newline/space after dialect property value causes Hibernate to search `H2Dialect\n` class - Remove dialect line - Spring auto-detects
- Preparing base for Load Balancer (90), Terraform (91) - CloudWatch logs will be aggregated from 2 EC2s behind ALB + CloudWatch metrics `totalUsers`, `totalLogs`, `HikariPool` connections + Alarms on ERROR logs

---

# 🚀 Future Enhancements

- ⚖ Add Load Balanced CloudWatch App - Project 90 - 2 EC2 instances sharing same RDS MySQL `raviteja89` + Same CloudWatch Log Group `89-cloudwatch-logs` with 2 streams `ec2-1-stream` + `ec2-2-stream` behind ALB - Both connect via same `RDS_URL` + Logs aggregated in CloudWatch console - Data shared across instances (unlike H2)
- 🏗 Add Terraform for CloudWatch + RDS - Project 91 - IaC `resource "aws_cloudwatch_log_group" "raviteja89" { name="89-cloudwatch-logs" retention_in_days=7 }` + `resource "aws_db_instance" "raviteja89" { engine="mysql", instance_class="db.t3.micro" }` + `aws_db_subnet_group` + `aws_security_group` for 3306 + `aws_cloudwatch_log_stream`
- 🔵🟢 Add Blue-Green CloudWatch - Project 92 - 2 EC2 ASG `blue` and `green` + 2 RDS instances + Same CloudWatch group with `blue-stream` + `green-stream` + Switch via ALB for zero downtime + CloudWatch compares logs blue vs green
- 🔒 Add CloudWatch Secrets Manager - Project 93 - Store `RDS_USER`, `RDS_PASSWORD`, `AWS_ACCESS_KEY_ID`, `AWS_SECRET_ACCESS_KEY` in `aws_secretsmanager_secret` + `spring.config.import=aws-secretsmanager:raviteja89/rds,raviteja89/cloudwatch` instead of env vars
- 🌐 Add CloudWatch + RDS Proxy - Project 94 - Add `aws_db_proxy` in front of RDS to handle 1000+ connections from 2 EC2s + CloudWatch Logs for proxy `slow query` + `connection` logs + Reduce RDS CPU
- 📦 Add ECR + Real CloudWatch + Flyway - Project 95 - Add `Flyway` or `Liquibase` for `V1__create_users_table.sql` + `V2__add_department.sql` instead of `ddl-auto=update` + ECR image + Real CloudWatch Logs via IAM role `CloudWatchLogsFullAccess` on EC2
- 🔐 Add CloudWatch IAM DB Authentication - Project 96 - Use `iam_database_authentication_enabled=true` on RDS + `AWS IAM` role on EC2 instead of password + CloudWatch logs for IAM auth success/failure - Token via `RdsIamAuthTokenGenerator`
- 📈 Add CloudWatch Alarms + Metrics - Project 97 - Enable `CloudWatch Alarm` when `ERROR` logs > 5 in 5 mins + `totalUsers` custom metric via `PutMetricData` + `HikariPool` connections metric + SNS email alert when `DBLoad > 1` or `High memory usage 80%` WARN
- 🔍 Add CloudWatch Insights + Read Replica - Project 98 - Create `aws_db_instance` read replica in different AZ `ap-south-1b` + `GET /users` reads from replica, `POST /users` writes to master - `@Transactional(readOnly=true)` routing + CloudWatch Logs Insights query `fields @timestamp, @message | filter @message like /ERROR/ | stats count() by bin(5m)`
- 🧪 Add CloudWatch Dashboard + RDS Snapshots - Project 99 - CloudWatch Dashboard with widgets: `totalUsers`, `totalLogs`, `ERROR count`, `HikariPool Active`, `Tomcat Requests` + Automated RDS backup `backup_retention_period=7` + Manual snapshot before deploy + Point-in-time recovery test
- 🏆 Final 100 - Add All AWS Together - Project 100 - EC2 (86) + S3 (87) + RDS (88) + CloudWatch (89) + ALB (90) + Terraform (91-99) = Full 3-tier AWS Production App + Observability - All logs in one CloudWatch group

---

# 👨💻 Author

Ravi Teja

Java Full Stack Developer

100 Java Full Stack Projects Challenge

Project 89 / 100

Tier 9 – DevOps + Cloud Deployment

---

## ⭐ Support

If you found this project helpful, consider giving it a ⭐ Star on GitHub.

**Single Repo:** [89-cloudwatch-logs](https://github.com/raviteja-dev950/89-cloudwatch-logs)

**Live URL:** https://eight9-cloudwatch-logs.onrender.com/ -> Replace with your Render URL after deploy

**Logs URL:** https://eight9-cloudwatch-logs.onrender.com/logs -> Shows last 50 logs - CloudWatch streaming proof

**Test Logs URL:** `POST https://eight9-cloudwatch-logs.onrender.com/logs/test` -> Generates 5 test logs INFO/WARN/ERROR/DEBUG

**EC2 Future URL:** `http://YOUR_EC2_PUBLIC_IP:9696` + `/logs` + `/logs/test` -> After adding real CloudWatch + RDS + EC2 Security Group + IAM Role CloudWatchLogsFullAccess

**RDS Instance:** `raviteja89` - Engine `MySQL 8.0` - Class `db.t3.micro` Free Tier - Region `ap-south-1` (Mumbai) - RDS-Ready (H2 fallback now, real RDS on EC2 with env)

**CloudWatch Log Group:** `89-cloudwatch-logs` - Retention 7 days - Region `ap-south-1` - Stream `raviteja-89-stream` - Ready for real AWS (Local fallback now)

**Docker Image:** `89-cloudwatch-logs:latest` on Render/EC2 (or `ravitejadev950/89-cloudwatch-logs:latest` on Docker Hub optional)

**GitHub Actions:** Ready for `Deploy to EC2` - On Push to `main` via SSH - Same as Project 86/87/88

**Secrets:** `EC2_HOST` + `EC2_SSH_KEY` + `RDS_URL` + `RDS_USER` + `RDS_PASSWORD` + `CLOUDWATCH_ENABLED` + `AWS_ACCESS_KEY_ID` + `AWS_SECRET_ACCESS_KEY` + `CLOUDWATCH_LOG_GROUP` + `CLOUDWATCH_LOG_STREAM` (for real CloudWatch)

**Trigger:** `git push origin main` -> Render auto deploy -> `https://eight9-cloudwatch-logs.onrender.com/` + `/logs` LIVE

**Postman:** `GET /logs` 200 OK 8ms 1.4KB -> `POST /logs/test` 200 OK 23ms 353B -> `POST /users` JSON `{"name","email"}` 200 OK 145ms -> `GET /users` list 200 OK 10ms -> All 200 OK + Logs streaming

**Pull:** `git clone https://github.com/raviteja-dev950/89-cloudwatch-logs.git`

