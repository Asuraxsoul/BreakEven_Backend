package com.breakeven.backend.dataAccessObject;

import com.breakeven.backend.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {
    List<Person> findByNameContaining(String name);

    Optional<Person> findByName(String name);
}
