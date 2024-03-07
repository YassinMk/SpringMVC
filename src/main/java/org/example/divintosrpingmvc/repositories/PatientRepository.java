package org.example.divintosrpingmvc.repositories;

import org.example.divintosrpingmvc.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
