package com.pm.patient_service.services;

import com.pm.patient_service.DTO.PatientResponseDTO;
import com.pm.patient_service.DTO.PatientRequestDTO;
import com.pm.patient_service.exception.EmailAlreadyExistException;
import com.pm.patient_service.mapper.PatientMapper;
import com.pm.patient_service.model.Patient;
import com.pm.patient_service.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    PatientRepo patientRepo;

    //List of Patient
    public List<PatientResponseDTO> getPatients(){

        List<Patient> patients = patientRepo.findAll();

        List<PatientResponseDTO> patientDTOs = patients.stream()
                .map(PatientMapper::toPatientDTO).toList();   //

        return patientDTOs;
    }

    //Add Patient
    public PatientResponseDTO createPatient(PatientRequestDTO patientDTO)
    {
        //Check if The Email is already Exists
        if(patientRepo.existsByEmail(patientDTO.getEmail())){
            throw new EmailAlreadyExistException("Patient with this email already exist  " + patientDTO.getEmail());
        }
     Patient newPatient = patientRepo.save(PatientMapper.toPatient(patientDTO));

     return PatientMapper.toPatientDTO(newPatient);


    }
}
