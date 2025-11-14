package com.pm.patient_service.services;

import com.pm.patient_service.DTO.PatientResponseDTO;
import com.pm.patient_service.DTO.PatientRequestDTO;
import com.pm.patient_service.exception.EmailAlreadyExistException;
import com.pm.patient_service.exception.PatientNotFoundException;
import com.pm.patient_service.mapper.PatientMapper;
import com.pm.patient_service.model.Patient;
import com.pm.patient_service.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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

    //Update Patient
    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientDTO){

        Patient patient = patientRepo.findById(id).
        orElseThrow(() -> new PatientNotFoundException("Patient Not Found with ID : " + id));

        //Check if The Email is already Exists
        if(patientRepo.existsByEmailAndIdNot(patientDTO.getEmail() , id ) ){
            throw new EmailAlreadyExistException("Patient with this email already exist  " + patientDTO.getEmail());
        }
        
        patient.setName(patientDTO.getName());
        patient.setEmail(patientDTO.getEmail());
        patient.setAddress(patientDTO.getAddress());
        patient.setDateOfBirth( LocalDate.parse( patientDTO.getDateOfBirth() ) );

        Patient updatedPatient = patientRepo.save(patient);

        return PatientMapper.toPatientDTO(updatedPatient);
    }
}
