package com.postalservice.postal.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import com.postalservice.postal.domain.Parcel;
public interface ParcelRepository extends JpaRepository<Parcel, String> {}
