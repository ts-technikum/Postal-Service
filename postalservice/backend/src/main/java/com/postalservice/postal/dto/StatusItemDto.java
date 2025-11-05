package com.postalservice.postal.dto;
import com.postalservice.postal.domain.*;
public record StatusItemDto(String id, String type, String name, String extra, String status) {
  public static StatusItemDto fromLetter(Letter l){
    return new StatusItemDto(l.getId(), "LETTER", l.getName(), l.getCountry(), l.getStatus().name());
  }
  public static StatusItemDto fromParcel(Parcel p){
    return new StatusItemDto(p.getId(), "PARCEL", p.getName(), String.valueOf(p.getWeightKg()), p.getStatus().name());
  }
}
