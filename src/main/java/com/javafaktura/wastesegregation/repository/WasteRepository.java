package com.javafaktura.wastesegregation.repository;

import com.javafaktura.wastesegregation.model.Waste;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WasteRepository extends JpaRepository<Waste, Long> {

}
