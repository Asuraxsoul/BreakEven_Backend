package com.breakeven.backend.service;

import com.breakeven.backend.dataAccessObject.InitiativeRepository;
import com.breakeven.backend.model.Initiative;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InitiativeService {
    private final InitiativeRepository initiativeRepository;

    @Autowired
    public InitiativeService(InitiativeRepository initiativeRepository) {
        this.initiativeRepository = initiativeRepository;
    }

    public List<Initiative> getAllInitiatives() {
        return initiativeRepository.findAll();
    }

    public Optional<Initiative> getInitiativeById(UUID id) {
        return initiativeRepository.findById(id);
    }

    public List<Initiative> getInitiativesByName(String name) {
        return initiativeRepository.findByNameContaining(name);
    }

    public Initiative createInitiative(Initiative initiative) {
        return initiativeRepository.save(initiative);
    }

    public void deleteInitiative(UUID id) {
        initiativeRepository.deleteById(id);
    }

    public Optional<Initiative> addNewInitiative(Initiative initiative) {
        Optional<Initiative> existingInitiative = initiativeRepository.findByName(initiative.getName());
        if (existingInitiative.isEmpty()) {
            return Optional.of(initiativeRepository.save(initiative));
        }

        return Optional.empty();
    }
}
