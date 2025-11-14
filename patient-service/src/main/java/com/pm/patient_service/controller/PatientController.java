package com.pm.patient_service.controller;

import com.pm.patient_service.DTO.PatientResponseDTO;
import com.pm.patient_service.DTO.PatientRequestDTO;
import com.pm.patient_service.DTO.validators.CreatePatientValidationGroup;
import com.pm.patient_service.services.PatientService;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<PatientResponseDTO> createPatient(@Validated({Default.class , CreatePatientValidationGroup.class}) @RequestBody PatientRequestDTO patientDTO){  //@Valid to go and check all validation from entity

        PatientResponseDTO patientDTO1 = patientService.createPatient(patientDTO);

        return ResponseEntity.ok().body(patientDTO1);

    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id , @Validated({Default.class}) @RequestBody PatientRequestDTO patientDTO){  //Validated ---> Cotnains all Validation messages

        PatientResponseDTO patientDTO1 = patientService.updatePatient(id,patientDTO);
        return ResponseEntity.ok().body(patientDTO1);
    }

}
