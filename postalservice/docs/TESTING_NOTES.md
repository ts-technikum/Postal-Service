# Testing Notes
- Controller tests via `@WebMvcTest` mock out collaborators and assert contract.
- Worker processors are unit-tested with mocked repositories.
- Integration tests can be added with Testcontainers (omitted to keep scope minimal).
