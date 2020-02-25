package com.javafaktura.wastesegregation.repository;

import com.javafaktura.wastesegregation.model.Waste;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WasteRepository extends JpaRepository<Waste, Long> {

}
