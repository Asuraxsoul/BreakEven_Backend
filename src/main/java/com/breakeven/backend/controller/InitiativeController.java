package com.breakeven.backend.controller;

import com.breakeven.backend.error.CustomResponse;
import com.breakeven.backend.model.Initiative;
import com.breakeven.backend.service.InitiativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/initiative")
public class InitiativeController {
    private final InitiativeService initiativeService;

    @Autowired
    public InitiativeController(InitiativeService initiativeService) {
        this.initiativeService = initiativeService;
    }

    @GetMapping("/all")
    public List<Initiative> getAllInitiatives() {
        return initiativeService.getAllInitiatives();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Initiative> getInitiative(@PathVariable UUID id) {
        return initiativeService.getInitiativeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<?> getInitiativesByName(@RequestParam String name) {
        try {
            List<Initiative> initiatives = initiativeService.getInitiativesByName(name);
            return ResponseEntity.ok(initiatives);
        } catch (Exception e) {
            CustomResponse customResponse = new CustomResponse(
                    CustomResponse.ResponseStatus.Fail,
                    "Search Initiative",
                    "Error occurred while searching for initiatives: " + e.getMessage()
            );

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customResponse);
        }
    }

    @PostMapping
    public ResponseEntity<?> createInitiative(@RequestBody Initiative initiative) {
        Optional<Initiative> newInitiative = initiativeService.addNewInitiative(initiative);

        if (newInitiative.isEmpty()) {
            CustomResponse customResponse = new CustomResponse(
                    CustomResponse.ResponseStatus.Fail,
                    "Add Initiative",
                    "Initiative already exists."
            );
            return ResponseEntity.status(HttpStatus.OK).body(customResponse);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(newInitiative);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInitiative(@PathVariable UUID id) {
        try {
            initiativeService.deleteInitiative(id);
            CustomResponse customResponse = new CustomResponse(
                    CustomResponse.ResponseStatus.Success,
                    "Delete Initiative",
                    "Deleted initiative record."
            );
            return ResponseEntity.status(HttpStatus.OK).body(customResponse);
        } catch (Exception e) {
            CustomResponse customResponse = new CustomResponse(
                    CustomResponse.ResponseStatus.Fail,
                    "Delete Initiative",
                    "Error occurred while deleting initiative: " + e.getMessage()
            );

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customResponse);
        }
    }

}
