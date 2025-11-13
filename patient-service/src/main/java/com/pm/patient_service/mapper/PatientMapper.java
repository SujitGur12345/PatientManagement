package com.pm.patient_service.mapper;

import com.pm.patient_service.DTO.PatientResponseDTO;
import com.pm.patient_service.DTO.PatientRequestDTO;
import com.pm.patient_service.model.Patient;

import java.time.LocalDate;

public class PatientMapper {

    //Patient to PatientDTO
  public static PatientResponseDTO toPatientDTO(Patient p) {

      PatientResponseDTO dto = new PatientResponseDTO();
      dto.setId(p.getId().toString());
      dto.setName(p.getName());
      dto.setAddress(p.getAddress());
      dto.setEmail(p.getEmail());
      dto.setAddress(p.getAddress());


      return dto;
  }

  //Patient to PatientRequestDTO

    public static Patient toPatient(PatientRequestDTO dto) {

      Patient p = new Patient();

      p.setName(dto.getName());
      p.setAddress(dto.getAddress());
      p.setEmail(dto.getEmail());
      p.setDateOfBirth(LocalDate.parse( dto.getDateOfBirth()));

      p.setRegistrationDate(LocalDate.parse(dto.getRegisteredDate()));


      return p;


    }

}


