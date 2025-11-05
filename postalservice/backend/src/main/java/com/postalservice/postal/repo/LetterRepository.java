package com.postalservice.postal.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import com.postalservice.postal.domain.Letter;
public interface LetterRepository extends JpaRepository<Letter, String> {}
