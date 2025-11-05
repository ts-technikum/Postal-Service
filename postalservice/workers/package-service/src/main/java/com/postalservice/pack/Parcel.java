package com.postalservice.pack;
import jakarta.persistence.*;
@Entity
public class Parcel {
  @Id private String id;
  private String name;
  private double weightKg;
  @Enumerated(EnumType.STRING) private Status status;
  public String getId(){return id;} public void setId(String id){this.id=id;}
  public String getName(){return name;} public void setName(String name){this.name=name;}
  public double getWeightKg(){return weightKg;} public void setWeightKg(double w){this.weightKg=w;}
  public Status getStatus(){return status;} public void setStatus(Status status){this.status=status;}
}
