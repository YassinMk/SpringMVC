package org.example.divintosrpingmvc.repositories;

import org.example.divintosrpingmvc.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findByNomContaining(String name, Pageable pageable);
}
