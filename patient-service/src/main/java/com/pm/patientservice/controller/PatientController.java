package com.pm.patientservice.controller;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.dto.validators.CreatePatientValidationGroup;
import com.pm.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/* http://localhost:4000/v3/api-docs - Put this in Swagger UI */

@RestController
@RequestMapping("/patients")
@Tag(name = "Patient", description = "API for managing Patients")
public class PatientController {
    private final PatientService patientService;
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    @Operation(summary = "Get all Patients")
    public ResponseEntity<List<PatientResponseDTO>> getPatients() {
        List<PatientResponseDTO> patientsDTO = patientService.getPatients();
        return ResponseEntity.ok().body(patientsDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a Patient by Id")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable UUID id) {
        PatientResponseDTO patientDTO = patientService.getPatientById(id);
        return ResponseEntity.ok().body(patientDTO);
    }

    @PostMapping
    @Operation(summary = "Create a new Patient")
    public ResponseEntity<PatientResponseDTO> createPatient(
            @Validated({Default.class, CreatePatientValidationGroup.class})
            @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO newPatientDTO = patientService.createPatient(patientRequestDTO);
        return ResponseEntity.ok().body(newPatientDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an Existing Patient by Id")
    public ResponseEntity<PatientResponseDTO> updatePatient(
            @PathVariable UUID id,
            @Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO updatedPatientDTO = patientService.updatePatient(id, patientRequestDTO);
        return ResponseEntity.ok().body(updatedPatientDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Patient by Id")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
