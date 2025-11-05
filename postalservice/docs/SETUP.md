# Setup & Run

## Prereqs
- JDK 17
- Maven 3.9+
- Docker + Docker Compose

## Steps
1. Start infrastructure:
   ```bash
   docker compose up -d
   ```
2. Start backend API:
   ```bash
   cd backend
   mvn spring-boot:run
   ```
3. Start workers (two terminals):
   ```bash
   cd workers/letter-service && mvn spring-boot:run
   cd workers/package-service && mvn spring-boot:run
   ```
4. Start JavaFX client:
   ```bash
   cd frontend
   mvn javafx:run
   ```

RabbitMQ UI: http://localhost:15672 (guest/guest)
