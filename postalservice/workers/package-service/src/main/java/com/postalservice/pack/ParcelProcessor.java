package com.postalservice.pack;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class ParcelProcessor {
  private final ParcelRepository parcels;
  public ParcelProcessor(ParcelRepository parcels){ this.parcels = parcels; }
  @Transactional
  public void process(String id){
    parcels.findById(id).ifPresent(p -> {
      boolean ok = p.getWeightKg() < 25.0;
      p.setStatus(ok ? Status.SEND : Status.REJECTED);
    });
  }
}
