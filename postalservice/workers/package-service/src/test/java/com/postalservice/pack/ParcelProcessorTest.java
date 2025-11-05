package com.postalservice.pack;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class ParcelProcessorTest {
  @Test void under25kg_send(){
    var repo = mock(ParcelRepository.class);
    var p = new Parcel(); p.setId("P1"); p.setWeightKg(10.0); p.setStatus(Status.WAITING);
    when(repo.findById("P1")).thenReturn(Optional.of(p));
    new ParcelProcessor(repo).process("P1");
    assertEquals(Status.SEND, p.getStatus());
  }
  @Test void atLeast25kg_reject(){
    var repo = mock(ParcelRepository.class);
    var p = new Parcel(); p.setId("P2"); p.setWeightKg(25.0); p.setStatus(Status.WAITING);
    when(repo.findById("P2")).thenReturn(Optional.of(p));
    new ParcelProcessor(repo).process("P2");
    assertEquals(Status.REJECTED, p.getStatus());
  }
}
