package com.breakeven.backend.dataAccessObject;

import com.breakeven.backend.model.Initiative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InitiativeRepository extends JpaRepository<Initiative, UUID> {
    List<Initiative> findByNameContaining(String name);

    Optional<Initiative> findByName(String name);
}
