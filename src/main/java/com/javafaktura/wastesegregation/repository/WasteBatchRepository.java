package com.javafaktura.wastesegregation.repository;

import com.javafaktura.wastesegregation.model.WasteBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WasteBatchRepository extends JpaRepository<WasteBatch, Long> {

}
