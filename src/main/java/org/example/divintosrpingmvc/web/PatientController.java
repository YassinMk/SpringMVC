package org.example.divintosrpingmvc.web;

import jakarta.validation.Valid;
import org.example.divintosrpingmvc.entities.Patient;
import org.example.divintosrpingmvc.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PatientController {
    @Autowired
    private  PatientRepository patientRepository;
    @GetMapping(path = "/index")
    public String Patients(Model model,@RequestParam(name = "page", defaultValue = "0")
    int page, @RequestParam(name = "size" , defaultValue = "5") int size ,
     @RequestParam(name = "keyword", defaultValue = "") String keyword){

        Page<Patient> pagePatient = patientRepository.findByNomContaining(keyword , PageRequest.of(page, size)) ;
        model.addAttribute("listPatients", pagePatient.getContent());
        model.addAttribute("pages", new int[pagePatient.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "patients";
    }
    @GetMapping(path = "/delete")
    public String delete(Long id, String keyword , int page){
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/")
    public String index(){
        return "redirect:/index";
    }

    @GetMapping(path = "/patients")
    @ResponseBody
    public List<Patient> patients(){
        return patientRepository.findAll();
    }
    @GetMapping("/formPatients")
    public String formPatients(Model model) {
        model.addAttribute("patient", new Patient());
        return "formPatients";
    }
    @PostMapping("/save")
    public String save(Model model, @Valid Patient patient, BindingResult bindingResult,@RequestParam(name = "page", defaultValue = "0") int page,
                       @RequestParam(name = "keyword", defaultValue = "") String keyword){
        if(bindingResult.hasErrors()) return "formPatients";
        patientRepository.save(patient);
        return "redirect:/formPatients"+page+"?keyword="+keyword;
    }
    @GetMapping(path = "/editPatient")
    public String edit(Model model, @RequestParam("id") Long id){
        Patient p = patientRepository.findById(id).orElse(null);
        if(p == null) throw new RuntimeException("Patient introuvable");
        model.addAttribute("patient", p);
        return "editPatient";
    }


}
