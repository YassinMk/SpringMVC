package org.example.divintosrpingmvc.web;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.divintosrpingmvc.entities.Patient;
import org.example.divintosrpingmvc.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PatientController {
    @Autowired
    private  PatientRepository patientRepository;
    @GetMapping(path = "/index")
    public String Patients(Model model){
        List<Patient> patients = patientRepository.findAll();
        model.addAttribute("listPatients", patients);
        return "patients";
    }

}
