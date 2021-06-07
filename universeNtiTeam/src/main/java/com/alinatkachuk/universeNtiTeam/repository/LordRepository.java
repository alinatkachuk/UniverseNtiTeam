package com.alinatkachuk.universeNtiTeam.repository;

import com.alinatkachuk.universeNtiTeam.model.Lord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LordRepository extends JpaRepository<Lord, Long> {

    @Override
    List<Lord> findAll();

    Optional<Lord> findById (Long lordId);

    List<Lord> findByPlanetsIsNull ();

    List<Lord> findTop10ByOrderByAgeAsc ();
}
