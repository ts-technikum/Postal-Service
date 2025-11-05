# Lessons Learned
- Keep validation in workers so the queue path is authoritative.
- Use IDs in messages; workers fetch latest state.
- JavaFX is simplest when avoiding FXML for tiny UIs.
- Spring Boot autoconfig keeps RabbitMQ/DB boilerplate minimal.
- A few focused tests are enough to validate core logic.
