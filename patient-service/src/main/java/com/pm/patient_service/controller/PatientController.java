package com.pm.patient_service.controller;

import com.pm.patient_service.DTO.PatientResponseDTO;
import com.pm.patient_service.DTO.PatientRequestDTO;
import com.pm.patient_service.services.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")

public class PatientController  {

    @Autowired
    PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients(){

       List<PatientResponseDTO> patientDTOList = patientService.getPatients();
        return ResponseEntity.ok().body(patientDTOList);
    }

    @PostMapping("/add")
    public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientRequestDTO patientDTO){  //@Valid to go and check all validation from entity

        PatientResponseDTO patientDTO1 = patientService.createPatient(patientDTO);

        return ResponseEntity.ok().body(patientDTO1);

    }

}
