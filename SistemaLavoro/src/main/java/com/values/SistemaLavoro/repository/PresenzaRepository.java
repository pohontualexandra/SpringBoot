package com.values.SistemaLavoro.repository;

import com.values.SistemaLavoro.model.Presenza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PresenzaRepository extends JpaRepository<Presenza, Long> {
    Presenza findPresenzaById(Long id);

    @Query("SELECT SUM(oreLavoro) FROM Presenza p WHERE p.user.id = :userId")
    Double selectTotals(Long userId);

    List<Presenza> findAllByUserId(Long userId);
}
