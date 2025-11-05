package com.postalservice.letter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;
@Service
public class LetterProcessor {
  private static final Set<String> ALLOWED = Set.of("AT","DE","CH");
  private final LetterRepository letters;
  public LetterProcessor(LetterRepository letters){ this.letters = letters; }
  @Transactional
  public void process(String id){
    letters.findById(id).ifPresent(l -> {
      boolean ok = ALLOWED.contains(l.getCountry());
      l.setStatus(ok ? Status.SEND : Status.REJECTED);
    });
  }
}
