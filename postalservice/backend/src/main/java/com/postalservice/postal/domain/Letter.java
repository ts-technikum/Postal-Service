package com.postalservice.postal.domain;
import jakarta.persistence.*;
@Entity
public class Letter {
  @Id private String id;
  private String name;
  private String country;
  @Enumerated(EnumType.STRING)
  private Status status = Status.WAITING;
  public String getId(){return id;} public void setId(String id){this.id=id;}
  public String getName(){return name;} public void setName(String name){this.name=name;}
  public String getCountry(){return country;} public void setCountry(String country){this.country=country;}
  public Status getStatus(){return status;} public void setStatus(Status status){this.status=status;}
}
