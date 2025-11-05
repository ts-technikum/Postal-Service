package com.postalservice.letter;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class LetterProcessorTest {
  @Test void allowedCountries_setStatusSend(){
    var repo = mock(LetterRepository.class);
    var l = new Letter(); l.setId("L1"); l.setCountry("AT"); l.setStatus(Status.WAITING);
    when(repo.findById("L1")).thenReturn(Optional.of(l));
    new LetterProcessor(repo).process("L1");
    assertEquals(Status.SEND, l.getStatus());
  }
  @Test void disallowedCountry_setStatusRejected(){
    var repo = mock(LetterRepository.class);
    var l = new Letter(); l.setId("L2"); l.setCountry("US"); l.setStatus(Status.WAITING);
    when(repo.findById("L2")).thenReturn(Optional.of(l));
    new LetterProcessor(repo).process("L2");
    assertEquals(Status.REJECTED, l.getStatus());
  }
}
